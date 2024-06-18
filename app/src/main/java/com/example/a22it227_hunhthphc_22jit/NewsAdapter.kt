package com.example.a22it227_hunhthphc_22jit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.News

class NewsAdapter(private val context: Context, private val courseList: List<News>) :
    RecyclerView.Adapter<NewsAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList[position]
        holder.courseTitle.text = course.title
        holder.courseImage.setImageResource(course.imageResource)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseImage: ImageView = itemView.findViewById(R.id.course_image)
        val courseTitle: TextView = itemView.findViewById(R.id.course_title)
    }
}
