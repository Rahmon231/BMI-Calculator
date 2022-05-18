package com.lemzeeyyy.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private SeekBar height_seek;
    private TextView currentHeight,currentWeight, currentAge;
    private ImageView incrementAge, incrementWeight, decrementAge, decrementWeight;
    private Button calc_bmi;
    private double bmi = 0 ;
    private int height = 0;
    private int age = 0;
    private int weight = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        height_seek = findViewById(R.id.seek_height);
        height_seek.setMax(200);
        currentHeight = findViewById(R.id.current_height);
        currentAge = findViewById(R.id.current_age);
        currentWeight = findViewById(R.id.current_weight);
        incrementAge = findViewById(R.id.increment_age);
        incrementWeight = findViewById(R.id.increment_weight);
        decrementAge = findViewById(R.id.decrement_age);
        decrementWeight = findViewById(R.id.decrement_weight);
        calc_bmi = findViewById(R.id.calculate_bmi);

        height_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b) {
                    height = i;
                    Log.d("TAG", "onProgressChanged: " + i);
                    currentHeight.setText(String.valueOf(height));

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                currentHeight.setText(String.valueOf(height));
            }
        });
        incrementAge.setOnClickListener(view -> {
            age++;
            currentAge.setText(String.valueOf(age));
        });
        decrementAge.setOnClickListener(view -> {
            if(age!=0){
                age--;
            }
            currentAge.setText(String.valueOf(age));
        });
        incrementWeight.setOnClickListener(view -> {
            weight++;
            currentWeight.setText(String.valueOf(weight));
        });
        decrementWeight.setOnClickListener(view -> {
            if(weight!=0){
                weight--;
            }
            currentWeight.setText(String.valueOf(weight));
        });
        calc_bmi.setOnClickListener(view -> getBMI());
    }

    private double getBMI() {

        return bmi;

    }

}