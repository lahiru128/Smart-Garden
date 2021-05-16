package com.example.smartgarden.IS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.smartgarden.COMM.GeneralFix;
import com.example.smartgarden.FBDB.Firebase;
import com.example.smartgarden.MODELS.Disease;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;
import com.example.smartgarden.ApiTFLITE.ClassifierActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Diseases extends AppCompatActivity {
    Button btnScan;
    ListView lvDis;
    List<Disease> list;
    List<Disease> d = new ArrayList<>();
    SharedPreferences sharedPreferences;
    String disType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);

        initSharedPrefs();

        new GeneralFix().initializePrefs(this);
        initViews();
        populateViews();
        setListeners();
    }

    private void initSharedPrefs() {
        sharedPreferences = getSharedPreferences(MainDashboard.MyPREFERENCES, Context.MODE_PRIVATE);
        disType = sharedPreferences.getString(DiseaseType.d_type_key, "null");
    }

    private void populateViews() {
        list = new ArrayList<>();
        Firebase fb = new Firebase("disease_tb");

        fb.getMyRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Disease disease = postSnapshot.getValue(Disease.class);
                    list.add(disease);
                }


                for (int i = 0; i < list.size(); i++)
                {
                    String pidd = list.get(i).getDplant();
                    String effect = list.get(i).getDeffect();

                    if (disType.equals("leaf")){
                        if (pidd.equals(sharedPreferences.getString("pid", "")) && (effect.equals("leaf") || effect.equals("Leaf"))){
                            d.add(list.get(i));
                        }
                    }
                    else if (disType.equals("fruit")){
                        if (pidd.equals(sharedPreferences.getString("pid", "")) && (effect.equals("fruit") || effect.equals("Fruit"))){
                            d.add(list.get(i));
                        }
                    }
                }
                lvDis.setAdapter(new AdapterDiseases(d, Diseases.this));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("not nice", "Failed to read value.", error.toException());
            }
        });
    }

    private void setListeners() {
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences.getString("pid", "").equals("p0002") && disType.equals("fruit")){
                    Toast.makeText(Diseases.this, "Yet to be built!", Toast.LENGTH_SHORT).show();
                }
                else if (d.size()==0)
                    Toast.makeText(Diseases.this, "No diseases found!", Toast.LENGTH_SHORT).show();
                else {
                    startActivity(new Intent(getApplicationContext(), ClassifierActivity.class));
                }
            }
        });
    }

    private void initViews() {
        btnScan = findViewById(R.id.btn_dis_scan);
        lvDis = findViewById(R.id.lv_diseases);
    }
}