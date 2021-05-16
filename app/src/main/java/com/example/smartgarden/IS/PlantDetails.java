package com.example.smartgarden.IS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartgarden.MODELS.Disease;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.R;

public class PlantDetails extends AppCompatActivity {

    Button btnA, btnB, btnC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details);

        initViews();
        setListeners();
        populateViews();
    }

    private void setListeners() {
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Diseases.class);
                startActivity(i);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DiseaseControl.class);
                startActivity(i);
            }
        });
    }

    private void populateViews() {
        
    }


    private void initViews() {
//        btnA = findViewById(R.id.btn_pldet_one);
        btnB = findViewById(R.id.btn_pldet_two);
        btnC = findViewById(R.id.btn_pldet_three);
    }
}