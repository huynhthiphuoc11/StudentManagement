package com.example.a22it227_hunhthphc_22jit

import android.app.Activity
import android.content.ContentValues.TAG
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

class EditClassActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var tvClassName: EditText
    private lateinit var tvId: EditText
    private lateinit var tvTotal: EditText
    private lateinit var btnUpdateClass: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_class)

        // Initialize views
        btnBack = findViewById(R.id.btn_back)
        tvClassName = findViewById(R.id.tvClassName)
        tvTotal = findViewById(R.id.tvTotal)
        tvId = findViewById(R.id.tvId)
        btnUpdateClass = findViewById(R.id.btn_update_class)


        btnBack.setOnClickListener {
            finish() // Close the current activity and go back to the previous one
        }


        // Get class data from intent
        val classData = intent.getSerializableExtra("CLASS_DATA") as? Classes
        classData?.let {
            tvClassName.setText(it.className)
            tvTotal.setText(it.total.toString())
            tvId.setText(it.id.toString())
        }

        // Set update button click listener
        btnUpdateClass.setOnClickListener {
            val updatedClass = Classes(
                id = tvId.text.toString().toInt(),
                className = tvClassName.text.toString(),
                total = tvTotal.text.toString().toInt()
            )

            RetrofitClient.instance.updateClass(updatedClass.id, updatedClass).enqueue(object : Callback<Classes> {
                override fun onResponse(call: Call<Classes>, response: Response<Classes>) {
                    if (response.isSuccessful) {
                        val updatedClass = response.body()
                        Log.d(TAG, "Class updated successfully: $updatedClass")

                        val resultIntent = Intent()
                        resultIntent.putExtra("UPDATED_CLASS", updatedClass)
                        setResult(Activity.RESULT_OK, resultIntent)
                        finish()
                    } else {
                        Log.e(TAG, "Failed to update class: ${response.errorBody()?.string()}")
                        Toast.makeText(this@EditClassActivity, "Failed to update class", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Classes>, t: Throwable) {
                    Log.e(TAG, "Error: ${t.message}", t)
                    Toast.makeText(this@EditClassActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
