package com.woasis.callbackdemo.util;

import com.woasis.callbackdemo.bean.WorkOrderMessage;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 消息队列
 */
public class MessageQueue {

    //当前队列
    private static LinkedBlockingQueue<WorkOrderMessage> currentWorkOrderQueue = new LinkedBlockingQueue<>();

    //回调失败后的队列
    private static LinkedBlockingQueue<WorkOrderMessage> nextWorkOrderQueue = new LinkedBlockingQueue<>();

    /**
     * 获取当前队列
     * @return
     */
    public static LinkedBlockingQueue<WorkOrderMessage> getCurrentWorkOrderQueue(){
        return currentWorkOrderQueue;
    }

    /**
     * 获取失败后的队列
     * @return
     */
    public static LinkedBlockingQueue<WorkOrderMessage> getNextWorkOrderQueue(){
        return nextWorkOrderQueue;
    }

    /**
     * 向当前队列放入数据
     * @param message
     * @throws InterruptedException
     */
    public static void putMessageToCurrent(WorkOrderMessage message) throws InterruptedException {
        currentWorkOrderQueue.put(message);
    }

    /**
     * 向失败之后的队列放入数据
     * @param message
     * @throws InterruptedException
     */
    public static void putMessageToNext(WorkOrderMessage message) throws InterruptedException {
        nextWorkOrderQueue.put(message);
    }

    /**
     * 从当前队列获取数据
     * @return
     * @throws InterruptedException
     */
    public static WorkOrderMessage getMessageFromCurrent() throws InterruptedException {
//        System.out.println("MessageQueue-currentWorkOrderQueue-队列大小"+currentWorkOrderQueue.size());
        return currentWorkOrderQueue.take();
    }

    /**
     * 从失败后的队列回去数据
     * @return
     * @throws InterruptedException
     */
    public static WorkOrderMessage getMessageFromNext() throws InterruptedException {
//        System.out.println("MessageQueue-nextWorkOrderQueue-队列大小"+nextWorkOrderQueue.size());
        return nextWorkOrderQueue.take();
    }


}
