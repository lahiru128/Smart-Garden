package com.example.smartgarden.TRACKING;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgarden.COMM.GeneralFix;
import com.example.smartgarden.R;

public class Suggestions extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    GeneralFix gf;
    String level;
    TextView textView, btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        initViews();
        initSharedPrefs();
        initGlobalVariables();
        populateViews();
        setListeners();

    }

    private void initGlobalVariables() {
        level = "";

        level = sharedPreferences.getString("sug", "null");
    }

    private void populateViews() {
        if (level.equals("sug_tomato")){
            textView.setText("1. Add Fertilizers \n - Murate of potash (K20) \n - Triple Super Phosphate or Urea \n\n 2. Check for diseases (Go to Diseases)");
        }
        else if(level.equals("sug_tomato2")){
            textView.setText("1. Wait for another few days and check again \n\n 2. Check for diseases (Go to Diseases)");
        }
        else if (level.equals("sug_tomato1"))
            textView.setText("1. Keep Watering for another two to three days.");
        else if (level.equals("sug_tomato3"))
            textView.setText("1. Keep Watering amd fertilizing for another three days.");
        else if (level.equals("sug_ch1"))
            textView.setText("1. Wait for few more days \n\n2. Make sure the soil in the container is damp");
        else if (level.equals("sug_ch2"))
            textView.setText("1. Put the plant in the light \n\n2. Keep Watering \n\n3. Wait for few days to check");
        else if (level.equals("sug_ch3"))
            textView.setText("1. Put the plant into the sunlight \n\n2. Check for any diseases \n\n3. Add compost to the plant \n\n4. Keep Watering \n\n5. Wait for few days to check");
        else if (level.equals("sug_ch4"))
            textView.setText("1. Check for diseases \n\n2. Put more fertilizers \n\n3. Check whether the pot space is too small");
        else if (level.equals("sug_ch5"))
            textView.setText("1. Add more chillie fertilizers \n\n2. Check for any diseases");
        else if (level.equals("sug_ch6"))
            textView.setText("1. Add more chillie fertilizers \n\n2. Check for any diseases");
        else if (level.equals("sug_ch7"))
            textView.setText("1. Put fertilizers \n\n2. Wait for a few more days");
    }

    private void setListeners() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TrackGrowth.class));
                finish();
            }
        });
    }

    private void initViews() {
        textView = findViewById(R.id.tv_suggestions);
        btn = findViewById(R.id.btn_suggestions);
    }

    private void initSharedPrefs() {
        gf = new GeneralFix();
        gf.initializePrefs(this);
        sharedPreferences = gf.getSharedPreferences();
    }


}