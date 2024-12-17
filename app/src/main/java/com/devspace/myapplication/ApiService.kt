package com.devspace.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("random?number=20")
    fun getRecipes() : Call<List<>>
}