package com.example.pawsitivepets

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CoursesListPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_courses_list_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnPetCare).setOnClickListener {
            startActivity(Intent(this, PetCare::class.java))
        }
        findViewById<Button>(R.id.btnTraining).setOnClickListener {
            startActivity(Intent(this, Training::class.java))
        }
        findViewById<Button>(R.id.btnGrooming).setOnClickListener {
            startActivity(Intent(this, Grooming::class.java))
        }
    }
}