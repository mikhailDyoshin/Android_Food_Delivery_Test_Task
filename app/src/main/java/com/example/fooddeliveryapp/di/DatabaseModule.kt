package com.example.fooddeliveryapp.di

import android.content.Context
import androidx.room.Room
import com.example.fooddeliveryapp.data.storage.database.AppDatabase
import com.example.fooddeliveryapp.data.storage.database.dao.CategoriesDao
import com.example.fooddeliveryapp.data.storage.database.dao.MealsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "AppDatabase",
        ).build()

    @Provides
    fun provideMealsDao(userDatabase: AppDatabase):MealsDao = userDatabase.mealsDao()

    @Provides
    fun provideCategoriesDao(userDatabase: AppDatabase):CategoriesDao = userDatabase.categoriesDao()


}