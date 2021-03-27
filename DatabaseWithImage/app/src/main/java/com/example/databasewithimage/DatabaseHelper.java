package com.example.databasewithimage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String Database_Name="Company.db";
    public static String Table1_Name="Department";
    public static String T1_Col1="Dept_Id";
    public static String T1_Col2="Dept_Name";

    public static String Table2_Name="Employee";
    public static String T2_Col1="Emp_Id";
    public static String T2_Col2="Emp_Name";
    public static String T2_Col3="Emp_Email";
    public static String T2_Col4="Emp_Password";
    public static String T2_Col5="Emp_Image";
    public static String T2_Col6="Emp_Depart";

    SQLiteDatabase db;

    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, 1);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+Table1_Name+" ("+T1_Col1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+T1_Col2+" TEXT)");
        sqLiteDatabase.execSQL("create table "+Table2_Name+" ("+T2_Col1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+T2_Col2+" TEXT,"+T2_Col3+" TEXT,"+T2_Col4+" TEXT,"+T2_Col5+" BLOB,"+T2_Col6+" INTEGER, Foreign key ("+T2_Col6+") references "+Table1_Name+"("+T1_Col1+"))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+Table1_Name);
        sqLiteDatabase.execSQL("DROP TABLE "+Table2_Name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertDeprt(String name)
    {
        ContentValues content=new ContentValues();
        content.put(T1_Col2,name);

        long result=db.insert(Table1_Name,null,content);
        if(result==-1)
        {
            return  false;
        }else
        {
            return true;
        }
    }

    public Cursor getAllDept()
    {
        Cursor cursor=db.rawQuery("select * from "+Table1_Name,null);
        return cursor;
    }
    public boolean insertEmployee(String name,String email,String password,byte[] image,int dept)
    {
        ContentValues content=new ContentValues();
        content.put(T2_Col2,name);
        content.put(T2_Col3,email);
        content.put(T2_Col4,password);
        content.put(T2_Col5,image);
        content.put(T2_Col6,dept);

        long result=db.insert(Table2_Name,null,content);
        if(result==-1)
        {
            return  false;
        }else
        {
            return true;
        }
    }
}
