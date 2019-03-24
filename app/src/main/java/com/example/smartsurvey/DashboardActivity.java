package com.example.smartsurvey;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView newSurveyView = (TextView) findViewById(R.id.newSurveyView);
        TextView viewSurveyView =(TextView) findViewById(R.id.viewSurveyView);
        newSurveyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SurveyPlanner.class);
                startActivity(intent);
            }
        });
        //viewSurveyView.setOnClickListener(new View.OnClickListener() {
            /*@Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ExampleSurvey.class);
                startActivity(intent);
            }
        });*/

    }


}
