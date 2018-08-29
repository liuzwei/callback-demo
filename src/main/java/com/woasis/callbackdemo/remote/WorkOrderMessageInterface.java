package com.woasis.callbackdemo.remote;

import com.alibaba.fastjson.JSONObject;
import com.woasis.callbackdemo.bean.WorkOrderMessage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WorkOrderMessageInterface {

    @POST("/api/batteryInfo/save")
    Call<JSONObject> updateBatteryInfo(@Body WorkOrderMessage message);

}
