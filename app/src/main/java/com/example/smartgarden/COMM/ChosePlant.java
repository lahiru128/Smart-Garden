package com.example.smartgarden.COMM;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

import com.example.smartgarden.FBDB.Firebase;
import com.example.smartgarden.IS.AdapterPlants;
import com.example.smartgarden.MODELS.Plant;
import com.example.smartgarden.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChosePlant extends AppCompatActivity {
    List<Plant> list;
    GridView gvPlants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_plant);

        initViews();
        populateViews();

    }

    private void populateViews() {

        list = new ArrayList<>();
        Firebase fb = new Firebase("plant_tb");

        // Read from the database
        fb.getMyRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Plant user = postSnapshot.getValue(Plant.class);
                    list.add(user);
                }

                gvPlants.setAdapter(new AdapterPlants(list, ChosePlant.this));

//                for (int i=0; i<list.size();i++)
//                {
//                    System.out.println("nenenenenene" + list.get(i).getDesc());
//                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("not nice", "Failed to read value.", error.toException());
            }
        });


    }

    private void initViews() {
        gvPlants = findViewById(R.id.gv_cp_plantslist);
    }
}