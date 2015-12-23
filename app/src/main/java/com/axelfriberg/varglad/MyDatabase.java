package com.axelfriberg.varglad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "songs.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LYRIC = "lyric";

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public Cursor getSongs(String tableName) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String [] sqlSelect = {"0 _id", "FirstName", "LastName"};
        String sqlTables = tableName;

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, null, null, null,
                null, null, null);

        c.moveToFirst();
        return c;
    }

}