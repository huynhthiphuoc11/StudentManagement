package com.example.a22it227_hunhthphc_22jit

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22it227_hunhthphc_22jit.data.News

class NewsActivity : AppCompatActivity() {
    private lateinit var btnback: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter
    private lateinit var courseList: MutableList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news)
        btnback = findViewById(R.id.btnBack)

        btnback.setOnClickListener {
            // Quay lại MainActivity2
            finish()
        }
        // Khởi tạo dữ liệu mẫu
        courseList = mutableListOf(
            News(
                "Cải cách giáo dục kỹ năng số: Trong nỗ lực cải cách giáo dục, các chương trình giáo dục.",
                R.drawable.news_1
            ),
            News("Đầu tư vào giáo dục công bằng: Trên thế giới, nhiều nước đang tập trung đầu tư. ", R.drawable.news_2),
            News("Khởi động lại giáo dục sau đại dịch COVID-19: Việc tái khởi động giáo dục sau đại dịch COVID-19. ", R.drawable.news_5),
            News("Thách thức về tài chính trong giáo dục: Nhiều quốc gia đang đối mặt. ", R.drawable.news_7)

        )

        // Ánh xạ RecyclerView từ layout
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Khởi tạo adapter
        adapter = NewsAdapter(this, courseList)
        recyclerView.adapter = adapter
    }
}
