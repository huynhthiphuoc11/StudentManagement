package com.example.a22it227_hunhthphc_22jit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.DateUtils
import com.example.a22it227_hunhthphc_22jit.data.User

class userAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<userAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.student_name)
        private val emailTextView: TextView = itemView.findViewById(R.id.tvEmail)
        private val addressTextView: TextView = itemView.findViewById(R.id.tvAddress)
        private val contactNoTextView: TextView = itemView.findViewById(R.id.tvContactNo)
        private val classNameTextView: TextView = itemView.findViewById(R.id.tvClassName)
        private val genderTextView: TextView = itemView.findViewById(R.id.tvGender)
        private val codeNoTextView: TextView = itemView.findViewById(R.id.tvCode)

        fun bind(user: User) {
            nameTextView.text = user.name
            emailTextView.text = user.email
            addressTextView.text = "Address                        ${user.address}"
            contactNoTextView.text ="Phone                           ${user.contactNo}"
            classNameTextView.text ="Class                             ${user.className}"
            genderTextView.text = "Gender                          ${user.gender}"
            codeNoTextView.text = "Code                         ${user.code}"


        }
    }
}