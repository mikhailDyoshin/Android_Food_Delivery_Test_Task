package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState

@Composable
fun CategoryBar(categories: List<CategoryState>) {
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(color = Color.Gray)) {
        items(categories) {
            category ->
            Text(text = category.category, modifier = Modifier.padding(horizontal = 5.dp))
        }
    }
}