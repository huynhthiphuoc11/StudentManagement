package com.example.a22it227_hunhthphc_22jit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a22it227_hunhthphc_22jit.data.Classes
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddClassActivity : AppCompatActivity() {

    private val TAG = "AddClassActivity"

    private lateinit var btnBack: ImageButton
    private lateinit var btnSubmit: Button
    private lateinit var etClassName: EditText
    private lateinit var etTotal: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_class)

        // Initialize the views
        btnBack = findViewById(R.id.btn_back)
        btnSubmit = findViewById(R.id.btnSubmit)
        etClassName = findViewById(R.id.etClassName)
        etTotal = findViewById(R.id.etTotal)

        btnBack.setOnClickListener {
            Log.d(TAG, "Back button clicked")
            finish()
        }

        btnSubmit.setOnClickListener {
            Log.d(TAG, "Submit button clicked")
            if (validateInputs()) {
                val className = etClassName.text.toString()
                val total = etTotal.text.toString().toInt()

                val classData = Classes(id = 0, className = className, total = total)

                Log.d(TAG, "Creating class: $classData")

                RetrofitClient.classService.createClass(classData).enqueue(object : Callback<Classes> {
                    override fun onResponse(call: Call<Classes>, response: Response<Classes>) {
                        if (response.isSuccessful) {
                            Log.d(TAG, "Class added successfully")

                            runOnUiThread {
                                Toast.makeText(this@AddClassActivity, "Class added successfully", Toast.LENGTH_SHORT).show()
                                val resultIntent = Intent()
                                resultIntent.putExtra("newClass", classData)
                                setResult(Activity.RESULT_OK, resultIntent)
                                finish()
                            }
                        } else {
                            handleErrorResponse(response)
                        }
                    }

                    override fun onFailure(call: Call<Classes>, t: Throwable) {
                        Log.e(TAG, "Error: ${t.message}", t)
                        runOnUiThread {
                            Toast.makeText(this@AddClassActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    private fun validateInputs(): Boolean {
        val className = etClassName.text.toString()
        val totalStr = etTotal.text.toString()

        if (className.isBlank() || totalStr.isBlank()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        return try {
            totalStr.toInt()
            true
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Total must be a number", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun handleErrorResponse(response: Response<Classes>) {
        val errorMessage = response.errorBody()?.string() ?: "Unknown error occurred"
        Log.e(TAG, "Failed to add class: $errorMessage")
        Toast.makeText(this@AddClassActivity, "Failed to add class: $errorMessage", Toast.LENGTH_SHORT).show()
    }
}
