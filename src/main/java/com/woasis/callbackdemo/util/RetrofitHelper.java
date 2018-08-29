package com.woasis.callbackdemo.util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitHelper {

    private static final String HTTP_URL = "http://baidu.com/";
    private static Retrofit retrofit;

    public static Retrofit instance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(HTTP_URL)
                    .client(new OkHttpClient.Builder()
                            .connectTimeout(30, TimeUnit.SECONDS)//连接时间
                            .readTimeout(30, TimeUnit.SECONDS)//读时间
                            .writeTimeout(30, TimeUnit.SECONDS)//写时间
                            .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
