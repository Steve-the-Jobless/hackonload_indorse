package com.example.smartsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginClick(View view){
        String tempmail = "example@gmail.com";
        String tempPass= "12345678";
        EditText emailView= (EditText) findViewById(R.id.emailView);
        EditText passView = (EditText) findViewById(R.id.passwordView);
        if(emailView.getText().toString().equals(tempmail)&& passView.getText().toString().equals(passView.getText().toString())){
            Intent intent = new Intent(this, DashboardActivity.class );
            startActivity(intent);
        }


    }
}
