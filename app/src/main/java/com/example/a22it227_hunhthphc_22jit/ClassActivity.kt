package com.example.a22it227_hunhthphc_22jit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.Classes
import com.example.a22it227_hunhthphc_22jit.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var classesAdapter: ClassesAdapter
    private val EDIT_CLASS_REQUEST_CODE = 2
    private val ADD_CLASS_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Close the current activity and go back to the previous one
        }

        fetchClasses()
    }

    private fun fetchClasses() {
        RetrofitClient.instance.getAllClasses().enqueue(object : Callback<List<Classes>> {
            override fun onResponse(call: Call<List<Classes>>, response: Response<List<Classes>>) {
                if (response.isSuccessful) {
                    val classesList = response.body() ?: emptyList()
                    classesAdapter = ClassesAdapter(classesList.toMutableList(),
                        { classData -> showOptionsDialog(classData) },
                        { classData -> openAttendanceActivity(classData) } // Pass the item click listener
                    )
                    recyclerView.adapter = classesAdapter
                } else {
                    Log.e(TAG, "Failed to fetch classes: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Classes>>, t: Throwable) {
                Log.e(TAG, "Error fetching classes: ${t.message}", t)
            }
        })
    }

    private fun showOptionsDialog(classData: Classes) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_options_class, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val tvClassName: TextView = dialogView.findViewById(R.id.tvClassName)
        val tvTotal: TextView = dialogView.findViewById(R.id.tvTotal)
        val btnAdd: ImageButton = dialogView.findViewById(R.id.btnAdd)
        val btnEdit: ImageButton = dialogView.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = dialogView.findViewById(R.id.btnDelete)

        tvClassName.text = classData.className
        tvTotal.text = classData.total.toString()

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddClassActivity::class.java)
            startActivityForResult(intent, ADD_CLASS_REQUEST_CODE)
            dialog.dismiss()
        }

        btnEdit.setOnClickListener {
            val intent = Intent(this, EditClassActivity::class.java).apply {
                putExtra("CLASS_DATA", classData)
            }
            startActivityForResult(intent, EDIT_CLASS_REQUEST_CODE)
            dialog.dismiss()
        }

        btnDelete.setOnClickListener {
            Log.d(TAG, "Delete button clicked for class with ID: ${classData.id}")
            classesAdapter.removeClass(classData)
            Toast.makeText(this@ClassActivity, "Class removed successfully", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun openAttendanceActivity(classData: Classes) {
        val intent = Intent(this, CalendarActivity::class.java).apply {
            putExtra("CLASS_DATA", classData)
        }
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_CLASS_REQUEST_CODE && resultCode == RESULT_OK) {
            val updatedClass = data?.getSerializableExtra("UPDATED_CLASS") as? Classes
            updatedClass?.let {
                classesAdapter.updateClass(it)
                Toast.makeText(this, "Class updated successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val TAG = "ClassActivity"
    }
}
