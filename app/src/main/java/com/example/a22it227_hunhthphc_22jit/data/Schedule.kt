package com.example.a22it227_hunhthphc_22jit.data

data class Schedule(
    val id: Long,
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String,
    val courseName: String,
    val location: String
)
