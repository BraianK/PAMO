package com.example.kalkulatorbmi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class CheckCalories extends AppCompatActivity {
    private static final NumberFormat weightFormat =
            NumberFormat.getNumberInstance();
    private static final NumberFormat intFormat =
            NumberFormat.getIntegerInstance();
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

        Bundle bundle=getIntent().getExtras();
        int height = bundle.getInt(MainActivity.HEIGHT);
        double weight = bundle.getDouble(MainActivity.WEIGHT);
        //caloriesValue.setText(intFormat.format(height));
        caloriesValue.setText(weightFormat.format(weight));
    }

//    private void calculate() {
//        double bmi = weight/((height*height)*0.0001);
//        bmi = bmi*100;
//        bmi = Math.round(bmi);
//        bmi = bmi/100;
//        bmiValueTextView.setText(weightFormat.format(bmi));
//    }
}