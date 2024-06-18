package com.example.a22it227_hunhthphc_22jit.service
import com.example.a22it227_hunhthphc_22jit.data.Classes
import retrofit2.Call
import retrofit2.http.*

interface ClassesApi {
    @GET("/api/classes")
    fun getAllClasses(): Call<List<Classes>>

    @POST("/api/classes/add")
    fun createClass(@Body classes: Classes): Call<Classes>

    @GET("/api/classes/{id}")
    fun getClassById(@Path("id") id: Long): Call<Classes>

    @PUT("/api/classes/{id}")
    fun updateClass(@Path("id") id: Int, @Body classes: Classes): Call<Classes>

    @DELETE("/api/classes/{id}")
    fun deleteClass(@Path("id") id: Int): Call<Void>
}
