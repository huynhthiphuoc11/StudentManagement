package com.example.a22it227_hunhthphc_22jit.retrofit

import com.example.a22it227_hunhthphc_22jit.service.ClassesApi
import com.example.a22it227_hunhthphc_22jit.service.GradeService
import com.example.a22it227_hunhthphc_22jit.service.ScheduleService
import com.example.a22it227_hunhthphc_22jit.service.UserService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "http://192.168.1.3:8080/api/"  // Ensure BASE_URL does not include "/api/"
    private val gson: Gson = GsonBuilder()
        .setDateFormat("MMMM d, yyyy hh:mm:ss a")
        .create()

    fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }

    val gradeService: GradeService by lazy {
        getRetrofitInstance().create(GradeService::class.java)
    }

    val classService: ClassesApi by lazy {
        getRetrofitInstance().create(ClassesApi::class.java)
    }
    val scheduleService: ScheduleService by lazy {
        getRetrofitInstance().create(ScheduleService::class.java)
    }

    val userService: UserService by lazy {
        getRetrofitInstance().create(UserService::class.java)
    }

    val instance: ClassesApi by lazy {
        getRetrofitInstance().create(ClassesApi::class.java)
    }
}
