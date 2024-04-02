package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.presentation.foodListScreen.state.MealState
import com.example.fooddeliveryapp.ui.theme.MealDescriptionTextColor
import com.example.fooddeliveryapp.ui.theme.MealTitleTextColor

@Composable
fun FoodItem(state: MealState, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = state.imageUrl,
                    contentDescription = "Meal image",
                    modifier = Modifier
                        .width(135.dp)
                        .height(135.dp)
                        .background(color = Color.Gray)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(135.dp)
                ) {
                    Text(
                        text = state.title,
                        color = MealTitleTextColor,
                        fontSize = 16.sp,
                        fontStyle = FontStyle(R.font.roboto_bold),
                        fontWeight = FontWeight(700),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = state.description,
                        color = MealDescriptionTextColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = FontFamily.SansSerif,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }

}

@Preview
@Composable
fun FoodItemPreview() {
    FoodItem(
        state = MealState(
            title = "Food",
            description = "Delicious food. For every day for life. For you and every one else. Eat and feel good",
            imageUrl = ""
        )
    )
}

@Preview
@Composable
fun FoodItemLastPreview() {
    FoodItem(
        state = MealState(
            title = "Food",
            description = "Delicious food. For every day for life. For you and every one else. Eat and feel good",
            imageUrl = ""
        ),
        modifier = Modifier.padding(bottom = 40.dp)
    )
}