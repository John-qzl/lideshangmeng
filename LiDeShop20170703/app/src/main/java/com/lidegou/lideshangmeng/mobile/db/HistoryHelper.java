package com.lidegou.lideshangmeng.mobile.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lidegou.lideshangmeng.mobile.data.entity.Contance;

public class HistoryHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    public HistoryHelper(Context context) {
        super(context, Contance.DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contance.CREAT_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
