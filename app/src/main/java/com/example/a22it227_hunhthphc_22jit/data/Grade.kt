package com.example.a22it227_hunhthphc_22jit.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Grade(
    @SerializedName("id") val id: Int,
    @SerializedName("studentId") val studentId: Int,
    @SerializedName("studentName") val studentName: String,
    @SerializedName("regularScore") val regularScore: Float,
    @SerializedName("midtermScore") val midtermScore: Float,
    @SerializedName("finalScore") val finalScore: Float,
    @SerializedName("semester_id") val semester: Int,
    @SerializedName("course_id") val courseId: Int,
) : Serializable {
    val averageScore: Float
        get() = (regularScore + midtermScore + finalScore) / 3
}
