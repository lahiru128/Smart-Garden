package com.example.smartgarden.IS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;

public class DiseaseType extends AppCompatActivity {
    Button btnleaf, btnFruit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static String d_type_key = "d_type_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_type);

        initSharedPrefs();
        initViews();
        setListners();
    }

    private void initSharedPrefs() {
        sharedPreferences = getSharedPreferences(MainDashboard.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void setListners() {
        btnleaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleIntent(0);
            }
        });

        btnFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleIntent(1);
            }
        });
    }

    private void handleIntent(int i) {
        if (i==0) {
            editor.putString(d_type_key, "leaf");
        }
        else {
            editor.putString(d_type_key, "fruit");
        }
        editor.commit();
        startActivity(new Intent(getApplicationContext(), Diseases.class));
    }

    private void initViews() {
        btnleaf = findViewById(R.id.btn_distype_leaf);
        btnFruit = findViewById(R.id.btn_distype_fruit);
    }


}