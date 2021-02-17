package com.example.customlistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<String> {

    String[] stName;
    String[] stContact;
    Integer[] stImage;
    Activity context;
    int resource;

    public CustomAdapter(@NonNull Activity context, int resource, String[] stName,String[] stContact,Integer[] stImage) {
        super(context, resource,stName);
        this.stName=stName;
        this.stContact=stContact;
        this.stImage=stImage;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(resource,null,true);

        TextView txtName= rowView.findViewById(R.id.txtName);
        TextView txtcontact= rowView.findViewById(R.id.txtContact);
        ImageView imgView= rowView.findViewById(R.id.img);

        txtName.setText(stName[position]);
        txtcontact.setText(stContact[position]);
        imgView.setImageResource(stImage[position]);

        return rowView;
    }
}
