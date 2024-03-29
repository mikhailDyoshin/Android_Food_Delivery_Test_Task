package com.example.fooddeliveryapp.data.storage.database.dao

interface DaoInterface<DataType> {

    suspend fun insert(model: DataType): Long

    fun getAll(): List<DataType>

}