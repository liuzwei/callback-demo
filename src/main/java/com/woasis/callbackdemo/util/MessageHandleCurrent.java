package com.woasis.callbackdemo.util;

import com.alibaba.fastjson.JSONObject;
import com.woasis.callbackdemo.bean.CallbackType;
import com.woasis.callbackdemo.bean.WorkOrderMessage;
import com.woasis.callbackdemo.remote.WorkOrderMessageInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 当前消息处理
 */
public class MessageHandleCurrent implements Runnable{

    @Override
    public void run() {
        while (true){
            //从队列获取数据,执行回调接口
            try {
                WorkOrderMessage message = MessageQueue.getMessageFromCurrent();
                System.out.println("MessageHandleCurrent-获取到数据："+JSONObject.toJSONString(message));
                remoteCallback(message);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    private void remoteCallback(WorkOrderMessage message){
        WorkOrderMessageInterface workOrderMessageInterface = RetrofitHelper.instance().create(WorkOrderMessageInterface.class);

        Call<JSONObject> objectCall = workOrderMessageInterface.updateBatteryInfo(message);

        objectCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                System.out.println("****调用成功"+Thread.currentThread().getId());
                message.setSuccess(true);
                System.out.println("MessageHandleCurrent-回调成功"+JSONObject.toJSONString(message));
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable throwable) {
                System.out.println("++++调用失败"+Thread.currentThread().getId());
                //失败后再将数据放入队列
                try {
                    //对回调策略初始化
                    long currentTime = System.currentTimeMillis();
                    message.setStartTime(currentTime);
                    message.setUpdateTime(currentTime);
                    message.setSuccess(false);
                    message.setCount(1);
                    message.setCallbackType(CallbackType.SECONDS_10);
                    MessageQueue.putMessageToNext(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("MessageHandleCurrent-放入队列数据失败");
                }
            }
        });
    }
}
