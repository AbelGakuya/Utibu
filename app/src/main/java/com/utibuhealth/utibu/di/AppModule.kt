package com.utibuhealth.utibu.di

import com.utibuhealth.utibu.common.Constants.BASE_URL
import com.utibuhealth.utibu.data.remote.UtibuApi
import com.utibuhealth.utibu.data.repository.UtibuRepositoryImpl
import com.utibuhealth.utibu.domain.repository.UtibuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUtibuApi(): UtibuApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UtibuApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUtibuRepository(api: UtibuApi): UtibuRepository {
        return UtibuRepositoryImpl(api)
    }
}