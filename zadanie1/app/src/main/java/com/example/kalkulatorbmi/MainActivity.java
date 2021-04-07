package com.example.kalkulatorbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private static final NumberFormat weightFormat =
            NumberFormat.getNumberInstance();
    private static final NumberFormat intFormat =
            NumberFormat.getIntegerInstance();
    private double weight = 0.0;
    private int height = 1;
    private double bmi = 0;
    private TextView weightTextView;
    private TextView bmiValueTextView;
    private TextView heightTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightTextView = (TextView) findViewById(R.id.weightTextView);
        weightTextView.setText("0.0");
        heightTextView = (TextView) findViewById(R.id.heightTextView);
        heightTextView.setText("1");
        bmiValueTextView = (TextView) findViewById(R.id.bmiValueTextView);
        bmiValueTextView.setText("0");

        EditText weightTextView =
                (EditText) findViewById(R.id.weightTextView);
        weightTextView.addTextChangedListener(weightTextWatcher);

        SeekBar heightSeekBar =
                (SeekBar) findViewById(R.id.heightSeekBar);
        heightSeekBar.setOnSeekBarChangeListener(heightBarListener);

    }
    private void calculate() {
        double bmi = weight/((height*height)*0.0001);
        bmi = bmi*100;
        bmi = Math.round(bmi);
        bmi = bmi/100;
        bmiValueTextView.setText(weightFormat.format(bmi));
    }



    private final SeekBar.OnSeekBarChangeListener heightBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    height = progress;
                    heightTextView.setText(intFormat.format(height));
                    calculate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            };

    private final TextWatcher weightTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {
                weight = Double.parseDouble(s.toString());
            }
            catch (NumberFormatException e) {
                weight = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
}