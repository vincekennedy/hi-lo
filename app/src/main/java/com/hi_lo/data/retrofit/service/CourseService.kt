package com.hi_lo.data.retrofit.service

import com.hi_lo.data.Course
import retrofit2.http.GET

interface CourseService {

    @GET("api/v1/courses")
    suspend fun getCourses(): List<Course>
}