package com.axelfriberg.varglad.UI.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.UI.ListSongsActivity.ListSongsActivity;
import com.axelfriberg.varglad.UI.RecyclerViewClickListener;


public class MainActivity extends Activity implements RecyclerViewClickListener {
    public final static java.lang.String EXTRA_SPEX_TITLE = "com.axelfriberg.varglad.main_activity.SPEX_TITLE";

    private final Spex[] mSpexArray = new Spex[]{
            new Spex("DIII", R.drawable.diii),
            new Spex("Drottning Kristina", R.drawable.drottning_kristina),
            new Spex("En Karnevalssaga", R.drawable.en_karnevalssaga),
            new Spex("Muren", R.drawable.muren),
            new Spex("Sherlock Holmes", R.drawable.sherlock_holmes),
            new Spex("Var Gladspexarna", R.drawable.var_gladspexarna),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

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