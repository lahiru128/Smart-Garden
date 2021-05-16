package com.example.smartgarden.IS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartgarden.COMM.ChosePlantAction;
import com.example.smartgarden.MODELS.Disease;
import com.example.smartgarden.MODELS.Plant;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;

import java.util.List;

public class AdapterDiseases extends BaseAdapter {

    List<Disease> listDisease;
    Context context;
    LayoutInflater layoutInflater;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public AdapterDiseases(List<Disease> listDisease, Context context) {
        this.listDisease = listDisease;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        sharedPreferences = context.getSharedPreferences(MainDashboard.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

//        System.out.println("opopopo" + listDisease.get(0).getName() + listDisease.get(0).getDesc());
    }

    @Override
    public int getCount() {
        return listDisease.size();
    }

    @Override
    public Object getItem(int i) {
        return listDisease.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) 0.0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewrow;

        viewrow = layoutInflater.inflate(R.layout.item_disease,null);

        TextView diseasename = viewrow.findViewById(R.id.tv_dis_name);
        TextView diseaseaffect = viewrow.findViewById(R.id.tv_dis_affecton);
        TextView diseasedesc = viewrow.findViewById(R.id.tv_dis_description);
        ImageView disimage = viewrow.findViewById(R.id.iv_dis);
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(listPlant.get(i).getImage());

        final String IMAGE_URL = listDisease.get(i).getDimage();

        Glide.with(context)
                .load(IMAGE_URL)
                .into(disimage);

        diseasename.setText(listDisease.get(i).getDname());
        diseaseaffect.setText("Affect on: " + listDisease.get(i).getDeffect());
        diseasedesc.setText(listDisease.get(i).getDdesc());

        viewrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("pop_image", listDisease.get(i).getDimage());
                editor.putString("pop_name", listDisease.get(i).getDname());
                editor.putString("pop_affect", listDisease.get(i).getDeffect());
                editor.putString("pop_desc", listDisease.get(i).getDdesc());
                editor.putString("pop_control", listDisease.get(i).getDcontrol());
                editor.commit();
                context.startActivity(new Intent(context.getApplicationContext(), Popup.class));

            }
        });

        return viewrow;
    }
}
