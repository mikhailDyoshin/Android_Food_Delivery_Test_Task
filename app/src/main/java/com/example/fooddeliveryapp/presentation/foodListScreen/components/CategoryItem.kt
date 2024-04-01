package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState

@Composable
fun CategoryItem(state: CategoryState, onClick: (category: CategoryState) -> Unit) {
    Column(modifier = Modifier.clickable { onClick(state) }.padding(horizontal = 5.dp, vertical = 2.dp)) {
        Text(text = state.category)
    }
}