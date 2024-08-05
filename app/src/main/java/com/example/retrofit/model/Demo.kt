package com.example.retrofit.model

import com.google.gson.annotations.SerializedName

//B2: tạo class để hứng data

class DemoData {
    @SerializedName("userId")
    val userId:Int? = null

    @SerializedName("id")
    val id:Int? = null

    @SerializedName("title")
    val title:String? = null

    @SerializedName("body")
    val body:String? = null

}