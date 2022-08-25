package com.tolgacobanoglu.bmicalculatorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tolgacobanoglu.bmicalculatorproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding; //it has more benefits than findViewByID more easy to find UI objects there are less codes and don't declare objects
    private BMI bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        activityMainBinding.buttonCalculate.setOnClickListener(v -> {
            String height = activityMainBinding.editTextHeight.getText().toString().replace(',','.'); //protect application if user wants to enter "12,8" program will throw exception
            String weight = activityMainBinding.editTextWeight.getText().toString().replace(',','.');
            if (height.equals("") || weight.equals(""))
            {
                Toast.makeText(MainActivity.this, "PLEASE ENTER VALID INPUTS!!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                bmi = new BMI(Double.parseDouble(weight),Double.parseDouble(height));
                activityMainBinding.buttonCalculate.setVisibility(View.INVISIBLE);
                activityMainBinding.textViewBMIResultValue.setText(String.valueOf(bmi.calculate()));
                activityMainBinding.textViewBMIResult.setText(bmi.getState());
                activityMainBinding.calculatedLayout.setVisibility(View.VISIBLE);
            }

        });

        activityMainBinding.rateStar.setOnClickListener(v -> {
            Review review = new Review(this);
            review.doRate(this);
        });


        activityMainBinding.textViewRecalculate.setOnClickListener(v -> {
            activityMainBinding.calculatedLayout.setVisibility(View.INVISIBLE);
            activityMainBinding.buttonCalculate.setVisibility(View.VISIBLE);
        });
    }
}