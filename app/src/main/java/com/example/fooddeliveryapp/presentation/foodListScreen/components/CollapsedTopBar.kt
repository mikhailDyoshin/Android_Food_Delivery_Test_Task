package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState

@Composable
fun CollapsedTopBar(
    modifier: Modifier = Modifier,
    categories: List<CategoryState>,
    isCollapsed: Boolean
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        if (isCollapsed) CategoryBar(categories)
    }
}