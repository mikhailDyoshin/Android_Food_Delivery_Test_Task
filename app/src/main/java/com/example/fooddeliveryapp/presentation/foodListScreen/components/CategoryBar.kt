package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoriesListState
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState
import com.example.fooddeliveryapp.ui.theme.CategoriesBarBackgroundColor

@Composable
fun CategoryBar(
    categories: Resource<CategoriesListState>,
    filterItems: (category: CategoryState) -> Unit
) {
    val selectedCategoryIndex = categories.data?.selectedCategoryIndex ?: 0

    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = selectedCategoryIndex)
    val selectedCategoryState = remember {
        mutableStateOf(CategoryState())
    }

    LaunchedEffect(selectedCategoryIndex) {

        lazyListState.scrollToItem(selectedCategoryIndex)

    }

    LazyRow(
        state = lazyListState,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = CategoriesBarBackgroundColor)
            .padding(all = 16.dp)
    ) {
        when (categories.status) {
            Resource.Status.LOADING -> {

            }

            Resource.Status.SUCCESS -> {
                items(categories.data?.categoriesList ?: emptyList()) { category ->
                    CategoryItem(
                        category,
                        onClick = { categoryValue ->
                            selectedCategoryState.value = categoryValue
                            filterItems(categoryValue)
                        })
                }
            }

            Resource.Status.ERROR -> {

            }
        }

    }
}

@Preview
@Composable
fun CategoryBarPreview() {
    val categories = Resource.success(
        data = CategoriesListState(
            categoriesList = listOf(
                CategoryState(category = "Sweets", selected = true),
                CategoryState(category = "Meet", selected = false),
                CategoryState(category = "Salads", selected = false),
                CategoryState(category = "Drinks", selected = false),
            ), selectedCategoryIndex = 0
        )
    )
    CategoryBar(categories = categories) {

    }
}
