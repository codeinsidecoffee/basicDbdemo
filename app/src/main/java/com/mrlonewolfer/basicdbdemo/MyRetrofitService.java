package com.mrlonewolfer.basicdbdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyRetrofitService {


    // NOTE: Here We use only on php file for insert delete ,update and retrive.
    // you can split this php file and use as per your convience


    //create Callable for your model class
    // then after write down @POST to perform post Request you can Write @GET for get Request
    // Then above it write Down @FROMURLENCODE To send Data in encoded From
    @FormUrlEncoded
    @POST(Const.USER_PHP_FILENAME)
    Call<UserBeanRoot> selectUSer(@Field(Const.FLAG)String flag);


    // ABove code Used to Retrive Http Data
    //Now you can Create Multiple Callable instance as per your Requirment and api
    //below code is use to select data
    @FormUrlEncoded
    @POST(Const.USER_PHP_FILENAME)
    Call<String> insertUser(@Field(Const.FLAG) String flag,
                            @Field(Const.FIRST_NAME) String firstName,
                            @Field(Const.LAST_NAME) String lastName,
                            @Field(Const.MOBILE) String mobile);


    @FormUrlEncoded
    @POST(Const.USER_PHP_FILENAME)
    Call<String> updateUser(@Field(Const.FLAG) String flag,
                            @Field(Const.ID) String id,
                            @Field(Const.FIRST_NAME) String firstName,
                            @Field(Const.LAST_NAME) String lastName,
                            @Field(Const.MOBILE) String mobile);


    @FormUrlEncoded
    @POST(Const.USER_PHP_FILENAME)
    Call<String> deleteUser(@Field(Const.FLAG) String flag,
                            @Field(Const.ID) String id);
}
