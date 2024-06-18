package com.example.a22it227_hunhthphc_22jit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.a22it227_hunhthphc_22jit.data.Notification

class NotificationAdapter(private val context: Context, private val notifications: List<Notification>) : ArrayAdapter<Notification>(context, 0, notifications) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false)
        }

        // Ensure itemView is not null before accessing its children
        itemView?.let {
            // Lấy thông tin của thông báo tại vị trí hiện tại
            val notification = notifications[position]

            // Gán dữ liệu vào các thành phần trong item layout
            val contentTextView: TextView? = it.findViewById(R.id.contentTextView)
            contentTextView?.text = notification.content

            val sentDateTextView: TextView? = it.findViewById(R.id.sentDateTextView)
            sentDateTextView?.text = "Sent Date: ${notification.sentDate}"

            val statusTextView: TextView? = it.findViewById(R.id.statusTextView)
            statusTextView?.text = "Status: ${notification.status}"

            // Thêm các gán dữ liệu cho các thành phần khác tùy thuộc vào dữ liệu bạn muốn hiển thị
        }

        return itemView ?: View(context) // Return itemView or an empty View if null
    }
}

