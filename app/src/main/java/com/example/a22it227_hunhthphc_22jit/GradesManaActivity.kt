package com.example.a22it227_hunhthphc_22jit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GradesManaActivity : AppCompatActivity() {

    private lateinit var spinnerlop: Spinner
    private lateinit var spinnerHocKy: Spinner
    private lateinit var spinnerCourse: Spinner
    private lateinit var btnHuy: Button
    private lateinit var btnThucHien: Button
    private lateinit var btnBack: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grades_mana)

        // Initialize the views
        spinnerlop = findViewById(R.id.spinnerClass)
        spinnerHocKy = findViewById(R.id.spinnerSemester)
        spinnerCourse = findViewById(R.id.spinnerCourse)

        btnHuy = findViewById(R.id.btnCancel)
        btnThucHien = findViewById(R.id.btnNext)
        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Close the current activity and go back to the previous one
        }

        // Set up the adapters
        val khoiAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.class_array, android.R.layout.simple_spinner_item
        )
        khoiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerlop.adapter = khoiAdapter

        val hocKyAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.semesters_array, android.R.layout.simple_spinner_item
        )
        hocKyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerHocKy.adapter = hocKyAdapter

        val courseAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.course_array, android.R.layout.simple_spinner_item
        )
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourse.adapter = courseAdapter

        // Set up listeners
        spinnerlop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Handle class selection
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case when nothing is selected
            }
        }

        spinnerHocKy.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Handle semester selection
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case when nothing is selected
            }
        }

        spinnerCourse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Handle course selection
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case when nothing is selected
            }
        }


        btnHuy.setOnClickListener {
            // Handle cancel button click
            Toast.makeText(this@GradesManaActivity, "Cancelled", Toast.LENGTH_SHORT).show()
        }

        btnThucHien.setOnClickListener {
            val selectedClass = spinnerlop.selectedItem.toString()
            val selectedHocKy = spinnerHocKy.selectedItemPosition + 1 // Assuming position 0 is semesterId 1
            val selectedCourse = spinnerCourse.selectedItemPosition + 1 // Assuming position 0 is courseId 1

            // Implement your logic here to handle the selected values
            Toast.makeText(
                this@GradesManaActivity,
                "Processing: $selectedClass, $selectedHocKy, $selectedCourse",
                Toast.LENGTH_LONG
            ).show()

            // Create an intent to start GradesActivity1
            val intent = Intent(this@GradesManaActivity, GradesActivity1::class.java).apply {
                putExtra("SELECTED_CLASS", selectedClass)
                putExtra("SELECTED_HOCKY", selectedHocKy)
                putExtra("SELECTED_COURSE", selectedCourse)
            }
            startActivity(intent)
        }

    }
}
