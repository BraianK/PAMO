package com.example.kalkulatorbmi

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat

class CheckCalories : AppCompatActivity() {
    private var radioGroup: RadioGroup? = null
    private var radioButton: RadioButton? = null
    private var btnDisplay: Button? = null
    private val age = 20
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_calories)
        val intent = intent
        val message = intent.getStringExtra(MainActivity.BMI)
        val bmiValue = findViewById<TextView>(R.id.bmiValue)
        bmiValue.text = message
        findViewById<TextView>(R.id.caloriesValue)
        val bundle = getIntent().extras
        val height = bundle!!.getInt(MainActivity.Companion.HEIGHT)
        val weight = bundle.getDouble(MainActivity.Companion.WEIGHT)
        addListenerOnButton(height, weight, age)
    }

    fun addListenerOnButton(height: Int, weight: Double, age: Int) {
        radioGroup = findViewById<View>(R.id.radioGroup) as RadioGroup
        btnDisplay = findViewById<View>(R.id.foodOfferButton) as Button
        btnDisplay!!.setOnClickListener {
            val selectedId = radioGroup!!.checkedRadioButtonId
            radioButton = findViewById<View>(selectedId) as RadioButton
            calculateCalories(radioButton!!.text as String, age, weight, height)
        }
    }

    fun calculateCalories(radio: String, age: Int, weight: Double, height: Int) {
        var age: Int
        val caloriesValue = findViewById<TextView>(R.id.caloriesValue)
        val ageValue = findViewById<View>(R.id.ageValue) as EditText
        val ageV = ageValue.text.toString()
        age = ageV.toInt()
        val calories: Double = if (radio == "Mężczyzna") {
            66.47 + 13.7 * weight + 5.0 * height - 6.76 * age
        } else {
            655.1 + 9.567 * weight + 1.85 * height - 4.68 * age
        }
        caloriesValue.text = weightFormat.format(calories)
        showRecipe()
    }

    fun showRecipe() {
        val recipeTextView = findViewById<TextView>(R.id.recipe)
        val intent = intent
        val bmi = intent.getStringExtra(MainActivity.BMI)
        val bmiValue = bmi!!.toDouble()
        if (bmiValue > 25.0) {
            recipeTextView.text = Html.fromHtml("""<h2 style="text-align:justify">Przepis:</h2>
 <p style="text-align:justify">Marchewkę, seler, jabłko, gruszkę. Następnie posiekaj i zmieszaj i wcinaj bo to zdrowe, a waga na więcej nie pozwala</p>""", Html.FROM_HTML_MODE_COMPACT)
        } else {
            recipeTextView.text = Html.fromHtml("""<h2 style="text-align:justify">Przepis:</h2>
 <p style="text-align:justify"> No tutaj z racji na optymalną wagę możemy sobie pozwolić na więcej. Przygotój 500g mielonego, 200g makaronu, słoik z gotowym sosem. Mięso przypraw i usmaż. Makaron ugotuj zgodnie z zaleceniami na opakowaniu. Następnie smażąc i mieszając mięso z makaronem, dodaj sos. Jak będzie gorące możesz śmiało podawać ze szklanką coli lub wody. SMACZNEGO!</p>""", Html.FROM_HTML_MODE_COMPACT)
        }
    }

    companion object {
        private val weightFormat = NumberFormat.getNumberInstance()
        private val intFormat = NumberFormat.getIntegerInstance()
    }
}