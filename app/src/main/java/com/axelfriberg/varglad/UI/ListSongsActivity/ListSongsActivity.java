package com.axelfriberg.varglad.UI.ListSongsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.axelfriberg.varglad.AssetHandler;
import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.UI.MainActivity.MainActivity;
import com.axelfriberg.varglad.UI.RecyclerViewClickListener;
import com.axelfriberg.varglad.UI.ViewSongActivity;


public class ListSongsActivity extends Activity implements RecyclerViewClickListener{
    public final static String EXTRA_SONG_TITLE = "com.axelfriberg.varglad.list_songs_activity.SONG_TITLE";
    public final static String EXTRA_SPEX_TITLE = "com.axelfriberg.varglad.list_songs_activity.SPEX_TITLE";

    private static String mSpexTitle;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mSongAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);

        Intent intent = getIntent();
        String extraTitle= intent.getStringExtra(MainActivity.EXTRA_SPEX_TITLE);
        if(extraTitle != null)
            mSpexTitle = extraTitle;

        setTitle(mSpexTitle);

        mRecyclerView = findViewById(R.id.songs_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if(mSongAdapter == null) {
            AssetHandler assetHandler = new AssetHandler(getApplicationContext());
            mSongAdapter = new SongAdapter(assetHandler.getArrayOfSongTitles(mSpexTitle), this);
        }

        mRecyclerView.setAdapter(mSongAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void recyclerViewListClicked(String songTitle) {
        Intent intent = new Intent(this, ViewSongActivity.class);
        intent.putExtra(EXTRA_SONG_TITLE, songTitle);
        intent.putExtra(EXTRA_SPEX_TITLE, mSpexTitle);
        startActivity(intent);
    }
}