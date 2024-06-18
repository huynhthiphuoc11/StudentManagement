package com.example.a22it227_hunhthphc_22jit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.DateUtils
import com.example.a22it227_hunhthphc_22jit.data.User
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class userActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: userAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val service = RetrofitClient.userService
        val call = service.getUser(4)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        adapter = userAdapter(listOf(user))
                        recyclerView.adapter = adapter
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("API_ERROR", "Error response: $errorBody")
                    Toast.makeText(this@userActivity, "Failed to get user", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("API_ERROR", "Network Error: ${t.message}")
                Toast.makeText(this@userActivity, "Network Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
