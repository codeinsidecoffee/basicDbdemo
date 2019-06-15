package com.mrlonewolfer.basicdbdemo;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofitClient {
    public static MyRetrofitService getClient(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyRetrofitService myRetrofitService=retrofit.create(MyRetrofitService.class);


        return myRetrofitService;
    }
}
