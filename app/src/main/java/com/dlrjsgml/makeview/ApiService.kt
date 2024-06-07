package com.dlrjsgml.makeview
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("data")
    fun getItems(): Call<List<AlimData>>
    @GET("grade")
    fun getGrades() : Call<List<GradeData>>
}