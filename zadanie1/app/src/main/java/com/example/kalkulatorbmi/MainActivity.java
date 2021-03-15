package com.example.kalkulatorbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double weight = 0.0;
    private int height = 1;
    private TextView weightTextView;
    private TextView bmiTextView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightTextView = (TextView) findViewById(R.id.weightTextView);
        weightTextView.setText("0.0");
        bmiTextView = (TextView) findViewById(R.id.bmiValueTextView);
        bmiTextView.setText("0.0");



    }
}