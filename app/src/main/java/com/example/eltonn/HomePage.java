package com.example.eltonn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    Button btnBooking, btnBMI, btnLogOut, btnWR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btnBooking = findViewById(R.id.button4);
        btnBMI = findViewById(R.id.button5);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnWR = findViewById(R.id.buttonWR);

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Booking.class);
                startActivity(i);
            }
        });

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), BMICalculator.class);
                startActivity(i);
            }
        });
        btnWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), WorkoutRoutineActivity.class);
                startActivity(i);
            }
        });
    }
    public void logOut(View v) {
        Intent logOut = new Intent(this, Login.class);
        startActivity(logOut);
    }
}
