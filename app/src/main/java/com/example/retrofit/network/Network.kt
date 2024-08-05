package com.example.retrofit.network

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Network {
        //B1: tạo retrofit , okhttp
     fun createRetrofit(url: String): Retrofit {
        return try {
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor())
                    .build())
                .build()
        } catch (e: Exception) {
            Log.d("error:",e.message.toString())
            throw RuntimeException("Error creating Retrofit instance", e)
        }
    }
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor { message ->
                Log.d("http:",message)
            }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return httpLoggingInterceptor
    }
}
