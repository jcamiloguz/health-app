package com.guzcode.healthapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends  SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase DataBase) {
        DataBase.execSQL("CREATE TABLE gli_patients(id INTEGER PRIMARY KEY AUTOINCREMENT, p_name text not null, p_lastName text not null, bornDate text not null, cc text not null, eps text not null, hasSymptom int not null, exam real, state text not null )");
        DataBase.execSQL("CREATE TABLE anm_patients(id INTEGER PRIMARY KEY AUTOINCREMENT, p_name text not null, p_lastName text not null, bornDate text not null, cc text not null,  sex text not null, email text not null,  hem_level int not null, state text not null )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
