package com.example.todlist.Api

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @get:GET("courses")
    val post : Call<List<PostModel?>?>
    companion object {
    val BASE_URL = "https://mockend.com/Mosesteshome/Todolist/"
    }

}