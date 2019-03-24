package com.example.smartsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ConductSurvey extends AppCompatActivity {

    ListView listView ;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conduct_survey);
        listView = (ListView) findViewById(R.id.areaListView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, SurveyPlanner.areaNames);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SurveyMapsActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }
}
