package com.example.fooddeliveryapp.common

import androidx.compose.ui.unit.dp

object Constants {
    val COLLAPSED_TOP_BAR_HEIGHT = 80.dp
    val EXPANDED_TOP_BAR_HEIGHT = 152.dp + COLLAPSED_TOP_BAR_HEIGHT

    const val BASE_URL = "https://themealdb.com/api/json/v1/1/"
    const val GET_MEALS_PARAM = "search.php?s"
    const val GET_CATEGORIES_PARAM = "categories.php"
}

