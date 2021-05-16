package com.example.smartgarden.IS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartgarden.COMM.GeneralFix;
import com.example.smartgarden.R;

public class Popup extends AppCompatActivity {
    ImageView iv;
    TextView tvname, tveff, tvdesc;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    GeneralFix gf;
    Button btnControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup2);

        initViews();
        setPrefs();

        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int w = dm.widthPixels;
        int h = dm.heightPixels;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getWindow().setLayout((int) (w * .9), (int) (h * .9));
        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getWindow().setLayout((int) (w * .6), (int) (h * .6));
        }

        setListeners();
    }

    private void setListeners() {
        btnControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PopDiseaseControl.class));
            }
        });
    }

    private void setPrefs() {
        gf = new GeneralFix();
        gf.initializePrefs(this);
        sharedPreferences = gf.getSharedPreferences();

        tvname.setText(sharedPreferences.getString("pop_name", "null"));
        tvdesc.setText(sharedPreferences.getString("pop_desc", "null"));
        tveff.setText("Affect On: " + sharedPreferences.getString("pop_affect", "null"));
        String imageurl = sharedPreferences.getString("pop_image", "null");
        Glide.with(this).load(imageurl).into(iv);
    }

    private void initViews() {
        iv = findViewById(R.id.iv_popup);
        tvname = findViewById(R.id.tv_popup_title);
        tvdesc = findViewById(R.id.tv_popup_desc);
        tveff = findViewById(R.id.tv_popup_affect);
        btnControl = findViewById(R.id.btn_discontrol);
    }
}