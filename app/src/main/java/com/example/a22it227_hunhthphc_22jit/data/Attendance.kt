package com.example.a22it227_hunhthphc_22jit.data


import java.io.Serializable
import java.util.Date


data class Attendance(
    val id: Int,
    val studentId: Int,
    val student_name: String,
    val attendanceDate: Date,
    var status: AttendanceStatus =  AttendanceStatus.PRESENT
) : Serializable
