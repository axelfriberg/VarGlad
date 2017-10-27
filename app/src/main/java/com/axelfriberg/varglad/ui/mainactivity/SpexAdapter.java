package com.axelfriberg.varglad.ui.mainactivity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.ui.RecyclerViewClickListener;

import java.util.Arrays;


public class SpexAdapter extends RecyclerView.Adapter<SpexAdapter.ViewHolder>  {
    private static final String TAG = "SpexAdapter";

    private static Spex[] mSpexArray;
    private static RecyclerViewClickListener mListener;
    private SortingMode mSortingMode;

    enum SortingMode {
        YEAR_ASCENDING,
        YEAR_DESCENDING,
        TITLE_ASCENDING,
        TITLE_DESCENDING
    }

    SpexAdapter(Spex[] spexArray, RecyclerViewClickListener listener) {
        mSpexArray = spexArray;
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mSpexArray.length;
    }

    SortingMode getSortingMode() {
        return mSortingMode;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView mImageView;

        ViewHolder(ImageView imageView) {
            super(imageView);
            mImageView = imageView;
            mImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.recyclerViewListClicked(mSpexArray[getAdapterPosition()].getTitle());
        }

    }

    @Override
    public SpexAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spex_view_holder, parent, false);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Spex spex = mSpexArray[position];
        holder.mImageView.setImageResource(spex.getPosterID());
    }

    void sort(SortingMode sortingMode) {
        switch (sortingMode) {
            case YEAR_ASCENDING:
                mSortingMode = sortYearAscending();
                break;
            case YEAR_DESCENDING:
                mSortingMode = sortYearDescending();
                break;
            case TITLE_ASCENDING:
                mSortingMode = sortTitleAscending();
                break;
            case TITLE_DESCENDING:
                mSortingMode = sortTitleDescending();
                break;
            default:
                Log.i(TAG, "That sorting mode does not exist.");
                break;
        }
    }

    private SortingMode sortYearAscending() {
        Arrays.sort(mSpexArray, new Spex.YearComparator());
        return SortingMode.YEAR_ASCENDING;
    }

    private SortingMode sortYearDescending() {
        Arrays.sort(mSpexArray, new Spex.YearComparator().reversed());
        return SortingMode.YEAR_DESCENDING;
    }

    private SortingMode sortTitleAscending() {
        Arrays.sort(mSpexArray, new Spex.TitleComparator());
        return SortingMode.TITLE_ASCENDING;
    }

    private SortingMode sortTitleDescending() {
        Arrays.sort(mSpexArray, new Spex.TitleComparator().reversed());
        return SortingMode.TITLE_DESCENDING;
    }
}
