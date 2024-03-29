package com.example.fooddeliveryapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fooddeliveryapp.presentation.customToolbar.CustomToolbarDemo
import com.example.fooddeliveryapp.presentation.foodListScreen.FoodListScreen
import com.example.fooddeliveryapp.presentation.foodListScreen.viewModel.FoodListViewModel
import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: FoodListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FoodListScreen(
                        mealsListState = viewModel.mealsListState.value,
                        categoriesState = viewModel.categoriesListState.value
                    )
//                    CustomToolbarDemo()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodDeliveryAppTheme {
        Greeting("Android")
    }
}