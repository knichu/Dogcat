package com.godminq.dogcat.api

import com.godminq.dogcat.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TheDogApiService {

    @GET("v1/images/search")
    suspend fun searchImages(
        @Query("id") id: String,
        @Query("url") url: String,
        @Query("width") width: Int,
        @Query("height") height: Int
    ) // 빠진부분 채워넣기(이부분 빠짐)

    companion object {
        private const val BASE_URL = "https://api.thecatdogapi.com/"

        fun create(): TheDogApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheDogApiService::class.java)
        }
    }

}