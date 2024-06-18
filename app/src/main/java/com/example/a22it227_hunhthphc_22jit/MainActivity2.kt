package com.example.a22it227_hunhthphc_22jit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.a22it227_hunhthphc_22jit.data.Item
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_home)

        // Initialize card views
        val cardHomeImage: CardView = findViewById(R.id.card_home_image)
        val cardHomeImage3: CardView = findViewById(R.id.card_home_image4)
        val cardHomeImage1: CardView = findViewById(R.id.card_home_image1)
        val cardHomeImage2: CardView = findViewById(R.id.card_home_image2)

        // Set up image slider
        val images = arrayOf(
            R.drawable.slider22,
            R.drawable.slider11,
            R.drawable.slider33
        )
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = ImageSliderAdapter(images)
        viewPager.adapter = adapter

        // Set up card click listeners
        cardHomeImage.setOnClickListener {
            val intent = Intent(this, ClassActivity::class.java)
            startActivity(intent)
        }
        cardHomeImage1.setOnClickListener {
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent)
        }
        cardHomeImage2.setOnClickListener {
            val intent = Intent(this, GradesManaActivity::class.java)
            startActivity(intent)
        }
        cardHomeImage3.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val itemList = listOf(
            Item(R.drawable.pic1, "Hướng dẫn đăng nhập", "https://www.youtube.com/watch?v=5ZUZ6Pwktfg"),
            Item(R.drawable.pic2, "Đổi năm học trên Edu", "https://www.youtube.com/watch?v=XAtu2H9YIsw"),
            Item(R.drawable.pic3, "Đăng ký tuyển sinh", "https://www.youtube.com/watch?v=EIwceYV-jmc"),
            Item(R.drawable.pic4, "Hướng dẫn đổi mật khẩu", "https://www.youtube.com/watch?v=8IiLha6M9OA")
        )

        recyclerView.adapter = ItemAdapter(itemList)

        // Set up bottom navigation
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_maneu)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    // Handle home action
                    true
                }
                R.id.profile -> {
                    // Handle profile action
                    val intent = Intent(this, userActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.settings -> {
                    val intent = Intent(this, SettingActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
