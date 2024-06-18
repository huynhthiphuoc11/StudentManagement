package com.example.a22it227_hunhthphc_22jit

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.Attendance
import com.example.a22it227_hunhthphc_22jit.data.AttendanceAdapter
import com.example.a22it227_hunhthphc_22jit.data.AttendanceStatus
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import com.example.a22it227_hunhthphc_22jit.service.AttendanceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AttendanceActivity : AppCompatActivity(), AttendanceUpdateListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var attendanceAdapter: AttendanceAdapter
    private lateinit var viewModel: AttendanceViewModel

    private lateinit var tvTotal: TextView
    private lateinit var tvAbsent: TextView
    private lateinit var tvLate: TextView
    private lateinit var tvPresent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attendance_activity)

        tvTotal = findViewById(R.id.tvTotal)
        tvAbsent = findViewById(R.id.tvAbsent)
        tvLate = findViewById(R.id.tvLate)
        tvPresent = findViewById(R.id.tvPresent)

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        recyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)
        attendanceAdapter = AttendanceAdapter(this, mutableListOf(), this)
        recyclerView.adapter = attendanceAdapter
        val selectedDate = intent.getStringExtra("selected_date")

        // Find the TextView and set the selected date
        val textStudentDate: TextView = findViewById(R.id.textStudentDate)
        textStudentDate.text = selectedDate
        textStudentDate.text = "22JIT|Morning| ${selectedDate}"
        btnBack.setOnClickListener {
            finish()
        }

        viewModel = ViewModelProvider(this).get(AttendanceViewModel::class.java)

        viewModel.attendanceData.observe(this, Observer { attendanceList ->
            attendanceList?.let {
                attendanceAdapter.setData(it)
                updateSummary(it)
            }
        })

        viewModel.fetchAttendanceData(RetrofitClient.getRetrofitInstance().create(AttendanceService::class.java))

        val btnSave: Button = findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
            saveAttendanceData()
        }
    }

    private fun saveAttendanceData() {
        val attendanceData = attendanceAdapter.getData()
        val service = RetrofitClient.getRetrofitInstance().create(AttendanceService::class.java)
        val call = service.saveAttendance(attendanceData)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AttendanceActivity, "Save successful", Toast.LENGTH_SHORT).show()
                    Log.d("AttendanceActivity", "Attendance data saved successfully")
                } else {
                    Log.e("AttendanceActivity", "Failed to save attendance data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("AttendanceActivity", "Error saving attendance data", t)
            }
        })
    }

    private fun updateSummary(attendanceList: List<Attendance>) {
        val total = attendanceList.size
        val absent = attendanceList.count { it.status == AttendanceStatus.ABSENT }
        val late = attendanceList.count { it.status == AttendanceStatus.LATE }
        val present = attendanceList.count { it.status == AttendanceStatus.PRESENT }

        tvTotal.text = "$total"
        tvAbsent.text = "$absent"
        tvLate.text = "$late"
        tvPresent.text = "$present"
    }

    override fun onAttendanceUpdated(attendance: Attendance) {
        viewModel.updateAttendance(attendance)
    }
}
