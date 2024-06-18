package com.example.a22it227_hunhthphc_22jit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.Grade

class GradesActivity1 : AppCompatActivity() {
    private val gradeViewModel: GradeViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnBack: ImageButton
    private lateinit var adapter: GradesAdapter1
    private lateinit var btnAdd: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        recyclerView = findViewById(R.id.recyclerViewGrades)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GradesAdapter1 { grade -> showGradeDialog(grade) }
        recyclerView.adapter = adapter


        val selectedHocKy = intent.getIntExtra("SELECTED_HOCKY", 1)
        val selectedCourse = intent.getIntExtra("SELECTED_COURSE", 1)

        gradeViewModel.fetchGrades(selectedHocKy, selectedCourse)

        gradeViewModel.grades.observe(this, Observer { grades ->
            grades?.let { adapter.submitList(it) }
        })
    }

    private fun showGradeDialog(grade: Grade) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_grade_info, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val tvStudentName: TextView = dialogView.findViewById(R.id.tvStudentName)
        val tvRegularScore: TextView = dialogView.findViewById(R.id.tvRegularScore)
        val tvMidtermScore: TextView = dialogView.findViewById(R.id.tvMidtermScore)
        val tvFinalScore: TextView = dialogView.findViewById(R.id.tvFinalScore)

        tvStudentName.text = grade.studentName
        tvRegularScore.text = "Regular Score: ${grade.regularScore}"
        tvMidtermScore.text = "Midterm Score: ${grade.midtermScore}"
        tvFinalScore.text = "Final Score: ${grade.finalScore}"

        val btnEdit: ImageButton = dialogView.findViewById(R.id.btnEdit)
        btnEdit.setOnClickListener {
            val intent = Intent(this, EditGradeActivity::class.java).apply {
                putExtra("GRADE_ID", grade.id)
            }
            startActivityForResult(intent, EDIT_GRADE_REQUEST_CODE)
            dialog.dismiss()
        }

        val btnAdd: ImageButton = dialogView.findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddGradeActivity::class.java)
            startActivityForResult(intent, ADD_GRADE_REQUEST_CODE)
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_GRADE_REQUEST_CODE && resultCode == RESULT_OK) {
            val newGrade = data?.getSerializableExtra("NEW_GRADE") as? Grade
            newGrade?.let {
                gradeViewModel.addGrade(it)
                Toast.makeText(this, "Grade added successfully", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == EDIT_GRADE_REQUEST_CODE && resultCode == RESULT_OK) {
            val updatedGrade = data?.getSerializableExtra("UPDATED_GRADE") as? Grade
            updatedGrade?.let {
                gradeViewModel.updateGrade(it.id, it) { isSuccess, message ->
                    if (isSuccess) {
                        val currentList = adapter.currentList.toMutableList()
                        val index = currentList.indexOfFirst { grade -> grade.id == it.id }
                        if (index != -1) {
                            currentList[index] = it
                            adapter.submitList(currentList)
                            Toast.makeText(this, "Grade updated successfully", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    companion object {
        private const val ADD_GRADE_REQUEST_CODE = 1
        private const val EDIT_GRADE_REQUEST_CODE = 2
    }
}
