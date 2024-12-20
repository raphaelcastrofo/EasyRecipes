package com.devspace.myapplication

import android.util.Log
import com.devspace.myapplication.ui.theme.EasyRecipesTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
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

    RecipeContent(
        radomRecipes = randomRecipes,
    ) {
        itemClicked ->
        navController.navigate(route = "recipeDetail/${itemClicked.id}")
    }


}

@Composable

private fun RecipeContent(
    radomRecipes: List<RecipeDto>,
    onClick: (RecipeDto) -> Unit
) {

    RecipeSession(
        recipeList = radomRecipes,
        onClick = onClick
    )

}

@Composable
private fun RecipeSession(
    recipeList: List<RecipeDto>,
    onClick: (RecipeDto)->Unit,
){

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            fontSize = 24.sp,
            text = "Recipes",
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.size(8.dp))

        RecipeList(recipeList = recipeList, onClick = onClick)
    }
}

@Composable
private fun RecipeList(
    recipeList: List<RecipeDto>,
    onClick: (RecipeDto) -> Unit,
){
    LazyColumn {
        items(recipeList){
            RecipeCard(
                recipeDto = it,
                onClick = onClick
            )
        }
    }
}

@Composable

private fun RecipeCard(
    recipeDto: RecipeDto,
    onClick: (RecipeDto) -> Unit
){
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .clickable {
                onClick.invoke(recipeDto)
            }
    ){
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 8.dp , topStart = 8.dp))
                .fillMaxWidth()
                .height(150.dp),
            model = recipeDto.image, contentDescription = "${recipeDto.title} Image"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    EasyRecipesTheme {
        RecipeContent(
            radomRecipes = emptyList(),
            onClick = {

            }
        )
    }
}