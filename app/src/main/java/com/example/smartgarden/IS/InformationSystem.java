package com.example.smartgarden.IS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.smartgarden.COMM.ChosePlant;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;
import com.example.smartgarden.TRACKING.TrackingMain;

public class InformationSystem extends AppCompatActivity {
    Button btnChosePlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_system);

        initViews();
        setListeners();
    }

    private void setListeners() {
        btnChosePlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ChosePlant.class);
                startActivity(i);
            }
        });
    }

    private void initViews() {
        btnChosePlant = findViewById(R.id.btn_ismain_chooseplant);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main2, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                startActivity(new Intent(InformationSystem.this, MainDashboard.class));
                return true;
            case R.id.action_logout:
                startActivity(new Intent(InformationSystem.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}