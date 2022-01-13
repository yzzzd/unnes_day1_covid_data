package com.friend.datacovid.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("positif")
    suspend fun positif(): String

    companion object {
        private val client: OkHttpClient by lazy {
            val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build()
        }

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.kawalcorona.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build()
        }

        fun getInstance() = retrofit.create(ApiService::class.java)
    }
}