package com.example.unde_sample;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetroFitInterface {
    @GET("api/users/2")
    Call<UserData> getUser();

    @FormUrlEncoded
    @POST("api/users")
    Call<CreateResponse> createUser(@Field("name") String name, @Field("job") String job);

    @FormUrlEncoded
    @PUT("api/users367")
    Call<CreateResponse> updateUser(@Field("name") String name, @Field("job") String job);

    @DELETE("api/users/367")
    Call<Void>  deleteUser();
}
