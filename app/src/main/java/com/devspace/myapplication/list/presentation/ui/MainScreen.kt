package com.devspace.myapplication.list.presentation.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devspace.myapplication.common.model.RecipeDto
import com.devspace.myapplication.list.presentation.RecipeListViewModel

@Composable
fun MainScreen(navController: NavHostController,viewModel: RecipeListViewModel) {

val randomRecipes by viewModel.uiRecipes.collectAsState()


    RecipeSession(
        recipes = randomRecipes,
    ) {
        itemClicked ->
        navController.navigate(route = "recipeDetail/${itemClicked.id}")
    }
}

@Composable
fun RecipeSession(
    recipes: List<RecipeDto>,
    onClick: (RecipeDto)->Unit,
){
    Column (
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            modifier = Modifier.padding(24.dp),
            fontSize = 32.sp,
            text = "Recipes",
            fontWeight = FontWeight.Bold
        )
        RecipeList(recipes = recipes, onClick = onClick)
    }
}

@Composable
private fun RecipeList(
    modifier: Modifier = Modifier,
    recipes: List<RecipeDto>,
    onClick: (RecipeDto) -> Unit,
){

    LazyColumn (
        modifier = modifier.padding(16.dp)
    ) {
        items(recipes){

            RecipeCard(
                recipe = it,
                onClick = onClick
            )
        }
    }
}

@Composable
fun RecipeCard(
    recipe: RecipeDto,
    onClick: (RecipeDto) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick.invoke(recipe) }
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp), clip = false),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = recipe.title.uppercase(),
            fontWeight = FontWeight.Bold)


        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            model = recipe.image,
            contentDescription = "${recipe.title} Image",

        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = recipe.summary,
            maxLines = 3,
            fontWeight = FontWeight.Light)
    }
}

