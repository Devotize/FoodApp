package com.example.jetpackcoposewithrecepieapp.repository

import android.util.Log
import com.example.jetpackcoposewithrecepieapp.domain.model.Recipe
import com.example.jetpackcoposewithrecepieapp.network.RecipeService
import com.example.jetpackcoposewithrecepieapp.network.model.RecipeDtoMapper
import com.example.jetpackcoposewithrecepieapp.util.TAG
import javax.inject.Inject
import javax.inject.Singleton


class RecipeRepository_Impl (
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
): RecipeRepository {


    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return mapper.toDomainModelList(recipeService.search(token, page, query).recipes)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token, id))
    }
}