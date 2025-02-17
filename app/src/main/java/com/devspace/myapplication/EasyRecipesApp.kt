package com.devspace.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devspace.myapplication.detail.presentation.RecipeDetailViewModel
import com.devspace.myapplication.detail.presentation.ui.RecipeDetailScreen
import com.devspace.myapplication.list.presentation.RecipeListViewModel
import com.devspace.myapplication.list.presentation.ui.MainScreen
import com.devspace.myapplication.onboarding.presentation.OnBoardingScreen

@Composable
fun EasyRecipesApp(
    recipeViewModel: RecipeListViewModel,
    detailViewModel: RecipeDetailViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "onBoarding") {


        composable(route = "onBoarding"){
            OnBoardingScreen(navController)
        }
        composable(route = "recipeList"){
            MainScreen(navController, recipeViewModel)
        }
        composable(
            route = "recipeDetail" + "/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
            })

        )
        { backStackEntry ->
            val recipeId = requireNotNull(backStackEntry.arguments?.getString("id"))
            RecipeDetailScreen(recipeId,navController,detailViewModel)
        }
    }
}
