package com.example.retrofit.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class HomeState(
    var name: MutableState<String> = mutableStateOf(""),
    var name1: MutableState<String> = mutableStateOf(""),
    var name2: MutableState<String> = mutableStateOf(""),
    var name3: MutableState<String> = mutableStateOf(""),
    var name4: MutableState<String> = mutableStateOf(""),
    var count: MutableState<Int> = mutableIntStateOf(0)
){
    fun increaseCount(){
        count.value++
    }

    fun result(): Boolean{
       return if (count.value > 5){
            true
        }else{
            false
        }
    }
}

@Composable
fun rememberHomeState() : HomeState{
    return remember {
        HomeState()
    }
}