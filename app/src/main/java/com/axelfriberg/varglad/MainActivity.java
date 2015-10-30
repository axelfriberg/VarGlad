package com.axelfriberg.varglad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;


import java.util.List;


public class MainActivity extends Activity {
    private SongsDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));


        datasource = new SongsDataSource(this);
        datasource.open();

        String spexanthem = "Nu är det spex på gång \n " +
                "å våran glada sång \n" +
                " Kommer att göras över alla natten lång \n " +
                "Kom hit så ska Ni se \n Att det blir stor succé \n " +
                "Å vår publik den skriker om och om igen \n \n " +
                "Nu är det spex på gång \n " +
                "Å hela natten lång \n " +
                "Så ska vi hålla sexa. Kom så ska vi Spexa \n " +
                "Att Vara Glad är bäst \n " +
                "Nu ska det bli stor fest \n " +
                "Å vi ska festa ända fram till morgonen \n \n" +
                "Ra-ra-raj-raj-raj...";

        Song song = datasource.createSong("Spex anthem", spexanthem);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                List<Song> songs = datasource.getAllSongs();
                Song song = songs.get(position);

                Intent intent = new Intent(MainActivity.this, ViewSongActivity.class);
                intent.putExtra(ViewSongActivity.EXTRA_TITLE, song.getTitle());
                intent.putExtra(ViewSongActivity.EXTRA_LYRICS, song.getLyrics());
                startActivity(intent);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
