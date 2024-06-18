
package com.example.a22it227_hunhthphc_22jit
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.a22it227_hunhthphc_22jit.data.Grade
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddGradeActivity : AppCompatActivity() {

    private val TAG = "AddGradeActivity"
    private lateinit var gradeViewModel: GradeViewModel

    private lateinit var btnBack: ImageButton
    private lateinit var btnSubmit: Button
    private lateinit var etStudentName: EditText
    private lateinit var etRegularScore: EditText
    private lateinit var etMidtermScore: EditText
    private lateinit var etFinalScore: EditText
    private lateinit var etStudentId: EditText
    private lateinit var etId: EditText
    private lateinit var spinnerSemester: Spinner
    private lateinit var spinnerCourse: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_grades)

        // Get the shared ViewModel instance
        gradeViewModel = ViewModelProvider(this).get(GradeViewModel::class.java)

        initializeViews()

        btnBack.setOnClickListener {
            Log.d(TAG, "Back button clicked")
            finish()
        }

        btnSubmit.setOnClickListener {
            Log.d(TAG, "Submit button clicked")
            if (validateInputs()) {
                val id = etId.text.toString().toInt()
                val studentName = etStudentName.text.toString()
                val regularScore = etRegularScore.text.toString().toFloat()
                val midtermScore = etMidtermScore.text.toString().toFloat()
                val finalScore = etFinalScore.text.toString().toFloat()
                val studentId = etStudentId.text.toString().toInt()
                val semesterId = spinnerSemester.selectedItemPosition + 1
                val courseId = getCourseId(spinnerCourse.selectedItem.toString())

                val grade = Grade(id, studentId, studentName, regularScore, midtermScore, finalScore, semesterId, courseId)

                Log.d(TAG, "Creating grade: $grade")

                RetrofitClient.gradeService.createGrade(grade).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Log.d(TAG, "Grade added successfully")
                            gradeViewModel.addGrade(grade)
                            runOnUiThread {
                                Toast.makeText(this@AddGradeActivity, "Grade added successfully", Toast.LENGTH_SHORT).show()
                                val resultIntent = Intent().apply {
                                    putExtra("NEW_GRADE", grade)
                                }
                                setResult(Activity.RESULT_OK, resultIntent)
                                finish()
                            }
                        } else {
                            handleErrorResponse(response)
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.e(TAG, "Error: ${t.message}", t)
                        runOnUiThread {
                            Toast.makeText(this@AddGradeActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    private fun initializeViews() {
        btnBack = findViewById(R.id.btn_back)
        btnSubmit = findViewById(R.id.btnSubmit)
        etStudentName = findViewById(R.id.etStudentName)
        etRegularScore = findViewById(R.id.etRegularScore)
        etMidtermScore = findViewById(R.id.etMidtermScore)
        etFinalScore = findViewById(R.id.etFinalScore)
        etStudentId = findViewById(R.id.etStudentId)
        etId = findViewById(R.id.etId)
        spinnerSemester = findViewById(R.id.spinnerSemester)
        spinnerCourse = findViewById(R.id.spinnerCourse)

        val semesterAdapter = ArrayAdapter.createFromResource(
            this, R.array.semesters_array, android.R.layout.simple_spinner_item
        )
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSemester.adapter = semesterAdapter

        val courseAdapter = ArrayAdapter.createFromResource(
            this, R.array.course_array, android.R.layout.simple_spinner_item
        )
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourse.adapter = courseAdapter
    }

    private fun validateInputs(): Boolean {
        val idText = etId.text.toString()
        val studentIdText = etStudentId.text.toString()
        val studentName = etStudentName.text.toString()
        val regularScoreText = etRegularScore.text.toString()
        val midtermScoreText = etMidtermScore.text.toString()
        val finalScoreText = etFinalScore.text.toString()

        if (idText.isBlank() || studentIdText.isBlank() || studentName.isBlank() ||
            regularScoreText.isBlank() || midtermScoreText.isBlank() || finalScoreText.isBlank()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun getCourseId(courseName: String): Int {
        return when (courseName) {
            "Mathematics" -> 1
            "Physics" -> 2
            "Chemistry" -> 3
            else -> 0
        }
    }

    private fun handleErrorResponse(response: Response<Void>) {
        val errorMessage = response.errorBody()?.string() ?: "Unknown error occurred"
        Log.e(TAG, "Failed to add grade: $errorMessage")
        Toast.makeText(this@AddGradeActivity, "Failed to add grade: $errorMessage", Toast.LENGTH_SHORT).show()
    }
}
