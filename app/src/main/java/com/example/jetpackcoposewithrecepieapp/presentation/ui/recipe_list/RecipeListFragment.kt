package com.example.jetpackcoposewithrecepieapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.jetpackcoposewithrecepieapp.R
import com.example.jetpackcoposewithrecepieapp.domain.model.Recipe
import com.example.jetpackcoposewithrecepieapp.presentation.components.FoodCategoryChip
import com.example.jetpackcoposewithrecepieapp.presentation.components.RecipeCard
import com.example.jetpackcoposewithrecepieapp.util.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value

                Column {
                    Surface(
                        elevation = 8.dp,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                TextField(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .padding(8.dp),
                                    value = query,
                                    onValueChange = {
                                        viewModel.onQueryChanged(it)
                                    },
                                    label = {
                                        Text(text = "Search")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Search
                                    ),
                                    leadingIcon = {
                                        Icon(Icons.Filled.Search)
                                    },
                                    onImeActionPerformed = { action, softKeyboardController ->
                                        if (action == ImeAction.Search) {
                                            viewModel.newSearch()
                                            softKeyboardController?.hideSoftwareKeyboard()
                                        }
                                    },
                                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                                    backgroundColor = MaterialTheme.colors.surface
                                )

                            }
                            ScrollableRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp, bottom = 8.dp)
                            ) {
                                for(category in getAddFoodCategories()){
                                    FoodCategoryChip(
                                        category = category.value,
                                        isSelected = selectedCategory == category,
                                        onSelectedCategoryChanged = {
                                            viewModel.onSelectedCategoryChanged(it)
                                        },
                                        onExecuteSearch = viewModel::newSearch
                                    )
                                }
                            }
                        }
                    }
                    LazyColumn() {
                        itemsIndexed(
                            items = recipes
                        ) { index, recipe ->
                            RecipeCard(recipe = recipe, onClick = {

                            })
                        }
                    }

                }


            }
        }


    }


}