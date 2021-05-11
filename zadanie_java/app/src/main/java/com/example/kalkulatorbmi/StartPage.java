package com.example.kalkulatorbmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

public class StartPage extends AppCompatActivity {

    Button switchToSecondActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        switchToSecondActivity = findViewById(R.id.startButton);
        switchToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });
    }
    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }


    public void goToChart(View view) {
        Intent intent = new Intent(this, Chart.class);
        startActivity(intent);
    }
    public void goToQuiz(View view) {
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }
}