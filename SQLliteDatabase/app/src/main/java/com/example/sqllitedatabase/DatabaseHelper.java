package com.example.sqllitedatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.os.IResultReceiver;

import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String Database_Name="Institute.db";
    private static final String Table_Name="Student";
    private static final String Col1="st_id";
    private static final String Col2="st_name";
    private static final String Col3="st_course";
    private static final String Col4="st_fees";
    SQLiteDatabase db;

    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, 1);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("Create table Student(st_id INTEGER PRIMARY KEY AUTOINCREMENT,st_name TEXT,st_course TEXT,st_fees INTEGER)");
        sqLiteDatabase.execSQL("Create table "+Table_Name+"("+Col1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Col2+" TEXT,"+Col3+" TEXT,"+Col4+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        this.onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name,String course,int fees)
    {
        ContentValues content=new ContentValues();
        content.put(Col2,name);
        content.put(Col3,course);
        content.put(Col4,fees);

        long result=db.insert(Table_Name,null,content);
        if(result==-1)
        {
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData()
    {
        Cursor cursor= db.rawQuery("select * from "+Table_Name,null);
        return cursor;
    }
    public boolean updateData(int id,String name,String course,int fees)
    {
        ContentValues content=new ContentValues();
        content.put(Col1,id);
        content.put(Col2,name);
        content.put(Col3,course);
        content.put(Col4,fees);

        long result=db.update(Table_Name,content,"st_id=?",new String[]{String.valueOf(id)});
        if(result==-1)
        {
            return false;
        }else {
            return true;
        }
    }
    public boolean deleteData(int id)
    {

        long result=db.delete(Table_Name,"st_id=?",new String[]{String.valueOf(id)});
        if(result==-1)
        {
            return false;
        }else {
            return true;
        }
    }
}
