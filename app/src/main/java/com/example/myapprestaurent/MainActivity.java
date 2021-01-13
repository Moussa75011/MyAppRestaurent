package com.example.myapprestaurent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapprestaurent.adapter.PopularAdapter;
import com.example.myapprestaurent.model.FoodData;
import com.example.myapprestaurent.model.Popular;
import com.example.myapprestaurent.model.Recommended;
import com.example.myapprestaurent.retrofit.ApiInterface;
import com.example.myapprestaurent.retrofit.RetrofitClient;

import com.example.myapprestaurent.adapter.AllMenuAdapter;
import com.example.myapprestaurent.adapter.PopularAdapter;
import com.example.myapprestaurent.adapter.RecommendedAdapter;
import com.example.myapprestaurent.model.Allmenu;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    RecyclerView popularRecyclerView, recommendedRecyclerView, allMenuRecyclerView;

    PopularAdapter popularAdapter;
    RecommendedAdapter recommendedAdapter;
    AllMenuAdapter allMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<FoodData>> call = apiInterface.getAllData();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {

                List<FoodData> foodDataList = response.body();


                getPopularData(foodDataList.get(0).getPopular());

                getRecommendedData(foodDataList.get(0).getRecommended());

                getAllMenu(foodDataList.get(0).getAllmenu());



                // lets run it.
                // extraction des a partir du serveur database model Json
                // maintenant,affichage des donné en utilisant la vu recycler
                // creation d'une vue recycleradapter
                // configuration et attache du secteur populaire
                //
                // we add two adapter class for allmenu and recommended items.
                // so lets do it fast.

            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Le serveur ne repond pas.", Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void  getPopularData(List<Popular> popularList){

        //initialisation de popularRecyclerView
        popularRecyclerView = findViewById(R.id.popular_recycler);

        /*
        popularAdapter nous permet de le lien entre le recyclerView (popularrecyclerView)
        et les données de popular qu'on souhaite afficher
        */
        popularAdapter = new PopularAdapter(this, popularList);


        // on va creer layout manager qui sera associer a ce recyclerView donc popularRecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }

    private void  getRecommendedData(List<Recommended> recommendedList){

        recommendedRecyclerView = findViewById(R.id.recommended_recycler);
        recommendedAdapter = new RecommendedAdapter(this, recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendedAdapter);

    }

    private void  getAllMenu(List<Allmenu> allmenuList){

        allMenuRecyclerView = findViewById(R.id.all_menu_recycler);
        allMenuAdapter = new AllMenuAdapter(this, allmenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allMenuRecyclerView.setLayoutManager(layoutManager);
        allMenuRecyclerView.setAdapter(allMenuAdapter);
        allMenuAdapter.notifyDataSetChanged();

    }
    // today w are going to make a food app like zomato and swiggy.
    // we have 3 category in data
    // popular items, recommended items and all menu,
    // lets have have a look on json data.
    // so lets start coding.
    // lets add retrofit dependency in gradle file for network call.
    // we have setup model class and adapter class
    // now we are going to setup data in recyclerview.
    // complited all recyclerview
    // now we will setup on click listener on items.
    // tutorial complited see you in the next video.



}
