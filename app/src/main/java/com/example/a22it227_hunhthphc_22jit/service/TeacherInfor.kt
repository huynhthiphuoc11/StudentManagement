package com.example.a22it227_hunhthphc_22jit.service

import com.example.horizonrecyclerview.StudentInfo
import retrofit2.http.GET

interface TeacherInfor {
    @GET("api/user/1")
    suspend fun getStudentInfo(): List<StudentInfo>
}