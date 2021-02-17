package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] stName={"Masood","Humza","Manzer","Ali","Uzair"};
        String[] stContact={"0313131131","0313131132","0313131134","0313131135","0313131136"};
        Integer[] stImage={R.drawable.download1,R.drawable.download2,R.drawable.download3,R.drawable.download4,R.drawable.download5};


        lst=findViewById(R.id.lstView);

        CustomAdapter custom=new CustomAdapter(MainActivity.this,R.layout.activity_my_list_item,stName,stContact,stImage);
        lst.setAdapter(custom);
    }
}