package com.example.eltonn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Booking extends AppCompatActivity {
    ListView lv;
    ArrayList<Gym> gymal;
    ArrayAdapter aa;
    ImageButton btnBack;
    Button btnPlus;
    EditText etnewGymname, etnewGymlocation, etnewGymaddress, etnewGymvacancies;
    RadioGroup rgnewGymratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        btnBack = findViewById(R.id.btnBack);
        btnPlus = findViewById(R.id.btnPlus);
        etnewGymname = findViewById(R.id.etnewGymname);
        etnewGymlocation = findViewById(R.id.etnewGymlocation);
        etnewGymaddress = findViewById(R.id.etnewGymaddress);
        etnewGymvacancies = findViewById(R.id.etnewGymvacancies);
        rgnewGymratings = findViewById(R.id.rgnewGymratings);

        DatabaseHelper db = new DatabaseHelper(Booking.this);

        lv = findViewById(R.id.gymlv);
        gymal = new ArrayList<Gym>();

        gymal.clear();
        gymal.addAll(db.getAllGyms());
        db.close();

        aa = new CustomAdapter(Booking.this, R.layout.rowgym, gymal);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent moveToEditActivity = new Intent(Booking.this, modifyBooking.class);
                Gym data = gymal.get(position);
                moveToEditActivity.putExtra("data", data);
                //startActivity(moveToEditActivity);
                startActivityForResult(moveToEditActivity, 9);
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newgymname = etnewGymname.getText().toString();
                String newgymlocation = etnewGymlocation.getText().toString();
                String newgymaddress = etnewGymaddress.getText().toString();
                String newgymvacancies = etnewGymvacancies.getText().toString();

                int selectedButtonId = rgnewGymratings.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedButtonId);
                Integer stars = Integer.parseInt(rb.getText().toString());
                if (newgymname.equalsIgnoreCase("") || newgymlocation.equalsIgnoreCase("")
                        || newgymaddress.equalsIgnoreCase("") || newgymvacancies.equalsIgnoreCase("")
                ){
                    Toast.makeText(Booking.this, "Field(s) is/are empty, please fill up everything",
                            Toast.LENGTH_LONG).show();
                }else{
                    DatabaseHelper dbhinsert = new DatabaseHelper(Booking.this);
                    long row_affected = dbhinsert.insertGym(newgymname, newgymlocation, newgymaddress, newgymvacancies, stars);
                    dbhinsert.close();
                    if (row_affected != -1){
                        Toast.makeText(Booking.this, "Insert successful",
                                Toast.LENGTH_SHORT).show();
                        etnewGymname.setText("");
                        etnewGymlocation.setText("");
                        etnewGymaddress.setText("");
                        etnewGymvacancies.setText("");
                        rgnewGymratings.clearCheck();
                        rgnewGymratings.check(R.id.radioButton);
                    }

                    lv = (ListView)findViewById(R.id.gymlv);
                    gymal = new ArrayList<Gym>();
                    DatabaseHelper dbhretrieve = new DatabaseHelper(Booking.this);
                    gymal.clear();
                    gymal.addAll(dbhretrieve.getAllGyms());
                    dbhretrieve.close();
                    aa = new CustomAdapter(Booking.this, R.layout.rowgym, gymal);
                    lv.setAdapter(aa);
                    
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 9){
            lv = (ListView)findViewById(R.id.gymlv);
            gymal = new ArrayList<Gym>();
            DatabaseHelper dbh = new DatabaseHelper(Booking.this);
            gymal.clear();
            gymal.addAll(dbh.getAllGyms());
            dbh.close();
            aa = new CustomAdapter(this, R.layout.rowgym, gymal);

            lv.setAdapter(aa);
        }
    }

    public void goBack(View v) {
        finish();
    }
}
