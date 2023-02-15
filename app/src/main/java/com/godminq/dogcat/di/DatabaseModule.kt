package com.godminq.dogcat.di

import android.content.Context
import android.util.Log
import com.godminq.dogcat.data.AppDatabase
import com.godminq.dogcat.data.dao.CatDao
import com.godminq.dogcat.data.dao.DogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        Log.d("태그", "SSSS")
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideDogDao(appDatabase: AppDatabase): DogDao {
        return appDatabase.dogDao()
    }

    @Provides
    fun provideCatDao(appDatabase: AppDatabase): CatDao {
        return appDatabase.catDao()
    }
}