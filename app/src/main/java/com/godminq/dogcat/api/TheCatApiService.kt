package com.godminq.dogcat.api

import android.util.Log
import com.godminq.dogcat.BuildConfig
import com.godminq.dogcat.data.entity.TheCatApiSearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCatApiService {

    @GET("v1/images/search")
    suspend fun searchImages(
        @Query("limit") limit: Int,
        @Query("mime_types") mimeTypes: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.THE_CAT_API_ACCESS_KEY
    ): List<TheCatApiSearchResponse>

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/"

        fun create(): TheCatApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
//                .addInterceptor { chain ->
//                    val originalRequest = chain.request()
//                    val url = originalRequest.url.newBuilder()
//                        .addQueryParameter("limit", originalRequest.url.queryParameter("limit"))
//                        .addQueryParameter("mime_types", originalRequest.url.queryParameter("mime_types"))
//                        .addQueryParameter("page", originalRequest.url.queryParameter("page"))
//                        .addQueryParameter("api_key", originalRequest.url.queryParameter("api_key"))
//                        .build()
//                    val requestBuilder = originalRequest.newBuilder().url(url)
//                    Log.d("태그", "searchImages()_cat: limit=${originalRequest.url.queryParameter("limit")}," +
//                            " mimeTypes=${originalRequest.url.queryParameter("mime_types")}," +
//                            " page=${originalRequest.url.queryParameter("page")}," +
//                            " apiKey=${originalRequest.url.queryParameter("api_key")}")
//                    chain.proceed(requestBuilder.build())
//                }
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