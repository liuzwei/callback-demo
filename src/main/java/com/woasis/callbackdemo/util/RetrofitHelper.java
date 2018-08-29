package com.woasis.callbackdemo.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final String HTTP_URL = "http://baidu.com/";
    private static Retrofit retrofit;

    public static Retrofit instance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(HTTP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
