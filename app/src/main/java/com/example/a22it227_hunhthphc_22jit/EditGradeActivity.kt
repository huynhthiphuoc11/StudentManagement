package com.example.a22it227_hunhthphc_22jit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.a22it227_hunhthphc_22jit.data.Grade

class EditGradeActivity : AppCompatActivity() {

    private val gradeViewModel: GradeViewModel by viewModels()
    private lateinit var etStudentId: EditText
    private lateinit var btnBack: ImageButton
    private lateinit var etStudentName: EditText
    private lateinit var etRegularScore: EditText
    private lateinit var etMidtermScore: EditText
    private lateinit var etFinalScore: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_grades)

        // Initialize views
        btnBack = findViewById(R.id.btn_back)
        etStudentId = findViewById(R.id.et_student_id)
        etStudentName = findViewById(R.id.et_student_name)
        etRegularScore = findViewById(R.id.et_regular_score)
        etMidtermScore = findViewById(R.id.et_midterm_score)
        etFinalScore = findViewById(R.id.et_final_score)
        btnSave = findViewById(R.id.btn_update_grade)

        // Handle back button click
        btnBack.setOnClickListener {
            finish() // Close the current activity and go back to the previous one
        }

        // Get the grade ID from the intent extras
        val gradeId = intent.getIntExtra("GRADE_ID", 0)

        // Load grade details
        gradeViewModel.getGradeById(gradeId).observe(this, Observer { grade ->
            grade?.let {
                etStudentId.setText(it.studentId.toString())
                etStudentName.setText(it.studentName)
                etRegularScore.setText(it.regularScore.toString())
                etMidtermScore.setText(it.midtermScore.toString())
                etFinalScore.setText(it.finalScore.toString())
            }
        })

        // Handle save button click
        btnSave.setOnClickListener {
            if (validateInputs()) {
                val updatedGrade = Grade(
                    id = gradeId,
                    studentId = etStudentId.text.toString().toInt(),
                    studentName = etStudentName.text.toString(),
                    regularScore = etRegularScore.text.toString().toFloat(),
                    midtermScore = etMidtermScore.text.toString().toFloat(),
                    finalScore = etFinalScore.text.toString().toFloat(),
                    semester = 0, // Placeholder value
                    courseId = 0 // Placeholder value
                )

                gradeViewModel.updateGrade(gradeId, updatedGrade) { isSuccess, message ->
                    if (isSuccess) {
                        val resultIntent = Intent().apply {
                            putExtra("UPDATED_GRADE", updatedGrade)
                        }
                        setResult(Activity.RESULT_OK, resultIntent)
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Validate input fields
    private fun validateInputs(): Boolean {
        return etStudentId.text.isNotBlank() &&
                etStudentName.text.isNotBlank() &&
                etRegularScore.text.isNotBlank() &&
                etMidtermScore.text.isNotBlank() &&
                etFinalScore.text.isNotBlank()
    }
}
