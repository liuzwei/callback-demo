package com.woasis.callbackdemo.bean;

/**
 * 消息对象
 */
public abstract class MessageInfo {

    //开始时间
    private long startTime;
    //更新时间
    private long updateTime;
    //是否回调成功
    private boolean isSuccess=false;
    //回调次数
    private int count=0;
    //回调策略
    private CallbackType callbackType;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CallbackType getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(CallbackType callbackType) {
        this.callbackType = callbackType;
    }
}
