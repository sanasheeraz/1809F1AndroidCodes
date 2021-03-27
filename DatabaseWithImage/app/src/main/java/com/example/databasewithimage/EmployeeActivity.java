package com.example.databasewithimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {

    Button btnAdd,btnUpload;
    EditText edName,edEmail,edPassword;
    Spinner spDept;
    DatabaseHelper databaseHelper;
    ImageView imgView;
    Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        spDept=findViewById(R.id.txtDept);
        edName=findViewById(R.id.txtName);
        edEmail=findViewById(R.id.txtEmail);
        edPassword=findViewById(R.id.txtPassword);
        imgView=findViewById(R.id.txtImage);
        btnAdd=findViewById(R.id.btnAdd);
        btnUpload=findViewById(R.id.uploadImg);

        databaseHelper=new DatabaseHelper(EmployeeActivity.this);
        ArrayList<String> deptNameList=new ArrayList<String>();
        ArrayList<Integer> deptIdList=new ArrayList<Integer>();
        Cursor cursor=databaseHelper.getAllDept();

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.T1_Col1));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.T1_Col2));
            deptNameList.add(name);
            deptIdList.add(id);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(EmployeeActivity.this,R.layout.support_simple_spinner_dropdown_item,deptNameList);
        spDept.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= edName.getText().toString();
                String email= edEmail.getText().toString();
                String password= edPassword.getText().toString();

                String dptName= spDept.getSelectedItem().toString();
                int nameId=deptNameList.indexOf(dptName);
                int deptId=deptIdList.get(nameId);

                try {
                    InputStream input=getContentResolver().openInputStream(ImageUri);
                    byte[] imgArray=Utils.getBytes(input);

                    boolean result=databaseHelper.insertEmployee(name,email,password,imgArray,deptId);
                    if(result)
                    {
                        Toast.makeText(EmployeeActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(EmployeeActivity.this, "Record Not Inserted", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200)
        {
            if(resultCode==RESULT_OK)
            {
                ImageUri=data.getData();
                try {
                    imgView.setImageBitmap(Utils.getImage(Utils.getBytes(getContentResolver().openInputStream(ImageUri))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}