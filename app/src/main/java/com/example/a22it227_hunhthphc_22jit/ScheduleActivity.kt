
package com.example.a22it227_hunhthphc_22jit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import android.util.Log
import android.widget.Toast
import com.example.a22it227_hunhthphc_22jit.data.DaySchedule
import com.example.a22it227_hunhthphc_22jit.data.Schedule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleActivity : AppCompatActivity() {

    private lateinit var rvSchedule: RecyclerView
    private lateinit var adapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.thoikhoabieu)

        rvSchedule = findViewById(R.id.rvSchedule)
        rvSchedule.setHasFixedSize(true)
        rvSchedule.layoutManager = LinearLayoutManager(this)

        fetchSchedules()
    }

    private fun fetchSchedules() {
        val call = RetrofitClient.scheduleService.getSchedules()
        call.enqueue(object : Callback<List<Schedule>> {
            override fun onResponse(call: Call<List<Schedule>>, response: Response<List<Schedule>>) {
                if (response.isSuccessful) {
                    val schedules = response.body() ?: return
                    val groupedSchedules = schedules.groupBy { it.dayOfWeek }
                        .map { DaySchedule(it.key, it.value) }
                    adapter = ScheduleAdapter(groupedSchedules)
                    rvSchedule.adapter = adapter
                } else {
                    Toast.makeText(this@ScheduleActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Schedule>>, t: Throwable) {
                Toast.makeText(this@ScheduleActivity, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("ScheduleActivity", t.message ?: "Error")
            }
        })
    }

}
