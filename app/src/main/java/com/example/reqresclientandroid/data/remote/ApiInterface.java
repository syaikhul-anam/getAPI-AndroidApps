package com.example.reqresclientandroid.data.remote;

import com.example.reqresclientandroid.data.model.BaseListResponse;
import com.example.reqresclientandroid.data.model.BaseRespone;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("users/{id}")
    Call<BaseRespone> getUser(@Path("id")int id);

    @GET("users")
    Call<BaseListResponse> getUsers();
}
