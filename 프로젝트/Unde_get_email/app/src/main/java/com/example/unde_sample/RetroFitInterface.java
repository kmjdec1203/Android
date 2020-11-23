package com.example.unde_sample;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroFitInterface {
    @GET("api/users/2")
    Call<UserData> getUser();
}
