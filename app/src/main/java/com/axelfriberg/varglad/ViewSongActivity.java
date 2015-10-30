package com.axelfriberg.varglad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewSongActivity extends Activity {
    private TextView mLyricsTextView;
    public final static String EXTRA_TITLE = "com.axelfriberg.varglad.SONG_TITLE";
    public final static String EXTRA_LYRICS = "com.axelfriberg.varglad.SONG_LYRICS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_view_song);

        mLyricsTextView = (TextView) findViewById(R.id.lyrics_TextView);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String lyrics = intent.getStringExtra(EXTRA_LYRICS);

        setTitle(title);
        mLyricsTextView.setText(lyrics);
    }
}
