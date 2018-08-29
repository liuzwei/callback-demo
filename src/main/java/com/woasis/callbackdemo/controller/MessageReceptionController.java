package com.woasis.callbackdemo.controller;

import com.woasis.callbackdemo.bean.WorkOrderMessage;
import com.woasis.callbackdemo.util.MessageQueue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息接收
 */
@RestController
@RequestMapping("/message")
public class MessageReceptionController {

    @PostMapping("/receive")
    public String receiveMessage(@RequestBody WorkOrderMessage message){
        try {
            MessageQueue.putMessageToCurrent(message);
        } catch (InterruptedException e) {
            System.out.println(e);
            return "FAIL";
        }
        return "SUCCESS";
    }

}
