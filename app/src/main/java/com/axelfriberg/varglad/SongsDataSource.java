package com.axelfriberg.varglad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SongsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_LYRIC };

    public SongsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Song createSong(String title, String lyrics) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_LYRIC, lyrics);
        long insertId = database.insert(MySQLiteHelper.TABLE_SONGS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SONGS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Song newSong = cursorToSong(cursor);
        cursor.close();
        return newSong;
    }

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_SONGS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Song song = cursorToSong(cursor);
            songs.add(song);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return songs;
    }

    private Song cursorToSong(Cursor cursor) {
        return new Song(cursor.getString(0),cursor.getString(1));
    }
}
