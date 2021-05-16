package com.example.smartgarden.TRACKING;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgarden.COMM.GeneralFix;
import com.example.smartgarden.FBDB.SQLiteDB;
import com.example.smartgarden.MODELS.PTrack;
import com.example.smartgarden.MODELS.SinglePTrack;
import com.example.smartgarden.MODELS.Step;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;
import com.example.smartgarden.TRACKERS.Tracker;

import java.util.ArrayList;
import java.util.List;

public class TrackGrowth extends AppCompatActivity {
    Button btnComplete, btnrbhandler;
    LinearLayout llone, llcheck;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    TextView tvnsiTitle, tvduration, tvcheckTitle;
    RadioButton rb1, rb2;

    int current_ptid;
    String lastStepCompleted;

    List contentsList;

    Tracker tm;
    SQLiteDB sqLiteDB;

    PTrack pTrack;
    List<SinglePTrack> listCurrentplantsteps;

    ListView lvLayout;

    List<Step> stepslist;
    String nextStep, nextstepID;
    private String laststepdate;

    TextView tvNotification;

    String currentPID;
    int predictedDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_growth);

        sqLiteDB = new SQLiteDB(this);
        tm = new Tracker();

        intiViews();
        initValues();
        populateValues();
        setListeners();
        Toast.makeText(this, nextstepID, Toast.LENGTH_SHORT).show();

    }

    private void handleDB() {
        pTrack = new PTrack(current_ptid);

        for (int i = 0; i < stepslist.size(); i++) {
            if (stepslist.get(i).getKey().equals(lastStepCompleted)) {
                Toast.makeText(this, stepslist.get(i + 1).getKey(), Toast.LENGTH_SHORT).show();
                sqLiteDB.updateTask(pTrack, stepslist.get(i + 1).getKey(), new GeneralFix().formatDT(new java.util.Date()));
                sqLiteDB.addPTALL(current_ptid, currentPID, stepslist.get(i + 1).getKey(), new GeneralFix().formatDT(new java.util.Date()));
            } else {
//                Toast.makeText(this, "Something's wrong!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setListeners() {
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pd = new GeneralFix().getDuration(predictedDuration, laststepdate, new java.util.Date());
//                System.out.println("check duration: " + pd);
                if(pd<=0) {
                    handleIntent();
                }
                else
                {
                    showAlertDialog("Cannot complete the action!", "There are some more days remaining still. Please wait till the scheduled number of days are done!");
                }
//                handleIntent();
            }
        });
        btnrbhandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!rb1.isChecked() && !rb2.isChecked())
                    Toast.makeText(TrackGrowth.this, "Please Respond Before Continuing!", Toast.LENGTH_SHORT).show();
                else if (rb1.isChecked())
                    handleIntent();
                else if (rb2.isChecked()) {
                    if (nextstepID.equals("check2t")){
                        handleSuggestions("sug", "sug_tomato");
                    }
                    else if (nextstepID.equals("check1t")){
                        handleSuggestions("sug", "sug_tomato1");
                    }
                    else if (nextstepID.equals("check3t")){
                        handleSuggestions("sug", "sug_tomato2");
                    }
                    else if (nextstepID.equals("check4t")){
                        handleSuggestions("sug", "sug_tomato3");
                    }
                    else if (nextstepID.equals("check1c")){
                        handleSuggestions("sug", "sug_ch1");
                    }
                    else if (nextstepID.equals("check2c")){
                        handleSuggestions("sug", "sug_ch2");
                    }
                    else if (nextstepID.equals("check3c")){
                        handleSuggestions("sug", "sug_ch3");
                    }
                    else if (nextstepID.equals("check4c")){
                        handleSuggestions("sug", "sug_ch4");
                    }
                    else if (nextstepID.equals("check5c")){
                        handleSuggestions("sug", "sug_ch5");
                    }
                    else if (nextstepID.equals("check6c")){
                        handleSuggestions("sug", "sug_ch6");
                    }
                    else if (nextstepID.equals("check7c")){
                        handleSuggestions("sug", "sug_ch7");
                    }

                }
            }
        });
    }

    private void handleSuggestions(String key, String value){
        editor.putString(key, value);
        editor.commit();
        startActivity(new Intent(getApplicationContext(), Suggestions.class));
        finish();
    }

    private void handleIntent() {
        handleDB();
        startActivity(new Intent(getApplicationContext(), TrackingMain.class));
        finish();
    }

    private void initValues() {
        currentPID = sharedPreferences.getString("pid", "none");
        current_ptid = sharedPreferences.getInt("ptid", 0);
        lastStepCompleted = sharedPreferences.getString("lstep", "");
        laststepdate = sharedPreferences.getString("lsdate", "");
        listCurrentplantsteps = new ArrayList<>();
        listCurrentplantsteps = sqLiteDB.getTrackofSinglePlant(current_ptid);

    }

    private void populateValues() {
        String ns = "";
        predictedDuration = 0;
        String isCheck = "";
        String rbtext1 = "";
        String rbtext2 = "";

        if (currentPID.equals("p0001"))
            stepslist = tm.getTomatoStepslist();
        else if (currentPID.equals("p0002"))
            stepslist = tm.getChiliStepList();
        else if (currentPID.equals("p0003"))
            stepslist = tm.getBrinjalStepList();
        else
            Toast.makeText(this, "No plant is selected!", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < stepslist.size(); i++) {
            if (stepslist.get(i).getKey().equals(lastStepCompleted)) {
                if (i == stepslist.size() - 1)
                {
                    nextstepID = stepslist.get(i).getKey();
                    ns = stepslist.get(i).getTitle();
                    predictedDuration = Integer.parseInt(stepslist.get(i).getDuration());
                    isCheck = stepslist.get(i).getDescription();
                    rbtext1 = stepslist.get(i).getOp1();
                    rbtext2 = stepslist.get(i).getOp2();
                }
                else{
                    nextstepID = stepslist.get(i+1).getKey();
                    ns = stepslist.get(i + 1).getTitle();
                    predictedDuration = Integer.parseInt(stepslist.get(i + 1).getDuration());
                    isCheck = stepslist.get(i + 1).getDescription();
                    rbtext1 = stepslist.get(i + 1).getOp1();
                    rbtext2 = stepslist.get(i + 1).getOp2();
                }

            }
        }

        nextStep = ns;

        //setting current activity

        if (isCheck.equals("bool")) {
            llone.setVisibility(View.GONE);
            llcheck.setVisibility(View.VISIBLE);
            tvcheckTitle.setText(nextStep);
            rb1.setText(rbtext1);
            rb2.setText(rbtext2);

        } else {
            llcheck.setVisibility(View.GONE);
            llone.setVisibility(View.VISIBLE);
            tvnsiTitle.setText(nextStep);
            tvduration.setText("Number of days: " + predictedDuration);
            tvNotification.setText(nextStep + ": " + String.valueOf(new GeneralFix().getDuration(predictedDuration, laststepdate, new java.util.Date())) + " days remaining");
        }

        if (nextStep.equals("nsfinal"))
            btnComplete.setClickable(false);

        //generating a list of completed steps

        List<SinglePTrack> s = new ArrayList<>();
        s = sqLiteDB.getTrackofSinglePlant(current_ptid);
        if (s.size() != 0)
            lvLayout.setAdapter(new AdapterSteps(s, this));
        else
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
    }

    private void intiViews() {
        btnComplete = findViewById(R.id.btn_ll_nsi_complete);
        tvnsiTitle = findViewById(R.id.tv_gt_ll_nsi_stepname);
        tvduration = findViewById(R.id.tv_gt_ll_nsi_desc);
        lvLayout = findViewById(R.id.lv_gt_previous_steps);
        tvNotification = findViewById(R.id.tv_tg_notification);
        llone = findViewById(R.id.ll_gt_layout_nsi);
        llcheck = findViewById(R.id.ll_check);
        tvcheckTitle = findViewById(R.id.tv_tg_checktitle);
        rb1 = findViewById(R.id.rb_tg_rb1);
        rb2 = findViewById(R.id.rb_tg_rb2);
        btnrbhandler = findViewById(R.id.btn_radiobuttonhandler);

        contentsList = new ArrayList();
        stepslist = new ArrayList<>();

        sharedPreferences = getSharedPreferences(MainDashboard.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void showAlertDialog(String title, String message){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(TrackGrowth.this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert); }
        else {
            builder = new AlertDialog.Builder(TrackGrowth.this); }
        builder.setTitle(title).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); }
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }
}