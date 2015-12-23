package com.axelfriberg.varglad;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ListSongsActivity extends ListActivity {
    private Cursor songs;
    private MyDatabase db;
    public final static String EXTRA_TITLE = "com.axelfriberg.varglad.SONG_TITLE";
    public final static String EXTRA_LYRICS = "com.axelfriberg.varglad.SONG_LYRICS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);


        Intent intent = getIntent();
        String tableName = intent.getStringExtra(MainActivity.EXTRA_TITLE);
        setTitle(tableName);

        db = new MyDatabase(this);
        songs = db.getSongs(tableName); // you would not typically call this on the main thread

        ListAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                songs,
                new String[] {"title"},
                new int[] {android.R.id.text1});

        getListView().setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_songs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cursor cursor = ((SimpleCursorAdapter)l.getAdapter()).getCursor();
        cursor.moveToPosition(position);

        String title = cursor.getString(cursor.getColumnIndex("title"));
        String lyrics = cursor.getString(cursor.getColumnIndex("lyrics"));

        cursor.close();

        Intent intent = new Intent(this, ViewSongActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_LYRICS, lyrics);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        songs.close();
        db.close();
    }
}
