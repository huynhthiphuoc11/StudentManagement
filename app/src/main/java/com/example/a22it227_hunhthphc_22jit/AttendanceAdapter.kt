package com.example.a22it227_hunhthphc_22jit.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.AttendanceUpdateListener
import com.example.a22it227_hunhthphc_22jit.R
import java.text.SimpleDateFormat
import java.util.*


import java.util.Locale

class AttendanceAdapter(
    private val context: Context,
    private val attendanceList: MutableList<Attendance>,
    private val listener: AttendanceUpdateListener
) : RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    inner class AttendanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textStudentId: TextView = itemView.findViewById(R.id.textStudentId)
        private val textStudentName: TextView = itemView.findViewById(R.id.textStudentName)
        private val spinnerStudentStatus: Spinner = itemView.findViewById(R.id.spinnerStudentStatus)

        fun bind(attendance: Attendance) {
            textStudentId.text = attendance.studentId.toString()
            textStudentName.text = attendance.student_name

            // Ensure status is not null
            val status = attendance.status ?: AttendanceStatus.PRESENT // Provide a default value if status is null
            spinnerStudentStatus.setSelection(status.ordinal)

            spinnerStudentStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val selectedStatus = AttendanceStatus.values()[position]
                    if (attendance.status != selectedStatus) {
                        attendance.status = selectedStatus
                        listener.onAttendanceUpdated(attendance)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.attendance_item, parent, false)
        return AttendanceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        holder.bind(attendanceList[position])
    }

    override fun getItemCount(): Int {
        return attendanceList.size
    }

    fun setData(newAttendanceList: List<Attendance>) {
        attendanceList.clear()
        attendanceList.addAll(newAttendanceList)
        notifyDataSetChanged()
    }

    fun getData(): List<Attendance> {
        return attendanceList
    }
}
