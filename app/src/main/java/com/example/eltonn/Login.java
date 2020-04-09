package com.example.eltonn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashMap;

//import android.widget.Toolbar;

public class Login extends AppCompatActivity {
     EditText etUsername;
     EditText etPassword;
     ImageButton btnLogin;
     Button btnSignUp;
     Toolbar toolbar;
    Boolean checkEditText;
    String finalResult;
    String HttpURL = "https://polypous-segment.000webhostapp.com/User/user/UserLogin.php";
    String userloginusername, userloginpassword;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Store login information
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (ImageButton) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add php validation codes
                CheckEditTextIsEmptyOrNot();

                if (checkEditText = true) {
                    UserLoginFunction(userloginusername, userloginpassword);

                    Intent i = new Intent(Login.this, HomePage.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Login.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Login.this, Signup.class);
                startActivity(i2);
            }
        });


    }

    public void CheckEditTextIsEmptyOrNot(){
        userloginusername = etUsername.getText().toString();
        userloginpassword = etPassword.getText().toString();

        if(TextUtils.isEmpty(userloginusername) || TextUtils.isEmpty(userloginpassword))
        {
            checkEditText = false;
        }
        else {

            checkEditText = true ;
        }
    }

    public void UserLoginFunction(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Login.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();

                if(httpResponseMsg.equalsIgnoreCase("Data Matched")){
                    finish();

                    Intent intent = new Intent(Login.this, HomePage.class);

                    intent.putExtra("username","username");

                    startActivity(intent);

                }
                else{

                    Toast.makeText(Login.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("username",params[0]);

                hashMap.put("password",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(username,password);
    }

}


