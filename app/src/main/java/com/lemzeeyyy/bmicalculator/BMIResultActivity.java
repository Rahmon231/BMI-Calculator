package com.lemzeeyyy.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class BMIResultActivity extends AppCompatActivity {
    private TextView bmiResult,gender;
    private Button reCalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);
        bmiResult = findViewById(R.id.bmi_result);
        reCalculate = findViewById(R.id.re_calculate_bmi);
        gender = findViewById(R.id.gender_display);
        double bmi = getIntent().getDoubleExtra("BMI",0);
        String.format("%.2f",bmi);
        bmiResult.setText(String.valueOf(bmi));
        reCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMIResultActivity.this,MainActivity.class);
                startActivity(intent);
                //startActivity(new Intent(BMIResultActivity.this,MainActivity.class));
            }
        });
    }
}