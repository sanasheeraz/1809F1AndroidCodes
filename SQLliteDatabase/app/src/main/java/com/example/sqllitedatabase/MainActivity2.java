package com.example.sqllitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView lstStudent;
    public static DatabaseHelper db;
    ArrayList<Student> stList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lstStudent=findViewById(R.id.lstStudents);
        db=new DatabaseHelper(MainActivity2.this);

        stList=new ArrayList<Student>();
        Cursor cursor=db.getAllData();

        while(cursor.moveToNext())
        {
            int id= cursor.getInt(cursor.getColumnIndex("st_id"));
            String name= cursor.getString(cursor.getColumnIndex("st_name"));
            String course= cursor.getString(cursor.getColumnIndex("st_course"));
            int fees= cursor.getInt(cursor.getColumnIndex("st_fees"));

            Student st=new Student(id,name,course,fees);
            stList.add(st);
        }

        CustomAdapter custom=new CustomAdapter(MainActivity2.this,R.layout.activity_my_list_items,stList);
        lstStudent.setAdapter(custom);
    }

}
