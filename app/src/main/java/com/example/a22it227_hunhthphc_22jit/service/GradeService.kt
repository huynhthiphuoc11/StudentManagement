package com.example.a22it227_hunhthphc_22jit.service

import com.example.a22it227_hunhthphc_22jit.data.Grade
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface GradeService {
    @GET("grades")  // Path should be relative to BASE_URL
    fun getAllGrades(): Call<List<Grade>>

    @POST("grades/add")  // Path should be relative to BASE_URL
    fun createGrade(@Body grade: Grade): Call<Void>

    @DELETE("grades/{id}")  // Path should be relative to BASE_URL
    fun deleteGrade(@Path("id") id: Int): Call<Void>

    @GET("grades/{id}")  // Path should be relative to BASE_URL
    fun getGradeById(@Path("id") id: Int): Call<Grade>

    @GET("grades/user/{userId}/semesters")
    fun getGradesByUserAndSemesters(
        @Path("userId") userId: Long,
        @Query("semesterNames") semesterNames: List<String>
    ): Call<List<Grade>>

    @PUT("grades/{id}")
    fun updateGrade(@Path("id") id: Int, @Body grade: Grade): Call<Void>

    @GET("api/grades/semester/{semesterId}")
    fun getGradesBySemester(@Path("semesterId") semesterId: Long): Call<List<Grade>>

    @GET("grades/semester/{semesterId}/course/{courseId}")
    fun getGradesBySemesterAndCourse(
        @Path("semesterId") semesterId: Int,
        @Path("courseId") courseId: Int
    ): Call<List<Grade>>
}
