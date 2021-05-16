package com.example.smartgarden.IS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;

public class PopDiseaseControl extends AppCompatActivity {
    TextView tvName, tvMethod;
    SharedPreferences sharedPreferences;
    String des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_disease_control);

        initSharedPrefs();
        initViews();
        populateViews();
    }

    private void initSharedPrefs() {
        sharedPreferences = getSharedPreferences(MainDashboard.MyPREFERENCES, Context.MODE_PRIVATE);

        des = sharedPreferences.getString("pop_control", "null");
        des = des.replaceAll("/n", "\n");
    }

    private void populateViews() {
        tvName.setText(sharedPreferences.getString("pop_name", "null"));
        tvMethod.setText(des);
    }

    private void initViews() {
        tvName = findViewById(R.id.tv_discon_name);
        tvMethod = findViewById(R.id.tv_discon_method);
    }
}