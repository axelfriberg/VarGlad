package com.axelfriberg.varglad.ui.listsongsactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.axelfriberg.varglad.AssetHandler;
import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.ui.mainactivity.MainActivity;
import com.axelfriberg.varglad.ui.RecyclerViewClickListener;
import com.axelfriberg.varglad.ui.ViewSongActivity;


public class ListSongsActivity extends AppCompatActivity implements RecyclerViewClickListener{
    public final static String EXTRA_SONG_TITLE = "com.axelfriberg.varglad.list_songs_activity.EXTRA_SONG_TITLE";
    public final static String EXTRA_SPEX_TITLE = "com.axelfriberg.varglad.list_songs_activity.EXTRA_SPEX_TITLE";

    private static String mSpexTitle;
    private RecyclerView.Adapter mSongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);

        Toolbar toolbar = findViewById(R.id.list_songs_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String extraTitle= intent.getStringExtra(MainActivity.EXTRA_SPEX_TITLE);
        if(extraTitle != null)
            mSpexTitle = extraTitle;
        else
            mSpexTitle = "Spex";

        setTitle(mSpexTitle);

        RecyclerView mRecyclerView = findViewById(R.id.songs_list_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if(mSongAdapter == null) {
            AssetHandler assetHandler = new AssetHandler(getApplicationContext().getAssets());
            mSongAdapter = new SongAdapter(assetHandler.getArrayOfSongTitles(mSpexTitle), this);
        }

        mRecyclerView.setAdapter(mSongAdapter);
    }

    @Override
    public void recyclerViewListClicked(String songTitle) {
        Intent intent = new Intent(this, ViewSongActivity.class);
        intent.putExtra(EXTRA_SONG_TITLE, songTitle);
        intent.putExtra(EXTRA_SPEX_TITLE, mSpexTitle);
        startActivity(intent);
    }
}