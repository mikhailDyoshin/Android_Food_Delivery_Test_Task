package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState
import com.example.fooddeliveryapp.ui.theme.CategoryBackgroundColor
import com.example.fooddeliveryapp.ui.theme.CategorySelectedBackgroundColor
import com.example.fooddeliveryapp.ui.theme.CategorySpotColor
import com.example.fooddeliveryapp.ui.theme.categorySelectedTextStyle
import com.example.fooddeliveryapp.ui.theme.categoryTextStyle

@Composable
fun CategoryItem(state: CategoryState, onClick: (category: CategoryState) -> Unit) {

    val categoryShadowElevation = if (state.selected) 0.dp else 10.dp
    val categoryTextStyle = if (state.selected) categorySelectedTextStyle else categoryTextStyle
    val categoryBackgroundColor =
        if (state.selected) CategorySelectedBackgroundColor else CategoryBackgroundColor

    Column(modifier = Modifier
        .clickable { onClick(state) }
        .padding(horizontal = 4.dp, vertical = 2.dp)
        .background(color = categoryBackgroundColor, shape = RoundedCornerShape(size = 6.dp))
        .shadow(
            elevation = categoryShadowElevation,
            spotColor = CategorySpotColor,
            shape = RoundedCornerShape(6.dp)
        )
    ) {
        Text(
            text = state.category,
            fontSize = categoryTextStyle.fontSize,
            fontFamily = categoryTextStyle.fontFamily,
            fontWeight = categoryTextStyle.fontWeight,
            color = categoryTextStyle.color,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        )
    }
}


@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItem(state = CategoryState(category = "Sweets")) {

    }
}

@Preview
@Composable
fun SelectedCategoryItemPreview() {
    CategoryItem(state = CategoryState(category = "Sweets", selected = true)) {

    }
}
