package com.example.fooddeliveryapp.presentation.foodListScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.usecases.GetMealsUseCase
import com.example.fooddeliveryapp.presentation.foodListScreen.state.MealState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(private val getMealsUseCase: GetMealsUseCase) :
    ViewModel() {

    private val _state = mutableStateListOf<MealState>()

    val state: SnapshotStateList<MealState> = _state

    init {
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch {
            _state.addAll(
                getMealsUseCase.execute()
                    .map { MealState(title = it.name, description = it.description) })

        }
    }
}