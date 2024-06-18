package com.example.a22it227_hunhthphc_22jit.service

import com.example.a22it227_hunhthphc_22jit.data.LoginRequest
import com.example.a22it227_hunhthphc_22jit.data.Notification
import com.example.a22it227_hunhthphc_22jit.data.SignUpRequest
import com.example.a22it227_hunhthphc_22jit.data.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("/api/user")
    fun signup(@Body request: SignUpRequest): Call<SignUpRequest>

    @POST("/api/login")
    fun login(@Body request: LoginRequest): Call<LoginRequest>

    @GET("user/{id}") // Đường dẫn URL: http://192.168.1.6:8080/api/user/{id}
    fun getUser(@Path("id") userId: Int): Call<User>


    @GET("notifications")
    fun getNotifications(): List<Notification> // Define Notification class




}
