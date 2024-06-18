package com.example.a22it227_hunhthphc_22jit.service


import com.example.a22it227_hunhthphc_22jit.data.Schedule
import retrofit2.Call
import retrofit2.http.GET

interface ScheduleService {
    @GET("schedules")
    fun getSchedules(): Call<List<Schedule>>
}
