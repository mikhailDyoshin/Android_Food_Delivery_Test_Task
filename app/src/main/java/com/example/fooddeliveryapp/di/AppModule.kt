package com.example.fooddeliveryapp.di

import com.example.fooddeliveryapp.common.BASE_URL
import com.example.fooddeliveryapp.data.repository.FoodDeliveryAppRepositoryImpl
import com.example.fooddeliveryapp.data.storage.FoodDeliveryAppApi
import com.example.fooddeliveryapp.domain.repository.FoodDeliveryAppRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFoodDeliveryAppApi(): FoodDeliveryAppApi {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(FoodDeliveryAppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFoodDeliveryAppRepository(
        api: FoodDeliveryAppApi
    ): FoodDeliveryAppRepository {
        return FoodDeliveryAppRepositoryImpl(api)
    }

}