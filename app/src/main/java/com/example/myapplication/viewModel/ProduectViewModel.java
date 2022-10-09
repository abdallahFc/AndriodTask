package com.example.myapplication.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Model;
import com.example.myapplication.repo.Repositry;

import java.util.ArrayList;

public class ProduectViewModel extends ViewModel {
    private final Repositry repositry = new Repositry();

    public void getFromRepo() {
        repositry.getAllProduects();
    }

    public void getsearechFromRepo(String s) {
        repositry.filiter(s);
    }

    public void searechForCatogry(String s) {
        repositry.filiterCatogry(s);
    }

    public LiveData<ArrayList<Model>> allProducet() {
        return repositry.allProducet();
    }

    public LiveData<ArrayList<Model>> searech() {
        return repositry.search();
    }

    public LiveData<ArrayList<Model>> searechCatogry() {
        return repositry.searchCatogry();
    }

    public LiveData<String> error() {
        return repositry.error();
    }
}
