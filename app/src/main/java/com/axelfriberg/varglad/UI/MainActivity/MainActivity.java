package com.axelfriberg.varglad.UI.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.UI.ListSongsActivity.ListSongsActivity;
import com.axelfriberg.varglad.UI.RecyclerViewClickListener;


public class MainActivity extends Activity implements RecyclerViewClickListener {
    public final static java.lang.String EXTRA_SPEX_TITLE = "com.axelfriberg.varglad.main_activity.SPEX_TITLE";

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

        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        RecyclerView.Adapter spexAdapter = new SpexAdapter(mSpexArray, this);
        recyclerView.setAdapter(spexAdapter);
    }

    @Override
    public void recyclerViewListClicked(String spexTitle) {
        Intent intent = new Intent(this, ListSongsActivity.class);
        intent.putExtra(EXTRA_SPEX_TITLE, spexTitle);
        startActivity(intent);
    }
}