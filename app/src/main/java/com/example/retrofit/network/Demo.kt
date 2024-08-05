package com.example.retrofit.network

import com.example.retrofit.model.DemoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//B2: tạo interface để giả lập method post,get...

interface Demo {

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Response<DemoData>
}