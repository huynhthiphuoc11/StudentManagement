package com.example.a22it227_hunhthphc_22jit

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var mSLideViewPager: ViewPager
    private lateinit var mDotLayout: LinearLayout
    private lateinit var backbtn: Button
    private lateinit var nextbtn: Button
    private lateinit var skipbtn: Button
    private lateinit var dots: Array<TextView>
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        backbtn = findViewById(R.id.backbtn)
        nextbtn = findViewById(R.id.nextbtn)
        skipbtn = findViewById(R.id.skipButton)

        backbtn.setOnClickListener {
            if (getitem(0) > 0) {
                mSLideViewPager.setCurrentItem(getitem(-1), true)
            }
        }

        nextbtn.setOnClickListener {
            if (getitem(0) < 2) {
                mSLideViewPager.setCurrentItem(getitem(1), true)
            } else {
                val i = Intent(this@MainActivity, SignupActivity::class.java)
                startActivity(i)
                finish()
            }
        }

        skipbtn.setOnClickListener {
            val i = Intent(this@MainActivity, SignupActivity::class.java)
            startActivity(i)
            finish()
        }

        mSLideViewPager = findViewById(R.id.slideViewPager)
        mDotLayout = findViewById(R.id.indicator_layout)

        viewPagerAdapter = ViewPagerAdapter(this)

        mSLideViewPager.adapter = viewPagerAdapter

        setUpIndicator(0)
        mSLideViewPager.addOnPageChangeListener(viewListener)

    }

    private fun setUpIndicator(position: Int) {
        dots = Array(3) { TextView(this) }
        mDotLayout.removeAllViews()

        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i].text = Html.fromHtml("&#8226")
            dots[i].textSize = 35f
            dots[i].setTextColor(resources.getColor(R.color.inactive, applicationContext.theme))
            mDotLayout.addView(dots[i])
        }

        dots[position].setTextColor(resources.getColor(R.color.active, applicationContext.theme))
    }

    private val viewListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            setUpIndicator(position)

            if (position > 0) {
                backbtn.visibility = View.VISIBLE
            } else {
                backbtn.visibility = View.INVISIBLE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun getitem(i: Int): Int {
        return mSLideViewPager.currentItem + i
    }
}
