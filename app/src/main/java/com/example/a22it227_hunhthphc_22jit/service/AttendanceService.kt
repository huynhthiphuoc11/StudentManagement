package com.example.a22it227_hunhthphc_22jit.service

import com.example.a22it227_hunhthphc_22jit.data.Attendance
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AttendanceService {
    @GET("attendance")
    fun getAttendance(): Call<List<Attendance>>
    @POST("attendance/batch")
    fun saveAttendance(@Body attendanceList: List<Attendance>): Call<Void>
}
