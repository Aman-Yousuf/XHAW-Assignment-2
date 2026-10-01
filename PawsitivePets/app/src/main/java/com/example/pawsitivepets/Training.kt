package com.example.pawsitivepets

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Training : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_training)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val puppyCare = findViewById<CheckBox>(R.id.checkPuppyTraining)
        val obedience = findViewById<CheckBox>(R.id.checkBasicObedience)
        val behaviour = findViewById<CheckBox>(R.id.checkBehaviorTraining)
        val dogWalking = findViewById<CheckBox>(R.id.checkLeashTraining)

        findViewById<Button>(R.id.btnConfirmSelection2).setOnClickListener {
            Booking.update("Puppy Care", 750.0, puppyCare.isChecked)
            Booking.update("Canine Obedience Training", 1500.0, obedience.isChecked)
            Booking.update("Animal Behaviour", 1500.0, behaviour.isChecked)
            Booking.update("Basic Dog Walking", 750.0, dogWalking.isChecked)
            startActivity(Intent(this, CalculateFeesPage::class.java))
        }
    }
}