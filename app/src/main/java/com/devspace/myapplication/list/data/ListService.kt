package com.devspace.myapplication.list.data

import com.devspace.myapplication.common.model.RecipeDto
import com.devspace.myapplication.common.model.RecipesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ListService {

    @GET("random?number=20")
    suspend fun getRecipes() : Response<RecipesResponse>
}