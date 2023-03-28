package com.godminq.dogcat.di

import android.util.Log
import com.godminq.dogcat.api.TheCatApiService
import com.godminq.dogcat.api.TheDogApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideTheDogApiService(): TheDogApiService {
        Log.d("태그", "NetworkModule1")
        return TheDogApiService.create()
    }

    @Singleton
    @Provides
    fun provideTheCatApiService(): TheCatApiService {
        return TheCatApiService.create()
    }
}
