package com.devspace.myapplication.ui.theme

data class RecipesResponse(
    val results : List<RecipesDto>
)

data class RecipesDto(
    val id: Int,
    val title: String,
    val summary : String,
    val image: String
)