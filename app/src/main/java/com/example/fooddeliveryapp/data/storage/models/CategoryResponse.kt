package com.example.fooddeliveryapp.data.storage.models

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categories")
    val categories: List<CategoryStorageModel>
)
