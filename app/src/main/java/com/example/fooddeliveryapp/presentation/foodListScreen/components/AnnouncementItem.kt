package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun AnnouncementItem(imageId: Int, modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(start = 16.dp)
            .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
            .height(112.dp)
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = "Food image",
            modifier = Modifier.fillMaxSize().clip(shape = RoundedCornerShape(10.dp)),
        )

    }

}