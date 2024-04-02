package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.presentation.foodListScreen.state.TabBarButtonState
import com.example.fooddeliveryapp.ui.theme.TabBarBackgroundColor

@Composable
fun TabBar() {

    val menuButtonsStates = listOf(
        TabBarButtonState(iconId = R.drawable.menu_icon, text = "Menu", active = true),
        TabBarButtonState(iconId = R.drawable.profile_icon, text = "Profile", active = false),
        TabBarButtonState(iconId = R.drawable.buscket_icon, text = "Cart", active = false),
    )

    Row(
        modifier = Modifier.fillMaxWidth().background(color = TabBarBackgroundColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        menuButtonsStates.forEach {
            TabBarButton(it)
        }
    }
}

@Preview
@Composable
fun TabBarPreview() {
    TabBar()
}