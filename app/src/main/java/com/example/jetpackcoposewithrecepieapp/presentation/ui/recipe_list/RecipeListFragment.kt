package com.example.jetpackcoposewithrecepieapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
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
import com.example.jetpackcoposewithrecepieapp.presentation.BaseApplication
import com.example.jetpackcoposewithrecepieapp.presentation.components.*
import com.example.jetpackcoposewithrecepieapp.presentation.components.HeartAnimationDefinition.HeartButtonState.*
import com.example.jetpackcoposewithrecepieapp.presentation.theme.AppTheme
import com.example.jetpackcoposewithrecepieapp.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {
    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(darkTheme = application.isDark.value) {
                    val recipes = viewModel.recipes.value
                    val query = viewModel.query.value
                    val selectedCategory = viewModel.selectedCategory.value
                    val isLoading = viewModel.isLoadingFood.value

                    Column {
                        SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = viewModel::newSearch,
                            scrollPosition = viewModel.categoryScrollPosition,
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
                            onToggleTheme = {
                                application.toggleTheme()
                            }
                        )


                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .background(MaterialTheme.colors.background)
                        ) {
                            if (isLoading){
                                LoadingRecipeListShimmer(imageHeight = 250.dp)
                            } else {
                                LazyColumn() {
                                    itemsIndexed(
                                        items = recipes
                                    ) { index, recipe ->
                                        RecipeCard(recipe = recipe, onClick = {

                                        })
                                    }
                                }
                            }

                            CircularIndeterminateProgressBar(isDisplayed = isLoading)

                        }

                    }



//heart animation
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(200.dp),
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        val state = remember { mutableStateOf(IDLE) }
//
//                        AnimatedHeartButton(
//                            modifier = Modifier,
//                            buttonState = state,
//                            onToggle = {
//                                state.value = if (state.value == IDLE) ACTIVE else IDLE
//                            }
//                        )
//                    }


//                    PulsingDemo()
                }
            }
        }
    }
}

@Composable
fun GradientDemo() {
    val colors = listOf(Color.Blue, Color.Red, Color.Blue)
    val brush = linearGradient(
        colors,
        start = Offset(0f, 0f),
        end = Offset(600f, 600f)
    )
    Surface(shape = MaterialTheme.shapes.small) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = brush)
        )
    }


}

















