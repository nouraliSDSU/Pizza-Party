package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

/**
 * @author Nour Ali
 * @since version 2022.2
 * This class is the main driver for our pizza party app.
 */
const val SLICES_PER_PIZZA = 8

class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup

    /**
     * Initialize [numAttendEditText], [numPizzasTextView] and [howHungryRadioGroup]
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
    }

    fun calculateClick(view: View) {

        /**
         * Retrieve the text that was typed into the EditText
         */
        val numAttendStr = numAttendEditText.text.toString()

        /**
         * Convert the text to an integer
         * Guard against null value by setting it to 0
         */
        val numAttend = numAttendStr.toIntOrNull() ?: 0;

        /**
         * Determine how many slices on average each person will eat
         */
        val slicesPerPerson = when (howHungryRadioGroup.checkedRadioButtonId) {
            R.id.light_radio_button -> 2
            R.id.medium_radio_button -> 3
            else -> 4
        }

        /**
         * Calculate and show the number of pizzas needed
         * Ex. When the Light radio button is selected,
         * the app assumes each person eats 2 slices.
         * Math.ceil(10 * 2 / 8.0) = Math.ceil(2.5) = 3
         */
        val totalPizzas = ceil(numAttend * slicesPerPerson / SLICES_PER_PIZZA.toDouble()).toInt()
        numPizzasTextView.text = "Total pizzas: $totalPizzas"
    }
}