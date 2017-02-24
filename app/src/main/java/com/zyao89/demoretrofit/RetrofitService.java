package com.zyao89.demoretrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zyao89 on 2017/2/24.
 */
public interface RetrofitService
{
    @Headers({"Content-type:application/json"})
    @POST("login")
    Call<ResponseBody> login(@Body User user);

//    @FormUrlEncoded
//    @POST("login")
//    Call<ResponseBody> login(@Field("username") String username, @Field("password") String password);

    @Headers({"Content-type:application/json"})
    @GET("{url}")
    Call<ResponseBody> login(@Path("url") String url, @Query("username") String username, @Query("password") String password);

    @Headers({"Content-type:application/json"})
    @GET("login")
    Call<ResponseBody> login(@Query("username") String username, @Query("password") String password);

    @Headers({"Content-type:application/json"})
    @PUT("put")
    Call<ResponseBody> put(@Body User user);

    @Headers({"Content-type:application/json"})
    @HTTP(method = "DELETE", path = "/delete", hasBody = true)
    Call<ResponseBody> delete(@Body DeleteID id);

    @Headers({"Content-type:application/json"})
    @GET("get/{id}")
    Call<ResponseBody> get(@Path("id") int id);

    @Headers({"Content-type:application/json"})
    @GET("get/{id}")
    Call<User> getUser(@Path("id") int id);

    @Headers({"Content-type:application/json"})
    @GET("get")
    Call<ResponseBody> query(@Query("id") int id);

    @FormUrlEncoded
    @POST("user/edit")
    Call<ResponseBody> updateUser(@Field("username") String username, @Field("password") String password);
}
