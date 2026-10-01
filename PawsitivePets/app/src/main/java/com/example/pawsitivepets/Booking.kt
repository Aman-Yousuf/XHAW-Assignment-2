package com.example.pawsitivepets

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
}