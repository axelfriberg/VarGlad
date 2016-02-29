package com.axelfriberg.varglad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewSongActivity extends Activity {
    private TextView mLyricsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_view_song);

        mLyricsTextView = (TextView) findViewById(R.id.lyrics_TextView);
        String title = intent.getStringExtra(ListSongsActivity.EXTRA_TITLE);
        String lyrics = intent.getStringExtra(ListSongsActivity.EXTRA_LYRICS);

        setTitle(title);
        mLyricsTextView.setText(lyrics);
    }
}
