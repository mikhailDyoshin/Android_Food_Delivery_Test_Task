package com.example.fooddeliveryapp.presentation.foodListScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.fooddeliveryapp.common.COLLAPSED_TOP_BAR_HEIGHT
import com.example.fooddeliveryapp.common.EXPANDED_TOP_BAR_HEIGHT
import com.example.fooddeliveryapp.presentation.foodListScreen.components.CategoryBar
import com.example.fooddeliveryapp.presentation.foodListScreen.components.ExpandedTopBar
import com.example.fooddeliveryapp.presentation.foodListScreen.components.CollapsedTopBar
import com.example.fooddeliveryapp.presentation.foodListScreen.components.FoodItem
import com.example.fooddeliveryapp.presentation.foodListScreen.state.MealState

@Composable
fun FoodListScreen(layoutState: List<MealState>) {
    val listState = rememberLazyListState()
    val overlapHeightPx = with(LocalDensity.current) {
        EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx()
    }
    val isCollapsed = remember {
        derivedStateOf {
            val isFirstItemHidden =
                listState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || listState.firstVisibleItemIndex > 0
        }
    }
    Box {
        CollapsedTopBar(modifier = Modifier.zIndex(2f), isCollapsed = isCollapsed.value)
        LazyColumn(state = listState) {
            item { ExpandedTopBar() }
            item { CategoryBar() }
            items(items = layoutState) { food ->
                FoodItem(state = food)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
