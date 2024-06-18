package com.example.a22it227_hunhthphc_22jit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.Classes

class ClassesAdapter(
    private val classesList: MutableList<Classes>,
    private val onOptionsClick: (Classes) -> Unit,
    private val onItemClick: (Classes) -> Unit // New click listener for items
) : RecyclerView.Adapter<ClassesAdapter.ClassesViewHolder>() {

    inner class ClassesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val className: TextView = itemView.findViewById(R.id.tvClassName)
        val total: TextView = itemView.findViewById(R.id.tvTotal)
        val btnOptions: ImageButton = itemView.findViewById(R.id.btnOptions)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(classesList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.class_item, parent, false)
        return ClassesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClassesViewHolder, position: Int) {
        val currentClass = classesList[position]
        holder.className.text = currentClass.className
        holder.total.text = currentClass.total.toString()
        holder.btnOptions.setOnClickListener {
            onOptionsClick(currentClass)
        }
    }

    override fun getItemCount() = classesList.size

    fun removeClass(classData: Classes) {
        val position = classesList.indexOf(classData)
        if (position != -1) {
            classesList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun updateClass(updatedClass: Classes) {
        val position = classesList.indexOfFirst { it.id == updatedClass.id }
        if (position != -1) {
            classesList[position] = updatedClass
            notifyItemChanged(position)
        }
    }
}
