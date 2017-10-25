package com.axelfriberg.varglad.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.axelfriberg.varglad.AssetHandler;
import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.UI.ListSongsActivity.ListSongsActivity;


public class ViewSongActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_song);

        Toolbar toolbar = findViewById(R.id.view_song_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String songTitle = intent.getStringExtra(ListSongsActivity.EXTRA_SONG_TITLE);
        String spexTitle = intent.getStringExtra(ListSongsActivity.EXTRA_SPEX_TITLE);

        setTitle(songTitle);

        AssetHandler assetHandler = new AssetHandler(getApplicationContext());

        TextView lyricsTextView = findViewById(R.id.lyrics_TextView);
        lyricsTextView.setText(assetHandler.readSongFile(spexTitle, songTitle));
    }
}
