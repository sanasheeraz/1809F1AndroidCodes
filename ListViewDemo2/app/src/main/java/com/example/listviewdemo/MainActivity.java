package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lstSt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] stu={"Humza","Ibrahim","Masood","Tuba","Nida"};

        lstSt=findViewById(R.id.lstStudent);

        ArrayAdapter<String> dataAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,stu);
        lstSt.setAdapter(dataAdapter);

        lstSt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(MainActivity.this,stu[position] , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("studentName",stu[position]);
                startActivity(intent);
            }
        });
    }
}