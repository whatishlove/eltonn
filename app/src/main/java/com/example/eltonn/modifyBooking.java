package com.example.eltonn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class modifyBooking extends AppCompatActivity {
    EditText tvGymID, etGymName, etGymLocation, etGymAddress, etGymVacancies;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rgStars;
    RadioButton rb, rb1, rb2, rb3, rb4, rb5;
    Gym data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifybooking);

        tvGymID = (EditText) findViewById(R.id.tvGymID);
        etGymName = (EditText) findViewById(R.id.etGymName);
        etGymLocation = (EditText) findViewById(R.id.etGymLocation);
        etGymAddress = (EditText) findViewById(R.id.etGymAddress);
        etGymVacancies = (EditText) findViewById(R.id.etGymVacancies);
        rgStars = (RadioGroup) findViewById(R.id.rgStars);
        rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);
        rb4 = (RadioButton) findViewById(R.id.radio4);
        rb5 = (RadioButton) findViewById(R.id.radio5);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent i = getIntent();
        data = (Gym) i.getSerializableExtra("data");

        tvGymID.setText(String.valueOf(data.getId()));
        etGymName.setText(data.getGymname());
        etGymLocation.setText(data.getLocation());
        etGymAddress.setText(data.getAddress());
        etGymVacancies.setText(data.getVacancies());

        if (data.getStars() == 5) {
            rb5.setChecked(true);
        } else if (data.getStars() == 4) {
            rb4.setChecked(true);
        } else if (data.getStars() == 3) {
            rb3.setChecked(true);
        } else if (data.getStars() == 2) {
            rb2.setChecked(true);
        } else {
            rb1.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbh = new DatabaseHelper(modifyBooking.this);

                int selectedButtonId = rgStars.getCheckedRadioButtonId();
                rb = findViewById(selectedButtonId);

                data.setGymname(etGymName.getText().toString());
                data.setLocation(etGymLocation.getText().toString());
                data.setAddress(etGymAddress.getText().toString());
                data.setVacancies(etGymVacancies.getText().toString());
                data.setStars(Integer.parseInt(rb.getText().toString()));
                dbh.updateGym(data);
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbh = new DatabaseHelper(modifyBooking.this);
                dbh.deleteGym(data.getId());
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
