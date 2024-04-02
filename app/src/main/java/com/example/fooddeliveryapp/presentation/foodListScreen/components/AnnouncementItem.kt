package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnnouncementItem() {
    Column(Modifier.padding(start = 16.dp)) {
        Column(
            Modifier
                .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .width(300.dp)
                .height(112.dp)
        ) {

        }
    }

}