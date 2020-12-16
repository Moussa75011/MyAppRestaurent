package com.example.myapprestaurent.retrofit;

import com.example.myapprestaurent.model.FoodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    //faisons notre modele de class de données json

    @GET("fooddata.json")
    Call<List<FoodData>> getAllData();

}
