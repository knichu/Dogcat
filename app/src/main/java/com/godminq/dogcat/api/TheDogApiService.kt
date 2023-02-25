package com.godminq.dogcat.api

import android.util.Log
import com.godminq.dogcat.data.entity.TheDogApiSearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

val THE_DOG_API_ACCESS_KEY = "live_gFsvDwUEj0oILkCZFrMxJ4jzRd6XNkhns21NKNBsp31HoJCi4K3LuLpHhGQQhvTx"

interface TheDogApiService {

    @GET("v1/images/search")
    suspend fun searchImages(
        @Query("limit") limit: Int,
        @Query("mime_types") mimeTypes: String,
        @Query("page") page: Int,
//        @Query("api_key") apiKey: String = THE_DOG_API_ACCESS_KEY
    ): List<TheDogApiSearchResponse>

    companion object {
        private const val BASE_URL = "https://api.thedogapi.com/"

        fun create(): TheDogApiService {
            Log.d("태그", "TheDogApiService create1")
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            Log.d("태그", "TheDogApiService create2")
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            Log.d("태그", "TheDogApiService create3")
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheDogApiService::class.java)
        }
    }

}