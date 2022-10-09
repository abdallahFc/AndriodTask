package com.example.myapplication.data;

import com.example.myapplication.model.ProducetModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL ="https://dummyjson.com/";
    private static ApiClient INSTANCE;
    private ApiInterface apiInterface;

    public ApiClient() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface=retrofit.create(ApiInterface.class);
    }
    public static ApiClient getInstance(){
        if(INSTANCE==null)
            INSTANCE=new ApiClient();
        return INSTANCE;
    }
    public Call<ProducetModel> getALlProducet(){
        return apiInterface.getAllProduct();
    }
}
