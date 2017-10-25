package com.axelfriberg.varglad.ui.mainactivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.ui.listsongsactivity.ListSongsActivity;
import com.axelfriberg.varglad.ui.RecyclerViewClickListener;


public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {
    public final static String EXTRA_SPEX_TITLE = "com.axelfriberg.varglad.main_activity.EXTRA_SPEX_TITLE";
    private final static String SIS_SORTING_MODE = "com.axelfriberg.varglad.main_activity.SIS_SORTING_MODE";
    private final static String SIS_SORTING_CHECKED_ITEM = "com.axelfriberg.varglad.main_activity.SIS_SORTING_CHECKED_ITEM";

    private SpexAdapter mSpexAdapter;
    private PopupMenu mPopupMenu;
    private int mCheckedItemID;

    private final Spex[] mSpexArray = new Spex[]{
            new Spex("DIII", 2013, Spex.Semester.SPRING, R.drawable.diii),
            new Spex("Drottning Kristina", 2015, Spex.Semester.FALL, R.drawable.drottning_kristina),
            new Spex("En Karnevalssaga", 2014, Spex.Semester.SPRING, R.drawable.en_karnevalssaga),
            new Spex("Muren", 2014, Spex.Semester.FALL, R.drawable.muren),
            new Spex("Sherlock Holmes", 2015, Spex.Semester.SPRING, R.drawable.sherlock_holmes),
            new Spex("Var Gladspexarna", 1998, Spex.Semester.SPRING, R.drawable.var_gladspexarna)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.title_main_activity);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        mSpexAdapter = new SpexAdapter(mSpexArray, this);

        if(savedInstanceState == null) {
            mSpexAdapter.sort(SpexAdapter.SortingMode.YEAR_DESCENDING);
            mCheckedItemID = R.id.sort_year_descending;
        } else {
            SpexAdapter.SortingMode sortingMode =
                    (SpexAdapter.SortingMode) savedInstanceState.getSerializable(SIS_SORTING_MODE);
            mSpexAdapter.sort(sortingMode);
            mCheckedItemID = savedInstanceState.getInt(SIS_SORTING_CHECKED_ITEM);
        }

        recyclerView.setAdapter(mSpexAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                showSortingPopupMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSortingPopupMenu() {
        if(mPopupMenu == null) {
            mPopupMenu = createPopupMenu();
            mPopupMenu.inflate(R.menu.main_sorting_menu);
            mPopupMenu.getMenu().findItem(mCheckedItemID).setChecked(true);
        }
        mPopupMenu.show();
    }

    private PopupMenu createPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.action_sort));

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sort_year_ascending:
                        mSpexAdapter.sort(SpexAdapter.SortingMode.YEAR_ASCENDING);
                        checkSortingItem(item);
                        return true;
                    case R.id.sort_year_descending:
                        mSpexAdapter.sort(SpexAdapter.SortingMode.YEAR_DESCENDING);
                        checkSortingItem(item);
                    default:
                        return false;
                }
            }
        });

        return popupMenu;
    }

    private void checkSortingItem(MenuItem item) {
        if(!item.isChecked())
            item.setChecked(true);
        mCheckedItemID = item.getItemId();
    }

    @Override
    public void recyclerViewListClicked(String spexTitle) {
        Intent intent = new Intent(this, ListSongsActivity.class);
        intent.putExtra(EXTRA_SPEX_TITLE, spexTitle);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(SIS_SORTING_MODE, mSpexAdapter.getSortingMode());
        savedInstanceState.putInt(SIS_SORTING_CHECKED_ITEM, mCheckedItemID);
    }
}