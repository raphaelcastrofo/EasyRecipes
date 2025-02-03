package com.devspace.myapplication

import android.media.Image
import java.util.IntSummaryStatistics


data class RecipesResponse(
    val results: List<RecipeDto>
)


data class RecipeDto (
    val id: Int,
    val name: String,
    val image:String,
    val aisle: String,
)
