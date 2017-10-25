package com.axelfriberg.varglad.ui.listsongsactivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.ui.RecyclerViewClickListener;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private static String[] mSongTitleArray;
    private static RecyclerViewClickListener mListener;

    SongAdapter(String[] myDataset, RecyclerViewClickListener listener) {
        mSongTitleArray = myDataset;
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mSongTitleArray.length;
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
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
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
