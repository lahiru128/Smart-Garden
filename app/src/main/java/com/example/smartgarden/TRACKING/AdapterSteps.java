package com.example.smartgarden.TRACKING;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartgarden.MODELS.SinglePTrack;
import com.example.smartgarden.models.Step;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;
import com.example.smartgarden.TRACKERS.Tracker;

import java.util.ArrayList;
import java.util.List;

public class AdapterSteps extends BaseAdapter {
    List<SinglePTrack> listCurrent;
    Context context;
    LayoutInflater layoutInflater;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    List<Step> list;

    public AdapterSteps(List<SinglePTrack> listCurrent, Context context){
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
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View viewrow;

        viewrow = layoutInflater.inflate(R.layout.item_tg_steps,null);

        TextView tvStep = viewrow.findViewById(R.id.tv_step_item_title);
        TextView tvdatecomplete = viewrow.findViewById(R.id.tv_step_item_complete);

        String s = listCurrent.get(i).getCompletedstep();
        final String d = listCurrent.get(i).getCompleteddate();



        if (listCurrent.get(i).getPid().equals("p0001")){
           list = new Tracker().getTomatoStepslist();
        }
        else if (listCurrent.get(i).getPid().equals("p0002"))
            list = new Tracker().getChiliStepList();
        else if (listCurrent.get(i).getPid().equals("p0003"))
            list = new Tracker().getBrinjalStepList();


        for (int ii = 0; ii<list.size(); ii++){
            if (list.get(ii).getKey().equals(s)){
                tvStep.setText(list.get(ii).getTitle());
                tvdatecomplete.setText("Completed On: " + d);
            }
        }

        return viewrow;
    }
}
