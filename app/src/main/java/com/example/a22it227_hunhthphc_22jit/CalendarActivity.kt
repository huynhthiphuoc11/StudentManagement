// CalendarActivity.kt
package com.example.a22it227_hunhthphc_22jit

import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class CalendarActivity : AppCompatActivity() {

    private lateinit var datePicker: DatePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)

        datePicker = findViewById(R.id.datePicker)

        // Get the current date
        val calendar = Calendar.getInstance()
        val year = 2024 // Set the year 2024
        val month = calendar.get(Calendar.MONTH) // Current month
        val day = calendar.get(Calendar.DAY_OF_MONTH) // Current day

        // Set the initial date for the DatePicker
        datePicker.updateDate(year, month, day)

        // Listen for date changes
        datePicker.init(year, month, day) { _, year, monthOfYear, dayOfMonth ->
            val date = "$dayOfMonth/${monthOfYear + 1}/$year"
            Toast.makeText(this@CalendarActivity, "Selected date: $date", Toast.LENGTH_SHORT).show()

            // Intent to navigate to AttendanceActivity
            val intent = Intent(this@CalendarActivity, AttendanceActivity::class.java)
            intent.putExtra("selected_date", date)
            startActivity(intent)
        }
    }
}
