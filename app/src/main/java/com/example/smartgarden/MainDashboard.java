package com.example.smartgarden;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.smartgarden.IS.InformationSystem;
import com.example.smartgarden.TRACKING.TrackingMain;
import com.example.smartgarden.fragments.MProfileFragment;
import com.example.smartgarden.fragments.ProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainDashboard extends AppCompatActivity implements View.OnClickListener {

    Button mcommunitybtn, mprofilebtn, tracking, disease, about;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences.Editor editor;

    public static final String spkey_module = "spkey_module";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        mcommunitybtn = findViewById(R.id.mcommunitybtn);
        tracking = findViewById(R.id.tracking);
        disease = findViewById(R.id.disease);
        about = findViewById(R.id.about);

        mprofilebtn = findViewById(R.id.mprofilebtn);
        mprofilebtn.setOnClickListener(this);
        
        mcommunitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainDashboard.this, DashboardActivity.class));

            }
        });

        tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDashboard.this, TrackingMain.class));

            }
        });

        disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDashboard.this, InformationSystem.class));

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.mprofilebtn) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new MProfileFragment()).commit();
            mprofilebtn.setVisibility(View.GONE);
            mcommunitybtn.setVisibility(View.GONE);
            tracking.setVisibility(View.GONE);
            disease.setVisibility(View.GONE);
            about.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main3, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
                startActivity(new Intent(MainDashboard.this, MainActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}