package com.hi_lo.data.retrofit

import com.hi_lo.data.retrofit.service.CourseService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:3000"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    val courseService: CourseService by lazy {
        RetrofitClient.retrofit.create(CourseService::class.java)
    }
}