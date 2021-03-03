package com.example.tongxunlu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tongxunlu.db.AppSqliteHelper;
import com.example.tongxunlu.model.User;

public class UserDao {
    private AppSqliteHelper helper;
    private String tableName = "user";

    public UserDao(Context context){
        helper= new AppSqliteHelper(context);
    }

    //根据账号和密码查询用户
    public User login(String account,String password){
        SQLiteDatabase db = helper.getReadableDatabase();
        User user = null;
        Cursor c = db.query(tableName, null, "name=? and password=?", new String[]{account, password}, null, null, null, null);
        if (c != null && c.getCount() > 0){
            if (c.moveToNext()){
                user = new User();
                user.setId(c.getInt(c.getColumnIndex("id")));
                user.setAccount(account);
                user.setPassword(password);
                user.setPhone(c.getString(c.getColumnIndex("phone")));
            }
        }
        return user;
    }

    //根据账号或手机查询用户是否存在
    public boolean isExistByAccountOrPhone(String account,String phone){
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(tableName,null,"name=? or phone=?",new String[]{account,phone},null,null,null);
        if (c != null && c.getCount() > 0){
            return c.moveToNext();
        }

        return false;

    }
    //根据账号或手机查询用户密码
    public String queryPasswordByAccountAndPhone(String account,String phone){
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(tableName,null,"name=? and phone=?",new String[]{account,phone},null,null,null);
        if (c != null && c.getCount() > 0){
            if (c.moveToNext()){
                return c.getString(c.getColumnIndex("password"));
            }
        }

        return null;

    }




    //添加用户到列表中
    public boolean insert(User bean){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",bean.getAccount());
        values.put("password",bean.getPassword());
        values.put("phone",bean.getPhone());

        long insert = db.insert(tableName, null, values);
        return insert > 0;
    }

}
