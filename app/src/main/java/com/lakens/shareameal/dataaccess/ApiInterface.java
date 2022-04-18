package com.lakens.shareameal.dataaccess;

import com.lakens.shareameal.domain.Meal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    //Get all meals
    @GET("api/meal")
    Call<Meal> getMeals();
}
