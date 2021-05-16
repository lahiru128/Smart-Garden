package com.example.smartgarden.COMM;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.example.smartgarden.FBDB.Firebase;
import com.example.smartgarden.IS.AdapterPlants;
import com.example.smartgarden.MODELS.Plant;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.TRACKING.TrackGrowth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeneralFix {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public GeneralFix() {
    }

    public String formatDT(java.util.Date dt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String dateTime = simpleDateFormat.format(dt);

        return dateTime;
    }

    public int getDuration(int pd, String laststepdate, java.util.Date dt){
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").parse(laststepdate);
            dt = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").parse(new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(dt));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("error in parsing date!");
        }

        return pd - (int)( (dt.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
    }

    public void initializePrefs(Context context){
        sharedPreferences = context.getSharedPreferences(MainDashboard.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }


    public String getPlantName(String pid){

        String plantname="";
        if (pid.equals("p0001"))
            plantname = "Tomato";
        else if (pid.equals("p0002"))
            plantname = "Chillie";
        else if (pid.equals("p0003"))
            plantname = "Brinjal";
        return plantname;
    }

    public void showAlertDialog(Context context, String title, String message){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert); }
        else {
            builder = new AlertDialog.Builder(context); }
        builder.setTitle(title).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }
}
