package com.example.pawsitivepets

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class CalculateFeesPage : AppCompatActivity() {

    private lateinit var txtSelectedService: TextView
    private lateinit var txtTotal: TextView
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText

    private var totalCalculated = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculate_fees_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }

        txtSelectedService = findViewById(R.id.txtSelectedService)
        txtTotal = findViewById(R.id.txtTotal)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)

        findViewById<Button>(R.id.btnCalculateTotal).setOnClickListener {
            calculateTotal()
        }
        findViewById<Button>(R.id.btnConfirmBooking).setOnClickListener {
            confirmBooking()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Booking.courseNames.isEmpty()) {
            txtSelectedService.text = "No courses selected yet"
        } else {
            txtSelectedService.text = Booking.courseNames.joinToString(", ")
        }
        txtTotal.text = "Your total is: "
        totalCalculated = false
    }

    private fun calculateTotal() {
        if (!detailsAreValid()) return

        if (Booking.courseNames.isEmpty()) {
            Toast.makeText(this, "Please select at least one course", Toast.LENGTH_SHORT).show()
            return
        }

        val subtotal = Booking.coursePrices.sum()

        val numberOfCourses = Booking.courseNames.size
        val discountPercent = when {
            numberOfCourses == 1 -> 0
            numberOfCourses == 2 -> 5
            numberOfCourses == 3 -> 10
            else -> 15
        }
        val discount = subtotal * discountPercent / 100

        val afterDiscount = subtotal - discount
        val vat = afterDiscount * 0.15

        val total = afterDiscount + vat

        txtTotal.text = "Total: " + rand(total)
        totalCalculated = true

        var quote = ""
        for (i in Booking.courseNames.indices) {
            quote += Booking.courseNames[i] + ": " + rand(Booking.coursePrices[i]) + "\n"
        }
        quote += "\nSubtotal: " + rand(subtotal)
        quote += "\nDiscount ($discountPercent%): -" + rand(discount)
        quote += "\nVAT (15%): +" + rand(vat)
        quote += "\n\nTotal: " + rand(total)
        quote += "\n\nThis is a quoted fee only, not a formal invoice."

        AlertDialog.Builder(this)
            .setTitle("Your Quote")
            .setMessage(quote)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun confirmBooking() {
        if (!totalCalculated) {
            Toast.makeText(this, "Please calculate your total first", Toast.LENGTH_SHORT).show()
            return
        }
        if (!detailsAreValid()) return

        Toast.makeText(
            this,
            "Thank you! A consultant will contact you to finalise your booking.",
            Toast.LENGTH_LONG
        ).show()

        Booking.clear()
        val intent = Intent(this, HomePage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }

    private fun detailsAreValid(): Boolean {
        val name = etName.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val email = etEmail.text.toString().trim()

        if (name.isEmpty()) {
            etName.error = "Please enter your name"
            return false
        }
        if (phone.length != 10 || !phone.all { it.isDigit() }) {
            etPhone.error = "Phone number must be 10 digits, e.g. 0821234567"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Please enter a valid email address"
            return false
        }
        return true
    }

    private fun rand(amount: Double): String {
        return "R" + String.format(Locale.US, "%.2f", amount)
    }

}