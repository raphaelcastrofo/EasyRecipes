package com.devspace.myapplication

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MainScreen(navController: NavHostController) {

    var randomRecipes by remember { mutableStateOf <List<RecipeDto>>(emptyList()) }

    val retrofit = RetrofitClient.retrofitInstance.create(ApiService::class.java)

    val callRandomRecipes = retrofit.getRecipes()
    callRandomRecipes.enqueue(object : Callback<RecipesResponse> {
        override fun onResponse(
            call: Call<RecipesResponse>,
            response: Response<RecipesResponse>
        ) {
            if (response.isSuccessful){
                val recipes = response.body()?.results
                if (recipes != null){
                    randomRecipes = recipes
                }

            }
        }

        override fun onFailure(
            call: Call<RecipesResponse>,
            t: Throwable) {
            Log.d("MainActivity", "Network Error :: ${t.message}")
        }

    })


}