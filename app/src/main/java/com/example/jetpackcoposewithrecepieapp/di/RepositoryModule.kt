package com.example.jetpackcoposewithrecepieapp.di

import com.example.jetpackcoposewithrecepieapp.network.RecipeService
import com.example.jetpackcoposewithrecepieapp.network.model.RecipeDtoMapper
import com.example.jetpackcoposewithrecepieapp.repository.RecipeRepository
import com.example.jetpackcoposewithrecepieapp.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepository_Impl(recipeService, recipeDtoMapper)
    }

}