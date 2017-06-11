package com.example.henrikhoang.projectmoviestage1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.henrikhoang.projectmoviestage1.utility.Network;
import com.example.henrikhoang.projectmoviestage1.utility.OpenMovieJsonUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    private TextView mDisplayDetail;
    private ImageView mDisplayPoster;

    private static final String TAG = DetailActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDisplayDetail = (TextView) findViewById(R.id.tv_display_movie_detail);
        mDisplayPoster = (ImageView) findViewById(R.id.iv_display_poster_thumbnail);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        Uri uri = Uri.parse(extras.getString("URL"));
        Picasso.with(getApplicationContext()).load(uri).into(mDisplayPoster);

        String posterPath = extras.getString("URL").toString();
        Log.v(TAG, "String Poster URL " + posterPath);

        URL movieChosen = Network.buildURLPopular();

        Log.v(TAG, "URL that is built " + movieChosen);

        try {
            String jsonMovieResponse = Network.getResponseFromHttpUrl(movieChosen);
            Log.v(TAG, "jsonMovieResponse " + jsonMovieResponse);

            String simpleMovieDetail = OpenMovieJsonUtils.getSimpleMovieStringsFromJson(this, jsonMovieResponse, posterPath);

            Log.v(TAG, "simpleMovieDetail  " + simpleMovieDetail);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }









}