package com.example.pawsitivepets

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnViewCourses).setOnClickListener {
            startActivity(Intent(this, CoursesListPage::class.java))
        }
        findViewById<Button>(R.id.btnBookAService).setOnClickListener {
            startActivity(Intent(this, CalculateFeesPage::class.java))
        }
        findViewById<Button>(R.id.btnAboutUs).setOnClickListener {
            startActivity(Intent(this, AboutUsPage::class.java))
        }
        findViewById<Button>(R.id.btnContactUs).setOnClickListener {
            startActivity(Intent(this, ContactUsPage::class.java))
        }
    }
}