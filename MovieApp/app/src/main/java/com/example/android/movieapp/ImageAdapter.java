package com.example.android.movieapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by E5757 on 28/02/2018.
 */

public class ImageAdapter extends BaseAdapter {
    private final Context mContext;
    private final Movie[] mMovies;

    public ImageAdapter(Context context, Movie[] movies) {
        mContext = context;
        mMovies = movies;
    }

    public int getCount() {
        if (mMovies == null || mMovies.length == 0) {
            return -1;
        }

        return mMovies.length;
    }

    public Object getItem(int position) {
        if (mMovies == null || mMovies.length == 0) {
            return null;
        }

        return mMovies[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        // Will be null if it's not recycled. Will initialize ImageView if new.
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext)
                .load(mMovies[position].getPosterPath())
                .resize(mContext.getResources().getInteger(R.integer.tmdb_poster_w185_width),
                        mContext.getResources().getInteger(R.integer.tmdb_poster_w185_height))
                //.error(R.drawable.not_found)
                //.placeholder(R.drawable.searching)
                .into(imageView);

        return imageView;
    };
}
