package com.example.pawsitivepets

import android.widget.CheckBox

object Booking {
    val courseNames = ArrayList<String>()
    val coursePrices = ArrayList<Double>()


    fun update(name: String, price: Double, isTicked: Boolean) {
        val position = courseNames.indexOf(name)

        if (isTicked && position == -1) {
            courseNames.add(name)
            coursePrices.add(price)
        }
        if (!isTicked && position != -1) {
            courseNames.removeAt(position)
            coursePrices.removeAt(position)
        }
    }

    fun clear() {
        courseNames.clear()
        coursePrices.clear()
    }

    // Ticks the box if the course is already chosen, and saves straight away when ticked or unticked
    fun link(box: CheckBox, name: String, price: Double) {
        box.isChecked = courseNames.contains(name)
        box.setOnCheckedChangeListener { _, ticked -> update(name, price, ticked) }
    }
}