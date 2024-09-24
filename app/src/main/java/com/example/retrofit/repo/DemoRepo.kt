package com.example.retrofit.repo

import com.example.retrofit.model.DemoData
import com.example.retrofit.network.Demo
import com.example.retrofit.network.Network
import retrofit2.Response

object DemoRepo {
    suspend fun callAPI(): Response<DemoData> {
        return Network.createRetrofit("https://jsonplaceholder.typicode.com/").create(
            Demo::class.java
        ).getPost(10)
    }
}