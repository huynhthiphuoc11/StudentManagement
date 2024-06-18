package com.example.a22it227_hunhthphc_22jit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.DaySchedule

class ScheduleAdapter(private val dayScheduleList: List<DaySchedule>) : RecyclerView.Adapter<ScheduleAdapter.DayScheduleViewHolder>() {

    class DayScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDayOfWeek: TextView = itemView.findViewById(R.id.tvDayOfWeek)
        val rvSchedules: RecyclerView = itemView.findViewById(R.id.rvSchedule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_schedule_item, parent, false)
        return DayScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayScheduleViewHolder, position: Int) {
        val currentDaySchedule = dayScheduleList[position]
        holder.tvDayOfWeek.text = currentDaySchedule.dayOfWeek

        // Set up nested RecyclerView
        holder.rvSchedules.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.rvSchedules.adapter = InnerScheduleAdapter(currentDaySchedule.schedules)
    }

    override fun getItemCount() = dayScheduleList.size
}
