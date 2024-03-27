package com.example.fooddeliveryapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fooddeliveryapp.presentation.foodListScreen.FoodListScreen
import com.example.fooddeliveryapp.presentation.foodListScreen.state.FoodState
import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme

class MainActivity : ComponentActivity() {
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
                        listOf(
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            ),
                            FoodState(
                                title = "Pizza",
                                description = "Tasty very much"
                            )
                        )
                    )
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