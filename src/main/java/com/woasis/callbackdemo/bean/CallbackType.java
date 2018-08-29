package com.woasis.callbackdemo.bean;


/**
 * 回调策略
 */
public enum CallbackType {

    //等级1，10s执行3次
    SECONDS_10(1, 10, 3),
    //等级2，30s执行2次
    SECONDS_30(2, 30, 2),
    //等级3，60s执行2次
    MINUTE_1(3, 60, 2),
    //等级4，5min执行1次
    MINUTE_5(4, 300, 1),
    //等级5，30min执行1次
    MINUTE_30(5, 30*60, 1),
    //等级6，1h执行2次
    HOUR_1(6, 60*60, 1),
    //等级7，3h执行2次
    HOUR_3(7, 60*60*3, 1),
    //等级8，6h执行2次
    HOUR_6(8, 60*60*6, 1);

    //级别
    private int level;
    //回调间隔时间 秒
    private int intervalTime;
    //回调次数
    private int count;


    CallbackType(int level, int intervalTime, int count) {
        this.level = level;
        this.intervalTime = intervalTime;
        this.count = count;
    }

    public static CallbackType getTypeByLevel(int level){
        switch (level){
            case 1:
                return SECONDS_10;
            case 2:
                return SECONDS_30;
            case 3:
                return MINUTE_1;
            case 4:
                return MINUTE_5;
            case 5:
                return MINUTE_30;
            case 6:
                return HOUR_1;
            case 7:
                return HOUR_3;
            case 8:
                return HOUR_6;
            default:
                return HOUR_6;
        }
    }

    public static int getCount(CallbackType type){
        switch (type){
            case SECONDS_10:
                return SECONDS_10.count;
            case SECONDS_30:
                return SECONDS_30.count;
            case MINUTE_1:
                return MINUTE_1.count;
            case MINUTE_5:
                return MINUTE_5.count;
            case MINUTE_30:
                return MINUTE_30.count;
            case HOUR_1:
                return HOUR_1.count;
            case HOUR_3:
                return HOUR_3.count;
            case HOUR_6:
                return HOUR_6.count;
            default:
                return 0;
        }
    }

    public static int getLevel(CallbackType type){
        switch (type){
            case SECONDS_10:
                return SECONDS_10.level;
            case SECONDS_30:
                return SECONDS_30.level;
            case MINUTE_1:
                return MINUTE_1.level;
            case MINUTE_5:
                return MINUTE_5.level;
            case MINUTE_30:
                return MINUTE_30.level;
            case HOUR_1:
                return HOUR_1.level;
            case HOUR_3:
                return HOUR_3.level;
            case HOUR_6:
                return HOUR_6.level;
            default:
                return 0;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
