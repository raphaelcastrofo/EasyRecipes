package com.devspace.myapplication.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.devspace.myapplication.common.data.RetrofitClient
import com.devspace.myapplication.common.model.RecipeDto
import com.devspace.myapplication.list.data.ListService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeListViewModel (private val listService: ListService
) : ViewModel() {

    private val _uiRecipes = MutableStateFlow<List<RecipeDto>>(emptyList())
    val uiRecipes: StateFlow<List<RecipeDto>> = _uiRecipes



    init {
        fetchRecipes()
    }

    private fun fetchRecipes(){
        viewModelScope.launch(Dispatchers.IO){
            val response = listService.getRecipes()
            if(response.isSuccessful) {
                val recipes = response.body()?.recipes
                if (recipes != null) {
                    _uiRecipes.value = recipes
                }
            } else {
                Log.d("RecipeListViewModel", "Request Error :: ${response.errorBody()}")
            }
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val listService = RetrofitClient.retrofitInstance.create(ListService::class.java)
                return RecipeListViewModel(
                    listService
                ) as T
            }
        }
    }


}