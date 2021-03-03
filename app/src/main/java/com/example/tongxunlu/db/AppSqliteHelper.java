package com.example.tongxunlu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppSqliteHelper extends SQLiteOpenHelper {
    public AppSqliteHelper(@Nullable Context context) {
        //创建数据库
        super(context, "txl.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("create table user(id Integer primary key autoincrement,name varchar(40) ,password varchar(40),phone varchar(11))");
        db.execSQL("create table concater(id Integer primary key autoincrement,name varchar(40) ,pic varchar(40),phone varchar(11))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
