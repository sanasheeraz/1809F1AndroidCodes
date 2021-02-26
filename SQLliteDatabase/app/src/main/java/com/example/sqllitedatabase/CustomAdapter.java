package com.example.sqllitedatabase;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
public class CustomAdapter extends ArrayAdapter<Student> {

    private Activity context;
    private ArrayList<Student> students;
    int resource;

    public CustomAdapter(@NonNull Activity context,int resource, ArrayList<Student> students) {
        super(context,R.layout.activity_my_list_items,students);
        this.context = context;
        this.students = students;
        this.resource=resource;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=inflater.inflate(resource,null,true);

        final TextView txtId=rowView.findViewById(R.id.txtId);
        final TextView txtCourse=rowView.findViewById(R.id.txtCourse);
        final TextView txtName=rowView.findViewById(R.id.txtName);
        final TextView txtFees=rowView.findViewById(R.id.txtFees);

        txtId.setText(String.valueOf(students.get(position).id));
        txtName.setText(String.valueOf(students.get(position).name));
        txtCourse.setText(String.valueOf(students.get(position).course));
        txtFees.setText(String.valueOf(students.get(position).fees));

        return  rowView;
    }


}