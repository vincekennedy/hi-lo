package com.hi_lo.data.service

import com.hi_lo.data.Course

interface CourseService {
    fun getCourses(): List<Course>
}