package com.example.a22it227_hunhthphc_22jit.data

import java.text.SimpleDateFormat
import java.util.*
class DateUtils {
    companion object {
        // Chuyển đổi ngày sinh sang định dạng mới
        fun formatBirthday(birthdayString: Date): String {
            // Định dạng đầu vào
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())

            // Định dạng đầu ra
            val outputFormat = SimpleDateFormat("MMMM d, yyyy hh:mm:ss a", Locale.getDefault())

            // Chuyển đổi chuỗi thành đối tượng Date
            val birthdayDate = inputFormat.parse(birthdayString.toString())

            // Chuyển đổi đối tượng Date thành chuỗi ngày tháng mới
            return outputFormat.format(birthdayDate)
        }
    }
}
