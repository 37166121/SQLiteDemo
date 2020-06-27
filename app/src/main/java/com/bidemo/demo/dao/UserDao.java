package com.bidemo.demo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bidemo.demo.bean.UserBean;
import com.bidemo.demo.helper.UserDBHelper;
import com.bidemo.demo.helper.UserDBHelper.FeedEntry;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Context context;
    private SQLiteDatabase db;
    private UserDBHelper userDBHelper;

    public UserDao(Context context){
        this.context = context;
        userDBHelper = new UserDBHelper(context);
    }

    public void insert(UserBean userBean){
        db = userDBHelper.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_USERNAME, userBean.getUsername());
        values.put(FeedEntry.COLUMN_NAME_PASSWORD, userBean.getPassword());
        db.insert(FeedEntry.TABLE_NAME,
                null, values
        );
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void delete(UserBean userBean){
        db = userDBHelper.getWritableDatabase();
        db.beginTransaction();
        db.delete(FeedEntry.TABLE_NAME,
                "username = ?",
                new String[]{userBean.getUsername()}
                );
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<UserBean> query(){
        db = userDBHelper.getReadableDatabase();
        List<UserBean> userBeans = new ArrayList<>();
        Cursor cursor = db.query(
                FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        while(cursor.moveToNext()) {
            UserBean userBean = new UserBean();
            userBean.setUsername(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_USERNAME)));
            userBean.setPassword(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_PASSWORD)));
            userBeans.add(userBean);
        }
        cursor.close();
        return userBeans;
    }

    public void update(UserBean userBean){
        db = userDBHelper.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_USERNAME, userBean.getUsername());
        values.put(FeedEntry.COLUMN_NAME_PASSWORD, userBean.getPassword());
        db.update(FeedEntry.TABLE_NAME,
                values,
                "username = ?",
                new String[]{userBean.getUsername()}
        );
        db.setTransactionSuccessful();
        db.endTransaction();
    }
}