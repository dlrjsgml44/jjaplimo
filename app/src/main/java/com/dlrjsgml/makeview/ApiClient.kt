package com.dlrjsgml.makeview

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://192.168.123.119:5000"
    private var retrofit: Retrofit? = null

    val instance: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
}
