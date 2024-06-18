package com.example.a22it227_hunhthphc_22jit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            setting_item(R.drawable.calendar, "Năm học", "2021 - 2022"),
            setting_item(R.drawable.padlock, "Đổi mật khẩu", ""),
            setting_item(R.drawable.electronics, "Xác thực sinh trắc học", ""),
            setting_item(R.drawable.share, "Chia sẻ với bạn bè", ""),
            setting_item(R.drawable.star, "Đánh giá ứng dụng", ""),
            setting_item(R.drawable.i, "Phiên bản hiện tại", "3.6.1"),
            setting_item(R.drawable.compliant, "Chính sách & điều khoản", ""),

        )

        val adapter = settingAdapter(items)
        recyclerView.adapter = adapter
    }
}
