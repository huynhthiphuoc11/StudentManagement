package com.example.a22it227_hunhthphc_22jit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.Grade

class GradesAdapter1(private val onItemClick: (Grade) -> Unit) :
    ListAdapter<Grade, GradesAdapter1.GradeViewHolder>(GradeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.grade_item, parent, false)
        return GradeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val grade = getItem(position)
        holder.bind(grade)
        holder.itemView.setOnClickListener {
            onItemClick.invoke(grade)
        }
    }

    class GradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentName: TextView = itemView.findViewById(R.id.textViewStudentName)
        private val regularScore: TextView = itemView.findViewById(R.id.textViewRegularScoreValue)
        private val midtermScore: TextView = itemView.findViewById(R.id.textViewMidtermScoreValue)
        private val finalScore: TextView = itemView.findViewById(R.id.textViewFinalScoreValue)
        private val averageScore: TextView = itemView.findViewById(R.id.textViewAverageScoreValue)

        fun bind(grade: Grade) {
            studentName.text = grade.studentName
            regularScore.text = grade.regularScore.toString()
            midtermScore.text = grade.midtermScore.toString()
            finalScore.text = grade.finalScore.toString()
            averageScore.text = "%.1f".format(grade.averageScore)
        }
    }

    class GradeDiffCallback : DiffUtil.ItemCallback<Grade>() {
        override fun areItemsTheSame(oldItem: Grade, newItem: Grade): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Grade, newItem: Grade): Boolean {
            return oldItem == newItem
        }
    }

}
