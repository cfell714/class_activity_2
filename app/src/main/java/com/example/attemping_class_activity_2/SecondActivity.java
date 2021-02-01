package com.example.attemping_class_activity_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textView_answer;
    private LinearLayout linear_Layout;
    private TextView textView_answer_2;
    private TextView textView_answer_3;
    private TextView textView_answer_4;
    private TextView textView_answer_5;
    private TextView textView_noCityFound;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        linear_Layout = findViewById(R.id.linear_layout_2);


        Intent intent = getIntent();
        if(intent.getStringExtra("description") == null){
            textView_noCityFound = findViewById(R.id.textView_noCityFound);
            textView_noCityFound.setText("no city found");
        }else {
            // getting temp_min
            textView_answer = findViewById(R.id.textView_tempmin);
            textView_answer.setText("Min " + intent.getStringExtra("temp_min") + "F");

            // getting temp_max
            textView_answer_2 = findViewById(R.id.textView_tempmax);
            textView_answer_2.setText("Max " + intent.getStringExtra("temp_max") + "F");

            // getting feels_like
            textView_answer_3 = findViewById(R.id.textView_feelslike);
            textView_answer_3.setText("Feels like " + intent.getStringExtra("feels_like") + "F");

            // getting description
            textView_answer_4 = findViewById(R.id.textView_description);
            textView_answer_4.setText(intent.getStringExtra("description"));

            // getting location
            textView_answer_5 = findViewById(R.id.textView_location);
            textView_answer_5.setText(intent.getStringExtra("name"));
        }

    }

}
