package com.example.coffee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "coffee.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {

        myDB.execSQL("create Table users(fullname Text, email Text primary key, phone Integer, password Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop Table if exists users");
    }
    public Boolean  insertData(String fullname, String email, String password)
    {
        SQLiteDatabase myDB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname",fullname);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = myDB.insert("users",null,contentValues);
        if(result == -1) {
            return false;
        }
        else{
            return true;
        }

    }
    public Boolean checkusername(String email)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("select * from users where email =?",new String[]{email});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
    public Boolean checkusernamePassword(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("select * from users where email =? and password =?",new String[]{email, password});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
}

