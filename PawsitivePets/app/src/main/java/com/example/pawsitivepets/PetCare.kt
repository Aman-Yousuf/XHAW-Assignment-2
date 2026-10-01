package com.example.pawsitivepets

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PetCare : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pet_care)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val firstAid = findViewById<CheckBox>(R.id.checkPetSitting)
        val businessMgmt = findViewById<CheckBox>(R.id.checkDogWalking)

        findViewById<Button>(R.id.btnConfirmSelection1).setOnClickListener {
            Booking.update("Pet First Aid", 750.0, firstAid.isChecked)
            Booking.update("Pet Business Management", 1500.0, businessMgmt.isChecked)
            startActivity(Intent(this, CalculateFeesPage::class.java))
        }
    }
}