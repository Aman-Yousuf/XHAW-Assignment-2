package com.example.pawsitivepets

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Grooming : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grooming)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }

        val grooming = findViewById<CheckBox>(R.id.checkBathAndDry)

        findViewById<Button>(R.id.btnConfirmSelection3).setOnClickListener {
            Booking.update("Pet Grooming", 1500.0, grooming.isChecked)
            startActivity(Intent(this, CalculateFeesPage::class.java))
        }
    }
}