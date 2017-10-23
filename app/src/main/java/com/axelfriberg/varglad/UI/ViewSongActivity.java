package com.axelfriberg.varglad.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.axelfriberg.varglad.AssetHandler;
import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.UI.ListSongsActivity.ListSongsActivity;


public class ViewSongActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_view_song);

        AssetHandler assetHandler = new AssetHandler(getApplicationContext());

        TextView lyricsTextView = findViewById(R.id.lyrics_TextView);
        String songTitle = intent.getStringExtra(ListSongsActivity.EXTRA_SONG_TITLE);
        String spexTitle = intent.getStringExtra(ListSongsActivity.EXTRA_SPEX_TITLE);
        setTitle(songTitle);
        lyricsTextView.setText(assetHandler.readSongFile(spexTitle, songTitle));
    }
}
