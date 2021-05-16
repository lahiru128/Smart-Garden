package com.example.smartgarden.TRACKING;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartgarden.COMM.ChosePlant;
import com.example.smartgarden.FBDB.Firebase;
import com.example.smartgarden.FBDB.SQLiteDB;
import com.example.smartgarden.IS.AdapterPlants;
import com.example.smartgarden.IS.InformationSystem;
import com.example.smartgarden.MODELS.CurrentTrack;
import com.example.smartgarden.MODELS.PTrack;
import com.example.smartgarden.MODELS.Plant;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;
import com.example.smartgarden.SettingsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrackingMain extends AppCompatActivity {

    Button btnNewPlant;
    ListView lvCurrent;
    List<CurrentTrack> list;
    TextView tvClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_main);

        intViews();
        setListeners();
        populateViews();
    }



    private void populateViews() {
        SQLiteDB sqLiteDB = new SQLiteDB(TrackingMain.this);
        List<PTrack> listpt = new ArrayList<>();
        listpt = sqLiteDB.getAllTasks();

        for (int i=0; i<listpt.size(); i++)
            System.out.println("hhfhdhfdhfhd" + listpt.get(i).getPid());

        lvCurrent.setAdapter(new AdapterCurrent(listpt, TrackingMain.this));

//        list = new ArrayList<>();
//        Firebase fb = new Firebase("tracking_tb");
//
//        // Read from the database
//        fb.getMyRef().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    CurrentTrack trk = postSnapshot.getValue(CurrentTrack.class);
//                    list.add(trk);
//                }
//                lvCurrent.setAdapter(new AdapterCurrent(list, TrackingMain.this));
//                for (int i=0; i<list.size();i++)
//                {
//                    System.out.println("nenenenenene" + list.get(i).getInitial_date());
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("not nice", "Failed to read value.", error.toException());
//            }
//        });
    }
    private void setListeners() {
        btnNewPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ChosePlant.class);
                startActivity(i);
            }
        });

        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SQLiteDB(TrackingMain.this).truncateTable(SQLiteDB.TABLEPT, SQLiteDB.CREATE_TPT);
                new SQLiteDB(TrackingMain.this).truncateTable(SQLiteDB.TABLEALLPT, SQLiteDB.CREATE_TPT_ALL);
                startActivity(new Intent(getApplicationContext(), TrackingMain.class));
                finish();
            }
        });
    }
    private void intViews() {
        btnNewPlant = findViewById(R.id.btn_trkmain_newplant);
        lvCurrent = findViewById(R.id.lv_trkmain_currentprogress);
        tvClear = findViewById(R.id.tv_trkmain_clear);
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
                startActivity(new Intent(TrackingMain.this, MainDashboard.class));
                return true;
            case R.id.action_logout:
                startActivity(new Intent(TrackingMain.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}