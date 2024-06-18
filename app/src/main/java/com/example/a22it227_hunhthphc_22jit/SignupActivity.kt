package com.example.a22it227_hunhthphc_22jit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a22it227_hunhthphc_22jit.data.SignUpRequest
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import com.example.a22it227_hunhthphc_22jit.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var editTextContactNo: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var buttonSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextAddress = findViewById(R.id.editTextAddress)
        editTextContactNo = findViewById(R.id.editTextContactNo)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonRegister = findViewById(R.id.buttonRegister)
        buttonSignIn = findViewById(R.id.buttonSignIn)
        buttonRegister.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val address = editTextAddress.text.toString().trim()
            val contactNo = editTextContactNo.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val role = 0 // Assuming default role is 0

            val signupRequest = SignUpRequest(name, email, address, role, contactNo, password)

            val userService = RetrofitClient.getRetrofitInstance().create(UserService::class.java)
            val call = userService.signup(signupRequest)

            call.enqueue(object : Callback<SignUpRequest> {
                override fun onResponse(call: Call<SignUpRequest>, response: Response<SignUpRequest>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@SignupActivity, "Signup successful", Toast.LENGTH_SHORT).show()
                        // Handle successful signup, maybe navigate to login activity
                    } else {
                        val errorMessage = response.errorBody()?.string()
                        val errorStatusCode = response.code()
                        Log.e("Signup failed - Status code: $errorStatusCode", errorMessage ?: "Unknown error")
                        Toast.makeText(this@SignupActivity, "Signup failed: $errorMessage", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SignUpRequest>, t: Throwable) {
                    Log.e("Failed to send signup request", "Error: ${t.message}", t)
                    Toast.makeText(this@SignupActivity, "Signup request failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        buttonSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}
