package com.example.smartsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class SurveyPlanner extends AppCompatActivity {

    static ArrayList<ArrayList<Marker>> polygons;
    static ArrayList<ArrayList<LatLng>> storeIDs;
    static ArrayList<String> areaNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_planner);
        polygons = new ArrayList<>();
        areaNames = new ArrayList<>();
        storeIDs = new ArrayList<>();
        TextView surveyArea = (TextView) findViewById(R.id.markSurveyView);
        TextView conductSurvey = (TextView) findViewById(R.id.conductSurveyView);
        TextView viewSurvey = (TextView) findViewById(R.id.surveyProgressView);

        surveyArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("service", "0");
                startActivity(intent);
            }
        });
        conductSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConductSurvey.class);
                startActivity(intent);
            }

        });
        Log.i("polygons", "" + polygons.size());

    }

    public void onOk(View view) {
        Log.i("polygons", "" + polygons.size());

    }
}
