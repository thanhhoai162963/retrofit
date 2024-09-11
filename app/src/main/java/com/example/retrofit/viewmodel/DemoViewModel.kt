package com.example.retrofit.viewmodel

import androidx.lifecycle.ViewModel
import com.example.retrofit.network.Demo
import com.example.retrofit.network.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ABC(var userId: Int? = null, var title: String? = null)

class DemoViewModel : ViewModel() {

    private var _abc: MutableStateFlow<ABC> = MutableStateFlow(ABC())
    val abc: StateFlow<ABC> = _abc


    fun callAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = Network.createRetrofit("https://jsonplaceholder.typicode.com/").create(
                Demo::class.java
            ).getPost(10)
            if (request.isSuccessful) {
                val abc = ABC(request.body()?.userId ?: 0, request.body()?.title ?: "")
                _abc.value = abc
            }

        }
    }
}