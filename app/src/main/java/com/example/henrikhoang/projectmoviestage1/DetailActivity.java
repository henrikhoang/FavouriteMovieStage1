package com.example.henrikhoang.projectmoviestage1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    private TextView mDisplayInfo;
    private ImageView mDisplayPoster;
    private TextView mDisplayOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDisplayInfo = (TextView) findViewById(R.id.tv_movie_info);
        mDisplayPoster = (ImageView) findViewById(R.id.iv_movie_thumbnail);
        mDisplayOverview = (TextView) findViewById(R.id.tv_display_movie_overview);

        Film film = Parcels.unwrap(getIntent().getParcelableExtra("film"));
        mDisplayInfo.setText("Title: " + film.getTitle() + "\n\n" + "Rating: " +film.getVote()+
                "/10" + "\n\n" + "Release Date: " +film.getDate());
        mDisplayOverview.setText("Plot: " + "\n" + film.getOverview());
        Picasso.with(this).load("http://image.tmdb.org/t/p/w500"+film.getPosterPath()).into(mDisplayPoster);

    }
}