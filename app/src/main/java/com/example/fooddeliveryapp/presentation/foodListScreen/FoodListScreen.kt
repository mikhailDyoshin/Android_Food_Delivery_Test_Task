package com.example.fooddeliveryapp.presentation.foodListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.fooddeliveryapp.common.Constants.COLLAPSED_TOP_BAR_HEIGHT
import com.example.fooddeliveryapp.common.Constants.EXPANDED_TOP_BAR_HEIGHT
import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.presentation.foodListScreen.components.CategoryBar
import com.example.fooddeliveryapp.presentation.foodListScreen.components.ExpandedTopBar
import com.example.fooddeliveryapp.presentation.foodListScreen.components.CollapsedTopBar
import com.example.fooddeliveryapp.presentation.foodListScreen.components.FoodItem
import com.example.fooddeliveryapp.presentation.foodListScreen.components.TabBar
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoriesListState
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState
import com.example.fooddeliveryapp.presentation.foodListScreen.state.MealState
import com.example.fooddeliveryapp.ui.theme.MealsListBackgroundColor
import com.example.fooddeliveryapp.ui.theme.SpacerColor

@Composable
fun FoodListScreen(
    mealsListState: Resource<List<MealState>>,
    categoriesState: Resource<CategoriesListState>,
    filterItems: (category: CategoryState) -> Unit
) {
    val listState = rememberLazyListState()
    val overlapHeightPx = with(LocalDensity.current) {
        EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx()
    }
    val isCollapsed = remember {
        derivedStateOf {
            val isFirstItemHidden =
                listState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || listState.firstVisibleItemIndex > 0
        }
    }
    Box {
        CollapsedTopBar(
            modifier = Modifier.zIndex(2f),
            categories = categoriesState,
            isCollapsed = isCollapsed.value,
            filterItems = { categoryValue -> filterItems(categoryValue) }
        )
        LazyColumn(
            state = listState,
            modifier = Modifier.background(color = MealsListBackgroundColor)
        ) {
            item { ExpandedTopBar() }
            item {
                CategoryBar(
                    categoriesState,
                    filterItems = { categoryValue -> filterItems(categoryValue) })
            }
            when (mealsListState.status) {
                Resource.Status.LOADING -> {
                    item { Text(text = "Loading...") }
                }

                Resource.Status.SUCCESS -> {
                    items(items = mealsListState.data ?: emptyList()) { food ->
                        Spacer(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(color = SpacerColor)
                        )
                        if (food == mealsListState.data?.last()) {
                            FoodItem(state = food, modifier = Modifier.padding(bottom = 60.dp))
                        }
                        else {
                            FoodItem(state = food)
                        }
                    }
                }

                Resource.Status.ERROR -> {
                    item { Text(text = "Error") }
                }
            }

        }
        TabBar(modifier = Modifier.zIndex(2f).align(Alignment.BottomCenter))
    }
}

@Preview
@Composable
fun FoodListScreenPreview() {

    val mealsListState = Resource.success(
        data = listOf(
            MealState(title = "Tiramisu", description = "Yummy", imageUrl = ""),
            MealState(title = "Tiramisu", description = "Yummy", imageUrl = ""),
            MealState(title = "Tiramisu", description = "Yummy", imageUrl = ""),
            MealState(title = "Tiramisu", description = "Yummy", imageUrl = ""),
        )
    )

    val categoriesListState = Resource.success(
        data = CategoriesListState(
            categoriesList = listOf(
                CategoryState(category = "Sweets", selected = true),
                CategoryState(category = "Meet", selected = false),
                CategoryState(category = "Salads", selected = false),
                CategoryState(category = "Drinks", selected = false),
            ), selectedCategoryIndex = 0
        )
    )

    FoodListScreen(mealsListState = mealsListState, categoriesState = categoriesListState) {

    }
}