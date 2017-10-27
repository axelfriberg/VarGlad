package com.axelfriberg.varglad.ui.listsongsactivity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.ui.RecyclerViewClickListener;

import java.util.Arrays;
import java.util.Collections;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private static final String TAG = "SongAdapter";
    private static String[] mSongTitleArray;
    private static RecyclerViewClickListener mListener;
    private SortingMode mSortingMode;

    enum SortingMode {
        TITLE_ASCENDING,
        TITLE_DESCENDING
    }

    SongAdapter(String[] myDataset, RecyclerViewClickListener listener) {
        mSongTitleArray = myDataset;
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mSongTitleArray.length;
    }

    SortingMode getSortingMode() {
        return mSortingMode;
    }

    void sort(SortingMode sortingMode) {
        switch (sortingMode) {
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

    private SortingMode sortTitleAscending() {
        Arrays.sort(mSongTitleArray);
        notifyAllItemsChanged();
        return SortingMode.TITLE_ASCENDING;
    }

    private SortingMode sortTitleDescending() {
        Arrays.sort(mSongTitleArray, Collections.reverseOrder());
        notifyAllItemsChanged();
        return SortingMode.TITLE_DESCENDING;
    }

    private void notifyAllItemsChanged() {
        for (int i = 0; i < mSongTitleArray.length; i++) {
            notifyItemChanged(i);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mTextView;
        private final LinearLayout mLinearLayout;

        ViewHolder(LinearLayout linearLayout) {
            super(linearLayout);
            mLinearLayout = linearLayout;
            mLinearLayout.setOnClickListener(this);
            mTextView = linearLayout.findViewById(R.id.song_title);
        }

        @Override
        public void onClick(View v) {
            mListener.recyclerViewListClicked(mSongTitleArray[getAdapterPosition()]);
        }
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_view_holder, parent, false);
        return new SongAdapter.ViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(final SongAdapter.ViewHolder holder, int position) {
        final String songTitle =  mSongTitleArray[position];
        holder.mTextView.setText(songTitle);
    }
}
