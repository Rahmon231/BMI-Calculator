package com.lemzeeyyy.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class BMIResultActivity extends AppCompatActivity {
    private TextView bmiResult,gender,bmi_category;
    private Button reCalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);
        bmiResult = findViewById(R.id.bmi_result);
        reCalculate = findViewById(R.id.re_calculate_bmi);
        gender = findViewById(R.id.gender_display);
        bmi_category = findViewById(R.id.bmi_category);
        double bmi = getIntent().getDoubleExtra("BMI",0);
        String.format("%.2f",bmi);
        if(bmi < 18.5){
            //underweight
            bmi_category.setText("your bmi category is underweight");
        }
        else if(bmi >18.5 && bmi < 24.9){
            //normal
            bmi_category.setText("your bmi category is normal");
        }else if(bmi > 25.00 && bmi < 29.9){
            //overweight
            bmi_category.setText("your bmi category is overweight");
        }else {
            //obese
            bmi_category.setText("your bmi category is obese");
        }

        bmiResult.setText(String.valueOf(bmi));

        Log.d("TAG", "onCreate: "+getIntent().getStringExtra("male"));
        Log.d("TAG", "onCreate: "+getIntent().getStringExtra("female"));
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