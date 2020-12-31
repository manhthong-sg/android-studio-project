package com.manhthong.chatsocketio.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manhthong.chatsocketio.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //Link API 172.168.10.233:3000/users
    //khoi tao gson
    Gson gson=new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService= new Retrofit.Builder()
            .baseUrl("http://192.168.1.5:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

//    @GET("/users")
//    Call<User> showUser();

//    @GET("/users/{phoneNumber}/password")
//    Call<User> getApiPassword(@Path("phoneNumber") String password );
    @POST("/users/data/update")
    Call<User> updateMyProfile(@Body User user);

    @POST("/users/data/updatepassword")
    Call<User> updateMyPassword(@Body User user);

    @GET("/users/data/{phoneNumber}")
    Call<List<User>> getApiLogin(@Path("phoneNumber") String phoneNumber);

    @GET("/users/data/{phoneNumber}")
    Call<List<User>> getInfoMainActivity(@Path("phoneNumber") String phoneNumber);

    //@Path("phoneNumber") String phone_number

    @POST("/users")
    Call<User> insertUser(@Body User user);
}
