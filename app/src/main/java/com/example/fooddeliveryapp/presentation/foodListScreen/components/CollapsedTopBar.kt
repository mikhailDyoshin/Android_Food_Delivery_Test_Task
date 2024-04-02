package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fooddeliveryapp.common.Resource
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoriesListState
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState

@Composable
fun CollapsedTopBar(
    modifier: Modifier = Modifier,
    categories: Resource<CategoriesListState>,
    isCollapsed: Boolean,
    filterItems: (category: CategoryState) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        Column {
            NavigationBar(modifier = Modifier)
            if (isCollapsed) CategoryBar(
                categories,
                filterItems = { categoryValue -> filterItems(categoryValue) })
        }

    }
}

@Preview
@Composable
fun CollapsedTopBarPreview() {

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

    CollapsedTopBar(categories = categories, isCollapsed = true) {

    }
}