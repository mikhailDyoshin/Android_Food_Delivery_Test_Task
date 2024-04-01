package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState

@Composable
fun CategoryItem(state: CategoryState, onClick: (category: CategoryState) -> Unit) {
    Column(modifier = Modifier.clickable { onClick(state) }) {
        Text(text = state.category)
    }
}