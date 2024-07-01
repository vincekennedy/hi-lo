package com.hi_lo.data.retrofit.service

import com.hi_lo.data.Course
import retrofit2.http.GET

interface CourseService {

    @GET("courses.json")
    suspend fun getCourses(): List<Course>
}