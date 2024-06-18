package com.example.a22it227_hunhthphc_22jit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a22it227_hunhthphc_22jit.data.LoginRequest
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import com.example.a22it227_hunhthphc_22jit.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignIn: Button
    private lateinit var buttonSignUp: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonSignIn = findViewById(R.id.buttonSignIn)
        buttonSignUp = findViewById(R.id.buttonSignUp)

        buttonSignIn.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val role = 1 // Default role value is 1 for student

            val loginRequest = LoginRequest(email, password, role)

            val userService = RetrofitClient.getRetrofitInstance().create(UserService::class.java)
            val call = userService.login(loginRequest)

            call.enqueue(object : Callback<LoginRequest> {
                override fun onResponse(call: Call<LoginRequest>, response: Response<LoginRequest>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()

                        // Redirect to teacher_home activity
                        val intent = Intent(this@LoginActivity, MainActivity2::class.java)
                        startActivity(intent)
                    } else {
                        val errorMessage = response.errorBody()?.string()
                        val errorStatusCode = response.code()
                        Log.e("Login failed - Status code: $errorStatusCode", errorMessage ?: "Unknown error")
                        Toast.makeText(this@LoginActivity, "Login failed: $errorMessage", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginRequest>, t: Throwable) {
                    Log.e("Failed to send login request", "Error: ${t.message}", t)
                    Toast.makeText(this@LoginActivity, "Login request failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        buttonSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}
