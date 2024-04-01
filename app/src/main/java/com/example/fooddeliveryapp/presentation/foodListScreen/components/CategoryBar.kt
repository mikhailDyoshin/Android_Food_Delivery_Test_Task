package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState

@Composable
fun CategoryBar(
    categories: Resource<List<CategoryState>>,
    filterItems: (category: CategoryState) -> Unit
) {

    val lazyListState = rememberLazyListState()
    val selectedCategoryState = remember {
        mutableStateOf(CategoryState(category = ""))
    }

    LaunchedEffect(selectedCategoryState.value) {
        val selectedCategoryIndex = categories.data?.indexOf(selectedCategoryState.value) ?: 0
        if (selectedCategoryIndex >= 0) {
            lazyListState.scrollToItem(selectedCategoryIndex)
        }
    }

    LazyRow(
        state = lazyListState,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color = Color.Gray)
    ) {
        when (categories.status) {
            Resource.Status.LOADING -> {

            }

            Resource.Status.SUCCESS -> {
                items(categories.data ?: emptyList()) { category ->
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