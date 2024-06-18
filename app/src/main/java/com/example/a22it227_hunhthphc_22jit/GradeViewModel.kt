package com.example.a22it227_hunhthphc_22jit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a22it227_hunhthphc_22jit.data.Grade
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GradeViewModel : ViewModel() {
    private val _grades = MutableLiveData<List<Grade>?>()
    val grades: LiveData<List<Grade>?> get() = _grades

    private val _grade = MutableLiveData<Grade?>()
    val grade: LiveData<Grade?> get() = _grade

    fun addGrade(grade: Grade) {
        val currentGrades = _grades.value ?: listOf()
        _grades.value = currentGrades + grade
    }

    fun fetchGrades(semesterId: Int, courseId: Int) {
        RetrofitClient.gradeService.getGradesBySemesterAndCourse(semesterId, courseId)
            .enqueue(object : Callback<List<Grade>> {
                override fun onResponse(call: Call<List<Grade>>, response: Response<List<Grade>>) {
                    if (response.isSuccessful) {
                        _grades.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<Grade>>, t: Throwable) {
                    // Handle failure
                }
            })
    }

    fun getGradeById(id: Int): LiveData<Grade?> {
        val gradeLiveData = MutableLiveData<Grade?>()
        RetrofitClient.gradeService.getGradeById(id).enqueue(object : Callback<Grade> {
            override fun onResponse(call: Call<Grade>, response: Response<Grade>) {
                if (response.isSuccessful) {
                    gradeLiveData.value = response.body()
                } else {
                    gradeLiveData.value = null
                }
            }

            override fun onFailure(call: Call<Grade>, t: Throwable) {
                gradeLiveData.value = null
            }
        })
        return gradeLiveData
    }

    fun updateGrade(id: Int, grade: Grade, callback: (Boolean, String) -> Unit) {
        RetrofitClient.gradeService.updateGrade(id, grade).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    val updatedList = _grades.value?.map {
                        if (it.id == id) grade else it
                    }
                    _grades.value = updatedList
                    callback(true, "Grade updated successfully")
                } else {
                    callback(false, "Failed to update grade: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback(false, "Failed to update grade: ${t.message}")
            }
        })
    }
}
