package com.example.vitali.githubapiclient.data.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class DBHelper extends SQLiteOpenHelper {

    DBHelper(Context context) {
        super(context, SQL.DB_NAME, null, SQL.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL.CREATE_TABLE_QUERY);
        } catch (SQLException ignored) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL.DROP_QUERY);
        this.onCreate(db);
    }
}