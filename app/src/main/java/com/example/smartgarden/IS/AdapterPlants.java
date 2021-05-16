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
import com.example.smartgarden.MODELS.Plant;
import com.example.smartgarden.MainActivity;
import com.example.smartgarden.MainDashboard;
import com.example.smartgarden.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPlants extends BaseAdapter {

    List<Plant> listPlant;
    Context context;
    LayoutInflater layoutInflater;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public AdapterPlants(List<Plant> listPlant, Context context) {
        this.listPlant = listPlant;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        sharedPreferences = context.getSharedPreferences(MainDashboard.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

//        System.out.println("opopopo" + listPlant.get(0).getName() + listPlant.get(0).getDesc());
    }

    @Override
    public int getCount() {
        return listPlant.size();
    }

    @Override
    public Object getItem(int i) {
        return listPlant.get(i);
    }

    @Override
    public long getItemId(int i) {
        for (int j = 0; j < listPlant.size(); j++){

        }
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

//        view = layoutInflater.inflate(R.layout.item_choseplant, null);
//        System.out.println("wewewewewew" + listPlant.get(i).getName() + listPlant.get(0).getName());

        View viewrow;
        viewrow = layoutInflater.inflate(R.layout.item_choseplant,null);

        TextView plantname = viewrow.findViewById(R.id.tv_item_choseplant);
        ImageView image =viewrow.findViewById(R.id.iv_item_choseplant);
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(listPlant.get(i).getImage());

        final String IMAGE_URL = listPlant.get(i).getImage();

        Glide.with(context)
                .load(IMAGE_URL)
                .into(image);

        plantname.setText(listPlant.get(i).getName());

        viewrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("pid", listPlant.get(i).getId_pl());
                editor.putString("pdesc", listPlant.get(i).getDesc());
                editor.putString("ptitle", listPlant.get(i).getName());
                editor.commit();
                Intent i = new Intent(context.getApplicationContext(), ChosePlantAction.class);
                context.startActivity(i);
            }
        });

        return viewrow;
    }

}
