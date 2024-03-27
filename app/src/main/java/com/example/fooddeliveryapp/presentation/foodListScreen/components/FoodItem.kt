package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryapp.presentation.foodListScreen.state.MealState

@Composable
fun FoodItem(state: MealState) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp, horizontal = 10.dp)) {
        Text(text = state.title)
        Text(text = state.description)
    }
}