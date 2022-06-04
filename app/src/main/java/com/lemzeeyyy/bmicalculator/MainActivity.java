package com.lemzeeyyy.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private SeekBar height_seek;
    private TextView currentHeight,currentWeight, currentAge,maleText,femaleText;
    private ImageView incrementAge, incrementWeight, decrementAge, decrementWeight;
    private Button calc_bmi;
    private double bmi = 0 ;
    private double height = 0;
    private int age = 0;
    private double weight = 0 ;
    private Metrics metrics = new Metrics();
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        femaleText = findViewById(R.id.text_female);
        maleText = findViewById(R.id.text_male);

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


        incrementWeight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mHandler.postDelayed(incrementRunnable, 0);
                return true;
            }
        });
        decrementWeight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mHandler.postDelayed(incrementRunnable, 0);
                return true;
            }
        });
        incrementAge.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mHandler.postDelayed(incrementRunnable, 0); // initial call for our handler.
                return true;
            }
        });
        decrementAge.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mHandler.postDelayed(incrementRunnable, 0); // initial call for our handler.
                return true;
            }
        });

        calc_bmi.setOnClickListener(view -> {
            getBMI();
            saveData();
            Intent intent = new Intent(MainActivity.this,BMIResultActivity.class);
            intent.putExtra("BMI",getBMI());
            startActivity(intent);

        });
        RelativeLayout male = findViewById(R.id.male);
        RelativeLayout female = findViewById(R.id.female);

    }

    private void saveData() {
        int age = Integer.parseInt(currentAge.getText().toString());
        double weight = Double.parseDouble(currentWeight.getText().toString());
        double height = metrics.getHeight();
        Metrics metric = new Metrics(weight,height,age);

    }

    private double getBMI() {
        bmi = (10000.0)*(metrics.getWeight()/Math.pow(metrics.getHeight(),2));
        Log.d("TAG", "weight: "+metrics.getWeight());
        Log.d("TAG", "age: "+metrics.getAge());
        Log.d("TAG", "height: "+metrics.getHeight());
        Log.d("TAG", "getBMI: "+Double.parseDouble(new DecimalFormat("#0.000").format(bmi)));


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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.male:
                intent.putExtra("male","Male");
                break;

            case R.id.female:
                intent.putExtra("female","Female");
                break;
        }
    }

    private Runnable incrementRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(incrementRunnable); // remove our old runnable, though I'm not really sure if this is necessary
            if(incrementAge.isPressed()) { // check if the button is still in its pressed state
                // increment the counter
                // display the updated value here, if necessary
                age++;
                metrics.setAge(age);
                currentAge.setText(String.valueOf(age));
                mHandler.postDelayed(incrementRunnable, 100); // call for a delayed re-check of the button's state through our handler. The delay of 100ms can be changed as needed.
            }else if(decrementAge.isPressed()){
                if(age!=0){
                    age--;
                }
                metrics.setAge(age);
                currentAge.setText(String.valueOf(age));
                mHandler.postDelayed(incrementRunnable, 100);
            }else if(incrementWeight.isPressed()){
                weight++;
                metrics.setWeight(weight);
                currentWeight.setText(String.valueOf(weight));
                mHandler.postDelayed(incrementRunnable, 100);
            }
            else if(decrementWeight.isPressed()){
                if(weight!=0){
                    weight--;
                }
                metrics.setWeight(weight);
                currentWeight.setText(String.valueOf(weight));
                mHandler.postDelayed(incrementRunnable, 100);
            }
            else{
                //do notthing
            }
        }
    };
}