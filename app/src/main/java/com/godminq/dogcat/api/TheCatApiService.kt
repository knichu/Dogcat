package com.godminq.dogcat.api

import com.godminq.dogcat.data.entity.TheCatApiSearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

val THE_CAT_API_ACCESS_KEY = "live_pLEpaOnZA1lVZGWePOYvrNNlJzB5fCb5oS6hsM6dR5GQhM96nJmGY13KRW5uoKMz"

interface TheCatApiService {

    @GET("v1/images/search")
    suspend fun searchImages(
        @Query("limit") limit: Int,
        @Query("mime_types") mimeTypes: String,
        @Query("page") page: Int,
//        @Query("api_key") apiKey: String = THE_CAT_API_ACCESS_KEY
    ): List<TheCatApiSearchResponse>

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/"

        fun create(): TheCatApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheCatApiService::class.java)
        }
    }

}