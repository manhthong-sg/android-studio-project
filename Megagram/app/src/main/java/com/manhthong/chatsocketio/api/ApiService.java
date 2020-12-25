package com.manhthong.chatsocketio.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manhthong.chatsocketio.Model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    //Link API 172.168.10.233:3000/users
    //khoi tao gson
    Gson gson=new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService= new Retrofit.Builder()
            .baseUrl("http://172.168.10.233:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

    @GET("/users")
    Call<User> showUser();

    @POST("/users")
    Call<User> insertUser(@Body User user);
}
