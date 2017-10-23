package com.axelfriberg.varglad.UI.MainActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.axelfriberg.varglad.R;
import com.axelfriberg.varglad.UI.RecyclerViewClickListener;


public class SpexAdapter extends RecyclerView.Adapter<SpexAdapter.ViewHolder>  {
    private static Spex[] mSpexArray;
    private static RecyclerViewClickListener mListener;

    SpexAdapter(Spex[] spexArray, RecyclerViewClickListener listener) {
        mSpexArray = spexArray;
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mSpexArray.length;
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
            mListener.recyclerViewListClicked(mSpexArray[getAdapterPosition()].name);
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
        holder.mImageView.setImageResource(spex.drawableId);
    }
}
