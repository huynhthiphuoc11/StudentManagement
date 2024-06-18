package com.example.studentinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.R
import com.example.horizonrecyclerview.StudentInfo

class StudentInfoAdapter(private val context: Context, private val dataSource: List<StudentInfo>) :
    RecyclerView.Adapter<StudentInfoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val keyTextView: TextView = view.findViewById(R.id.key)
        val valueTextView: TextView = view.findViewById(R.id.value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_teacher_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val studentInfo = dataSource[position]
        holder.keyTextView.text = studentInfo.key
        holder.valueTextView.text = studentInfo.value
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}
