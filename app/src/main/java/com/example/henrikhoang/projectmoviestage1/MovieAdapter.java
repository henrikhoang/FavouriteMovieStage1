package com.example.henrikhoang.projectmoviestage1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;



import com.squareup.picasso.Picasso;

import static com.example.henrikhoang.projectmoviestage1.R.styleable.RecyclerView;

/**
 * Created by henrikhoang on 5/25/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private String[] mMovieData;

    private final MovieAdapterOnClickHandler mClickHandler;

    private Context context;

    public interface MovieAdapterOnClickHandler {
        void onClick (String listedMovie);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler, Context ctx) {
        mClickHandler = clickHandler;
        context = ctx;
    }


    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        //public final TextView mWeatherTextView;

        public final ImageView mMovieImageView;

        public MovieAdapterViewHolder(View view) {
            super(view);
     //     mWeatherTextView = (TextView) view.findViewById(R.id.tv_movie_data);
            mMovieImageView = (ImageView) view.findViewById(R.id.tv_movie_poster);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String movieSelected = mMovieData[adapterPosition];
            mClickHandler.onClick(movieSelected);
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)  {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentsImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentsImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        String movieBeingSelected = mMovieData[position];
//        try not presenting the TextView
     //   holder.mWeatherTextView.setText(movieBeingSelected);

        Picasso.with(context).load(movieBeingSelected).into(holder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        if (null == mMovieData) return 0;
        return mMovieData.length;
    }

    public void setMovieData(String[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }
}
