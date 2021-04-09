package com.example.kalkulatorbmi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class CheckCalories extends AppCompatActivity {
    private static final NumberFormat weightFormat =
            NumberFormat.getNumberInstance();
    private static final NumberFormat intFormat =
            NumberFormat.getIntegerInstance();
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    private int age = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_calories);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.BMI);
        // int height = intent.getIntExtra(MainActivity.HEIGHT,0);
        TextView bmiValue = findViewById(R.id.bmiValue);
        bmiValue.setText(message);
        TextView caloriesValue = findViewById(R.id.caloriesValue);


        Bundle bundle = getIntent().getExtras();
        int height = bundle.getInt(MainActivity.HEIGHT);
        double weight = bundle.getDouble(MainActivity.WEIGHT);
        addListenerOnButton(height, weight, age);
    }

    public void addListenerOnButton(int height, double weight, int age) {

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnDisplay = (Button) findViewById(R.id.foodOfferButton);

        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedId);
                calculateCalories((String) radioButton.getText(), age, weight, height);

            }

        });

    }

    public void calculateCalories(String radio, int age, double weight, int height) {
        TextView caloriesValue = findViewById(R.id.caloriesValue);
        double calories;
        EditText ageValue =
                (EditText) findViewById(R.id.ageValue);
        String ageV = ageValue.getText().toString();
        age = Integer.parseInt(ageV);
        if (radio.equals("Mężczyzna")) {

            calories = (66.47 + (13.7 * weight) + (5.0 * height) - (6.76 * age));

        } else {
            calories = (655.1 + (9.567 * weight) + (1.85 * height) - (4.68 * age));
        }
        caloriesValue.setText(weightFormat.format(calories));
        showRecipe();

    }
    public void showRecipe() {
        TextView recipeTextView = findViewById(R.id.recipe);
        Intent intent = getIntent();
        String bmi = intent.getStringExtra(MainActivity.BMI);
        double bmiValue = Double.parseDouble(bmi);
        if(bmiValue > 25.0){
            recipeTextView.setText(Html.fromHtml("<h2 style=\"text-align:justify\">Przepis:</h2>\n" +
                    " <p style=\"text-align:justify\">Marchewkę, seler, jabłko, gruszkę. Następnie posiekaj i zmieszaj i wcinaj bo to zdrowe, a waga na więcej nie pozwala</p>", Html.FROM_HTML_MODE_COMPACT));
        }else {
            recipeTextView.setText(Html.fromHtml("<h2 style=\"text-align:justify\">Przepis:</h2>\n" +
                    " <p style=\"text-align:justify\"> No tutaj z racji na optymalną wagę możemy sobie pozwolić na więcej. Przygotój 500g mielonego, 200g makaronu, słoik z gotowym sosem. Mięso przypraw i usmaż. Makaron ugotuj zgodnie z zaleceniami na opakowaniu. Następnie smażąc i mieszając mięso z makaronem, dodaj sos. Jak będzie gorące możesz śmiało podawać ze szklanką coli lub wody. SMACZNEGO!</p>", Html.FROM_HTML_MODE_COMPACT));
        }
    }
}