package com.example.smartgarden.TRACKING;
import com.example.smartgarden.COMM.GeneralFix;
import com.example.smartgarden.MODELS.Step;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.TRACKERS.Tracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartgarden.FBDB.SQLiteDB;
import com.example.smartgarden.MODELS.PTrack;
import com.example.smartgarden.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCurrent extends BaseAdapter {

    List<PTrack> listCurrent;
    Context context;
    LayoutInflater layoutInflater;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public AdapterCurrent(List<PTrack> listCurrent, Context context) {
        this.listCurrent = listCurrent;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        sharedPreferences = context.getSharedPreferences(MainDashboard.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    @Override
    public int getCount() {
        return listCurrent.size();
    }

    @Override
    public Object getItem(int i) {
        return listCurrent.get(i);
    }

    @Override
    public long getItemId(int i) {
        for (int j = 0; j < listCurrent.size(); j++){

        }
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View viewrow;

        viewrow = layoutInflater.inflate(R.layout.item_current_tracking,null);

        TextView plantname = viewrow.findViewById(R.id.tv_tm_pid);
        TextView datep = viewrow.findViewById(R.id.tv_tm_trackdate);
        TextView nextstep = viewrow.findViewById(R.id.tv_tm_nextstep);

        SQLiteDB sqLiteDB = new SQLiteDB(context);
        sqLiteDB.getTrack(listCurrent.get(i).getId());

        plantname.setText("Plant " + listCurrent.get(i).getId() + " - " + new GeneralFix().getPlantName(listCurrent.get(i).getPid()));
        datep.setText(listCurrent.get(i).getInitialdate());

        nextstep.setText(getNextStep(listCurrent.get(i).getPid(),listCurrent.get(i).getLastcompletedstep()));

        viewrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(context.getApplicationContext(), TrackGrowth.class);

                editor.putInt("ptid", listCurrent.get(i).getId());
                editor.putString("pid", listCurrent.get(i).getPid());
                editor.putString("idate", listCurrent.get(i).getInitialdate());
                editor.putString("lstep", listCurrent.get(i).getLastcompletedstep());
                editor.putString("lsdate", listCurrent.get(i).getLaststepdate());
                editor.commit();
                context.startActivity(ii);

                System.out.println("Before:" + listCurrent.get(i).getLastcompletedstep());

            }
        });
        return viewrow;
    }

    private String getNextStep(String pid, String currentstep) {

        String step = "";

        List<Step> steplist = new ArrayList<>();

        if (pid.equals("p0001"))
            steplist = new Tracker().getTomatoStepslist();
        else if (pid.equals("p0002"))
            steplist = new Tracker().getChiliStepList();

        for (int i = 0; i < steplist.size(); i++){
            System.out.println(i + " " + steplist.size() + " " + steplist.get(i).getTitle() +"\n");

            if (steplist.get(i).getKey().equals(currentstep))
                if (i == steplist.size()-1)
                    step = steplist.get(i).getTitle();
                else
                    step = steplist.get(i+1).getTitle();
        }

        return step;
    }

}
