package com.woasis.callbackdemo.util;

import com.alibaba.fastjson.JSONObject;
import com.woasis.callbackdemo.bean.CallbackType;
import com.woasis.callbackdemo.bean.WorkOrderMessage;
import com.woasis.callbackdemo.remote.WorkOrderMessageInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 失败后的一次消息处理
 */
public class MessageHandleNext implements Runnable {

    private ScheduledExecutorService pool = Executors.newScheduledThreadPool(16);

    @Override
    public void run() {
        while (true){
            //从队列获取数据，交给定时器执行
            try {
                WorkOrderMessage message = MessageQueue.getMessageFromNext();
                long excueTime = message.getUpdateTime()+message.getCallbackType().getIntervalTime()* 1000;
                long t = excueTime - System.currentTimeMillis();
                if (t/1000 < 5) {//5s之内将要执行的数据提交给调度线程池
                    System.out.println("MessageHandleNext-满足定时器执行条件"+JSONObject.toJSONString(message));
                    pool.schedule(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            remoteCallback(message);
                            return true;
                        }
                    }, t, TimeUnit.MILLISECONDS);
                }else {
                    MessageQueue.putMessageToNext(message);
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    private void remoteCallback(WorkOrderMessage message){
        WorkOrderMessageInterface workOrderMessageInterface = RetrofitHelper.instance().create(WorkOrderMessageInterface.class);

        Call<JSONObject> objectCall = workOrderMessageInterface.updateBatteryInfo(message);
        System.out.println("远程调用执行："+new Date());

        objectCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                System.out.println("MessageHandleNext****调用成功"+Thread.currentThread().getId());
                message.setSuccess(true);
                System.out.println("MessageHandleNext-回调成功"+JSONObject.toJSONString(message));
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable throwable) {
                System.out.println("MessageHandleNext++++调用失败"+Thread.currentThread().getId());
                //失败后再将数据放入队列
                try {
                    //对回调策略初始化
                    long currentTime = System.currentTimeMillis();
                    message.setUpdateTime(currentTime);
                    message.setSuccess(false);
                    CallbackType callbackType = message.getCallbackType();
                    //获取等级
                    int level = CallbackType.getLevel(callbackType);
                    //获取次数
                    int count = CallbackType.getCount(callbackType);
                    //如果等级已经最高，则不再回调
                    if (CallbackType.HOUR_6.getLevel() == callbackType.getLevel() && count == message.getCount()){
                        System.out.println("MessageHandleNext-等级最高，不再回调， 线下处理:"+JSONObject.toJSONString(message));
                    }else {
                        //看count是否最大，count次数最大则增加level
                        if (message.getCount()<callbackType.getCount()){
                            message.setCount(message.getCount()+1);
                        }else {//如果不小，则增加level
                            message.setCount(1);
                            level += 1;
                            message.setCallbackType(CallbackType.getTypeByLevel(level));
                        }
                        MessageQueue.putMessageToNext(message);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("MessageHandleNext-放入队列数据失败");
                }
            }
        });
    }
}
