package com.dlrjsgml.makeview
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("data")
    fun getItems(): Call<List<AlimData>>
    @GET("grade")
    fun getGrades() : Call<List<GradeData>>

    @POST("add_data")
    fun addItems(@Body post: Post): Call<Void>
}