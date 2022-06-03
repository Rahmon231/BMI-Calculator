package com.lemzeeyyy.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity{
    private SeekBar height_seek;
    private TextView currentHeight,currentWeight, currentAge;
    private ImageView incrementAge, incrementWeight, decrementAge, decrementWeight;
    private Button calc_bmi;
    private double bmi = 0 ;
    private double height = 0;
    private int age = 0;
    private double weight = 0 ;
    private Metrics metrics = new Metrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                    currentHeight.setText(String.valueOf(height));
                    metrics.setHeight(height);
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
            metrics.setAge(age);
            currentAge.setText(String.valueOf(age));
        });
        decrementAge.setOnClickListener(view -> {
            if(age!=0){
                age--;
            }
            metrics.setAge(age);
            currentAge.setText(String.valueOf(age));
        });
        incrementWeight.setOnClickListener(view -> {
            weight++;
            metrics.setWeight(weight);
            currentWeight.setText(String.valueOf(weight));
        });
        decrementWeight.setOnClickListener(view -> {
            if(weight!=0){
                weight--;
            }
            metrics.setWeight(weight);
            currentWeight.setText(String.valueOf(weight));
        });
        calc_bmi.setOnClickListener(view -> {
            getBMI();
            saveData();
            Intent intent = new Intent(MainActivity.this,BMIResultActivity.class);
            intent.putExtra("BMI",getBMI());
            startActivity(intent);
            //intent.putExtra("GENDER",)
        });
    }

    private void saveData() {
        int age = Integer.parseInt(currentAge.getText().toString());
        double weight = Double.parseDouble(currentWeight.getText().toString());
        double height = metrics.getHeight();
        Metrics metric = new Metrics(weight,height,age);

    }

    private double getBMI() {
        bmi = (metrics.getWeight()/Math.pow(metrics.getHeight(),2));

        //Log.d("TAG", "getBMI: "+bmi);

        return Double.parseDouble(new DecimalFormat("#0.000").format(bmi));

    }

    @Override
    protected void onStart() {
        super.onStart();
        ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this,"please wait",
                "processing",true);
        CountDownTimer timer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();
            }
        }.start();
    }
}