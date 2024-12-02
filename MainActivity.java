package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText loanAmount, interestRate, tenure;
    Button calculateBtn;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        loanAmount = findViewById(R.id.loanAmount);
        interestRate = findViewById(R.id.interestRate);
        tenure = findViewById(R.id.tenure);
        calculateBtn = findViewById(R.id.calculateBtn);
        result = findViewById(R.id.result);

        // Set button click listener
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateEMI();
            }
        });
    }

    private void calculateEMI() {
        try {
            // Get input values
            double principal = Double.parseDouble(loanAmount.getText().toString());
            double rate = Double.parseDouble(interestRate.getText().toString());
            int time = Integer.parseInt(tenure.getText().toString());

            // Convert annual interest rate to monthly and calculate EMI
            double monthlyRate = rate / (12 * 100);
            double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, time)) /
                    (Math.pow(1 + monthlyRate, time) - 1);

            // Display result
            result.setText(String.format("Your EMI is: ₹ %.2f", emi));
        } catch (Exception e) {
            result.setText("Please enter valid inputs!");
        }
    }
}
