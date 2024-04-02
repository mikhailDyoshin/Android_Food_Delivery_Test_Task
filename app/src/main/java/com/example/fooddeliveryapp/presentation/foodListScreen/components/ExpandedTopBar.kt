package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.common.Constants.EXPANDED_TOP_BAR_HEIGHT

@Composable
fun ExpandedTopBar() {

    val tempList = List(2) { 0 }

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT)
            .padding(bottom = 8.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        LazyRow {
            items(tempList) {
                AnnouncementItem()
            }
        }
    }
}

@Preview
@Composable
fun ExpandedTopBarPreview() {
    ExpandedTopBar()
}