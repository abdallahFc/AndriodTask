package com.example.myapplication.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.ApiClient;
import com.example.myapplication.model.Model;
import com.example.myapplication.model.ProducetModel;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositry {
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Model>> responseProducet = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Model>> change = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Model>> change2 = new MutableLiveData<>();
    public void getAllProduects() {
        ApiClient.getInstance().getALlProducet().enqueue(new Callback<ProducetModel>() {
            @Override
            public void onResponse(Call<ProducetModel> call, Response<ProducetModel> response) {
                if (response.isSuccessful()) {
                    responseProducet.setValue(response.body().getProducts());
                } else {
                    error.setValue(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ProducetModel> call, Throwable t) {
                error.setValue(t.toString());
            }
        });
    }

    public LiveData<ArrayList<Model>> allProducet() {
        return responseProducet;
    }

    public LiveData<String> error() {
        return error;
    }

    public void filiter(String text) {
        ArrayList<Model> list = new ArrayList<>();
        for (Model item : responseProducet.getValue()) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase(Locale.ROOT))) {
                list.add(item);
            }
        }
        change.setValue(list);
    }

    public LiveData<ArrayList<Model>> search() {
        return change;
    }
    public void filiterCatogry(String text) {
        ArrayList<Model> list = new ArrayList<>();
        for (Model item : responseProducet.getValue()) {
            if (item.getCategory().toLowerCase().contains(text.toLowerCase(Locale.ROOT))) {
                list.add(item);
            }
        }
        change2.setValue(list);
    }

    public LiveData<ArrayList<Model>> searchCatogry() {
        return change2;
    }
}
