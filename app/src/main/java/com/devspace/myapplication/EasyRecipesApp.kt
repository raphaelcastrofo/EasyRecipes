package com.devspace.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devspace.myapplication.ui.theme.DetailScreen

@Composable
fun EasyRecipesApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "recipeList") {

        composable(route = "recipeList"){
            MainScreen(navController)
        }
        composable(route = "recipeDetail"){
            DetailScreen()
        }
    }
}
