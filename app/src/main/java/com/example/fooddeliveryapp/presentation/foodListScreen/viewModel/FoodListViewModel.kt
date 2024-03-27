package com.example.fooddeliveryapp.presentation.foodListScreen.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.usecases.GetCategoriesUseCase
import com.example.fooddeliveryapp.domain.usecases.GetMealsUseCase
import com.example.fooddeliveryapp.presentation.foodListScreen.state.CategoryState
import com.example.fooddeliveryapp.presentation.foodListScreen.state.MealState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) :
    ViewModel() {

    private val _mealsListState = mutableStateListOf<MealState>()
    private val _categoriesListState = mutableStateListOf<CategoryState>()

    val mealsListState: SnapshotStateList<MealState> = _mealsListState
    val categoriesListState: SnapshotStateList<CategoryState> = _categoriesListState

    init {
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch {
            _mealsListState.addAll(
                getMealsUseCase.execute()
                    .map { MealState(title = it.name, description = it.description) })

            _categoriesListState.addAll(
                getCategoriesUseCase.execute()
                    .map { CategoryState(category = it.category) }
            )
        }
    }
}