package com.example.fooddeliveryapp.presentation.foodListScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.presentation.foodListScreen.state.TabBarButtonState
import com.example.fooddeliveryapp.ui.theme.TabBarActiveButtonIconColor
import com.example.fooddeliveryapp.ui.theme.TabBarActiveButtonTextColor
import com.example.fooddeliveryapp.ui.theme.TabBarButtonIconColor
import com.example.fooddeliveryapp.ui.theme.TabBarButtonTextColor

@Composable
fun TabBarButton(state: TabBarButtonState) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(top = 5.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(state.iconId),
            contentDescription = "Button icon",
            tint = if (state.active)  TabBarActiveButtonIconColor else TabBarButtonIconColor
        )
        Text(
            text = state.text,
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            color = if (state.active) TabBarActiveButtonTextColor else TabBarButtonTextColor,
            fontStyle = FontStyle(R.font.pt_sans_caption_regular)
        )
    }
}

@Preview
@Composable
fun TabBarButtonPreview() {
    val state = TabBarButtonState(R.drawable.profile_icon, "Profile", active = false)

    TabBarButton(state)
}

@Preview
@Composable
fun TabBarActiveButtonPreview() {
    val state = TabBarButtonState(R.drawable.profile_icon, "Profile", active = true)

    TabBarButton(state)
}