package com.example.smartgarden.COMM;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgarden.FBDB.SQLiteDB;
import com.example.smartgarden.IS.DiseaseType;
import com.example.smartgarden.IS.Diseases;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;
import com.example.smartgarden.TRACKING.TrackGrowth;
import com.example.smartgarden.TRACKING.TrackingMain;

import java.util.Date;

public class ChosePlantAction extends AppCompatActivity {

    ImageView ivNewPlant, ivdis;
    TextView tvdesc, tvtitle;
    SQLiteDB sqLiteDB;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String pid, pdesc, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_plant_action);

        sharedPreferences = getSharedPreferences(MainDashboard.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        pid = sharedPreferences.getString("pid", "null");
        pdesc = sharedPreferences.getString("pdesc", "null");
        title = sharedPreferences.getString("ptitle", "null");
        initViews();
        setListeners();
    }

    private void setListeners() {
        ivNewPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("New Plant!", "Are you sure you want to create a new plant entry?");
            }
        });
        ivdis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiseaseType.class));
            }
        });
    }

    private void initViews() {
        ivNewPlant = findViewById(R.id.iv_new);
        ivdis = findViewById(R.id.iv_dis);
        tvdesc = findViewById(R.id.tv_plantactiondescription);
        tvtitle = findViewById(R.id.tv_plantactiontitle);

        tvtitle.setText(title);
        tvdesc.setText(pdesc);
    }

    public void showAlertDialog(String title, String message){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            builder = new AlertDialog.Builder(ChosePlantAction.this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        }
        else
        {
            builder = new AlertDialog.Builder(ChosePlantAction.this);
        }

        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), MainDashboard.class);
                        startActivity(i);
                        sqLiteDB = new SQLiteDB(ChosePlantAction.this);
                        sqLiteDB.addPT(pid, new GeneralFix().formatDT(new Date()));

//                        new GeneralFix().showAlertDialog(ChosePlantAction.this, "Congratulations!" , "New Plant Created successfully! Go to Tracking to follow the progress.");

                        Toast.makeText(ChosePlantAction.this, "New plant created successfully!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}