package com.example.jetpackcoposewithrecepieapp.network.responses

import com.example.jetpackcoposewithrecepieapp.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("results")
    var recipes: List<RecipeDto>,
) {
}