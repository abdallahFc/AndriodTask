package com.example.myapplication.data;

import com.example.myapplication.model.ProducetModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("products")
    Call<ProducetModel> getAllProduct();
}
