package com.example.fooddeliveryapp.data.storage

import com.example.fooddeliveryapp.common.Constants.GET_CATEGORIES_PARAM
import com.example.fooddeliveryapp.common.Constants.GET_MEALS_PARAM
import com.example.fooddeliveryapp.data.storage.models.CategoryResponse
import com.example.fooddeliveryapp.data.storage.models.MealsResponse
import retrofit2.http.GET

interface FoodDeliveryAppApi : FoodDeliveryAppStorage {

    @GET(GET_MEALS_PARAM)
    override suspend fun getMeals(): MealsResponse

    @GET(GET_CATEGORIES_PARAM)
    override suspend fun getCategories(): CategoryResponse
}