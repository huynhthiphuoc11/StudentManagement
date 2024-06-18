package com.example.a22it227_hunhthphc_22jit.data

import com.google.gson.annotations.SerializedName

data class Semester(
    @SerializedName("semesterId") val semesterId: Int,
    @SerializedName("semesterName") val semesterName: String
)