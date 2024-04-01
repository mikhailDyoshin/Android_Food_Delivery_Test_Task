package com.example.fooddeliveryapp.presentation.foodListScreen.state

data class CategoriesListState(
    val categoriesList: List<CategoryState> = emptyList(),
    val selectedCategoryIndex: Int = 0
)
