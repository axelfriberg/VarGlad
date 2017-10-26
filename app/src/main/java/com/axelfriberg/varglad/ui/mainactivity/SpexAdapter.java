package com.axelfriberg.varglad.ui.mainactivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.ui.RecyclerViewClickListener;

import java.util.Arrays;


public class SpexAdapter extends RecyclerView.Adapter<SpexAdapter.ViewHolder>  {
    private static Spex[] mSpexArray;
    private static RecyclerViewClickListener mListener;
    private SortingMode mSortingMode;

    enum SortingMode {
        YEAR_ASCENDING,
        YEAR_DESCENDING
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

    //TODO: Sort by name
    //TODO: Add search
    void sort(SortingMode sortingMode) {
        switch (sortingMode) {
            case YEAR_ASCENDING:
                sortYearAscending();
                mSortingMode = SortingMode.YEAR_ASCENDING;
                break;
            case YEAR_DESCENDING:
                sortYearDescending();
                mSortingMode = SortingMode.YEAR_DESCENDING;
                break;
            default:
                sortYearAscending();
                break;
        }
    }

    private void sortYearAscending() {
        Arrays.sort(mSpexArray, new Spex.YearComparator());
    }

    private void sortYearDescending() {
        Arrays.sort(mSpexArray, new Spex.YearComparator().reversed());
    }
}
