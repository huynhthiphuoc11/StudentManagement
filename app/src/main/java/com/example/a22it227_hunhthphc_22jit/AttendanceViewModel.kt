package com.example.a22it227_hunhthphc_22jit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a22it227_hunhthphc_22jit.data.Attendance
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import com.example.a22it227_hunhthphc_22jit.service.AttendanceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AttendanceViewModel : ViewModel() {
    private val _attendanceData = MutableLiveData<List<Attendance>>()
    val attendanceData: LiveData<List<Attendance>> get() = _attendanceData

    fun fetchAttendanceData(service: AttendanceService) {
        service.getAttendance().enqueue(object : Callback<List<Attendance>> {
            override fun onResponse(call: Call<List<Attendance>>, response: Response<List<Attendance>>) {
                if (response.isSuccessful) {
                    _attendanceData.postValue(response.body() ?: emptyList())
                } else {
                    _attendanceData.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<Attendance>>, t: Throwable) {
                _attendanceData.postValue(emptyList())
            }
        })
    }

    fun updateAttendance(attendance: Attendance) {
        val currentList = _attendanceData.value?.toMutableList() ?: mutableListOf()
        val index = currentList.indexOfFirst { it.studentId == attendance.studentId }
        if (index != -1) {
            currentList[index] = attendance
            _attendanceData.postValue(currentList)
        }
    }
}
