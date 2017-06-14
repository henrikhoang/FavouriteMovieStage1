package com.example.henrikhoang.projectmoviestage1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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


    }
}