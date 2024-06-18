package com.example.a22it227_hunhthphc_22jit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.Schedule

class InnerScheduleAdapter(private val scheduleList: List<Schedule>) : RecyclerView.Adapter<InnerScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStartTime: TextView = itemView.findViewById(R.id.tvStartTime)
        val tvEndTime: TextView = itemView.findViewById(R.id.tvEndTime)
        val tvSubject: TextView = itemView.findViewById(R.id.tvSubject)
        val tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tkb_item, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val currentItem = scheduleList[position]
        holder.tvStartTime.text = currentItem.startTime
        holder.tvEndTime.text = currentItem.endTime
        holder.tvSubject.text = currentItem.courseName
        holder.tvLocation.text = currentItem.location
    }

    override fun getItemCount() = scheduleList.size
}
