package com.example.jetpackcoposewithrecepieapp.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcoposewithrecepieapp.domain.model.Recipe
import com.example.jetpackcoposewithrecepieapp.repository.RecipeRepository
import com.example.jetpackcoposewithrecepieapp.repository.RecipeRepository_Impl
import kotlinx.coroutines.launch
import javax.inject.Named

class RecipeListViewModel
@ViewModelInject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String
): ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    val isLoadingFood: MutableState<Boolean> = mutableStateOf(false)
    var categoryScrollPosition: Float = 0f

    init {
        newSearch()
    }

    fun newSearch() {
        isLoadingFood.value = true
        resetSearchState()
        viewModelScope.launch {
            val result = repository.search(token, 1, query.value)
            recipes.value = result
            isLoadingFood.value = false
        }
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        if (selectedCategory.value?.value != query.value) clearSelectedCategory()
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Float) {
        this.categoryScrollPosition = position
    }

}






















