package com.axelfriberg.varglad.ui.listsongsactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.axelfriberg.varglad.AssetHandler;
import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.ui.RecyclerViewClickListener;
import com.axelfriberg.varglad.ui.ViewSongActivity;
import com.axelfriberg.varglad.ui.mainactivity.MainActivity;


public class ListSongsActivity extends AppCompatActivity implements RecyclerViewClickListener {
    public static final String EXTRA_SONG_TITLE =
            "com.axelfriberg.varglad.list_songs_activity.EXTRA_SONG_TITLE";
    public static final String EXTRA_SPEX_TITLE =
            "com.axelfriberg.varglad.list_songs_activity.EXTRA_SPEX_TITLE";
    private static final String SIS_SPEX_TITLE =
            "com.axelfriberg.varglad.list_songs_activity.SIS_SPEX_TITLE";
    private static final String SIS_SORTING_MODE =
            "com.axelfriberg.varglad.list_songs_activity.SIS_SORTING_MODE";
    private static final String SIS_SORTING_CHECKED_ITEM =
            "com.axelfriberg.varglad.list_songs_activity.SIS_SORTING_CHECKED_ITEM";

    private static String mSpexTitle;
    private PopupMenu mPopupMenu;
    private int mCheckedItemID;
    private SongAdapter mSongAdapter;

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

        RecyclerView mRecyclerView = findViewById(R.id.songs_list_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        String extraTitle = intent.getStringExtra(MainActivity.EXTRA_SPEX_TITLE);

        SongAdapter.SortingMode sortingMode = SongAdapter.SortingMode.TITLE_ASCENDING;
        mCheckedItemID = R.id.sort_song_title_ascending;

        if (savedInstanceState != null) {
            mSpexTitle = savedInstanceState.getString(SIS_SPEX_TITLE);
            mCheckedItemID = savedInstanceState.getInt(SIS_SORTING_CHECKED_ITEM);
            sortingMode =
                    (SongAdapter.SortingMode) savedInstanceState.getSerializable(SIS_SORTING_MODE);
        } else if (extraTitle != null) {
            mSpexTitle = extraTitle;
        } else if (mSpexTitle == null) {
            mSpexTitle = "Spex Title";
        }

        AssetHandler assetHandler = new AssetHandler(getApplicationContext().getAssets());
        mSongAdapter = new SongAdapter(assetHandler.getArrayOfSongTitles(mSpexTitle), this);
        mRecyclerView.setAdapter(mSongAdapter);
        mSongAdapter.sort(sortingMode);

        setTitle(mSpexTitle);
    }

    @Override
    public void recyclerViewListClicked(String songTitle) {
        Intent intent = new Intent(this, ViewSongActivity.class);
        intent.putExtra(EXTRA_SONG_TITLE, songTitle);
        intent.putExtra(EXTRA_SPEX_TITLE, mSpexTitle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_songs_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_songs:
                showSortingPopupMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSortingPopupMenu() {
        if (mPopupMenu == null) {
            mPopupMenu = createPopupMenu();
            mPopupMenu.inflate(R.menu.songs_sorting_menu);
            mPopupMenu.getMenu().findItem(mCheckedItemID).setChecked(true);
        }
        mPopupMenu.show();
    }

    private PopupMenu createPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.action_sort_songs));

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sort_song_title_ascending:
                        if (!item.isChecked()) {
                            mSongAdapter.sort(SongAdapter.SortingMode.TITLE_ASCENDING);
                            checkSortingItem(item);
                        }
                        return true;
                    case R.id.sort_song_title_descending:
                        if (!item.isChecked()) {
                            mSongAdapter.sort(SongAdapter.SortingMode.TITLE_DESCENDING);
                            checkSortingItem(item);
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });

        return popupMenu;
    }

    private void checkSortingItem(MenuItem item) {
        item.setChecked(true);
        mCheckedItemID = item.getItemId();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(SIS_SPEX_TITLE, mSpexTitle);
        savedInstanceState.putSerializable(SIS_SORTING_MODE, mSongAdapter.getSortingMode());
        savedInstanceState.putInt(SIS_SORTING_CHECKED_ITEM, mCheckedItemID);
    }
}