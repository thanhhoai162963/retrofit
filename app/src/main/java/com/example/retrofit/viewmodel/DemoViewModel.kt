package com.example.retrofit.viewmodel

import androidx.lifecycle.ViewModel
import com.example.retrofit.network.Demo
import com.example.retrofit.network.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

data class ABC(var userId: Int? = null, var title: String? = null)

class DemoViewModel : ViewModel() {

    private var _abc: MutableStateFlow<ABC> = MutableStateFlow(ABC())
    val abc: StateFlow<ABC> = _abc// hot flow
    // 1 nhận biet vong doi
    // 2 emit data cho thằng khởi tạo nó


    private val _events = MutableSharedFlow<Any>()
    val events = _events.asSharedFlow()
    // 1 ko biet vong doi
    // 2 share flow cho cac thang khac
    // 3 tồn tại trên toàn app
    suspend fun callAPI() {
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
    // cach su dung: call api, xu li logic, use database.
    // diem khac biet: khi co data moi emit lien


    fun coldFlow(): Flow<ABC>{
       return flow {
           val request = Network.createRetrofit("https://jsonplaceholder.typicode.com/").create(
               Demo::class.java
           ).getPost(10)
           if (request.isSuccessful) {
               val abc = ABC(request.body()?.userId ?: 0, request.body()?.title ?: "")
               emit(abc)
           }
        }.flowOn(Dispatchers.IO)

        // cach su dung: call api, xu li logic, use database.
        // diem khac biet no chi call 1 lan thoi
    }
}