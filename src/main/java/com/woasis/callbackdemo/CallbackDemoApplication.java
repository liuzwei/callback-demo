package com.woasis.callbackdemo;

import com.woasis.callbackdemo.util.MessageHandleCurrent;
import com.woasis.callbackdemo.util.MessageHandleNext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CallbackDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallbackDemoApplication.class, args);
        //开启线程处理队列数据
        new Thread(new MessageHandleCurrent()).start();
        System.out.println("开启当前队列线程成功");
        new Thread(new MessageHandleNext()).start();
        System.out.println("开启迭代队列线程成功");
        System.out.println("程序启动成功");

    }
}
