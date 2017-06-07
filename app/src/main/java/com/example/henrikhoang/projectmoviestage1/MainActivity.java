package com.example.henrikhoang.projectmoviestage1;

import android.content.Context;
import android.graphics.Path;
import android.net.Uri;
import android.util.Log;
import com.example.henrikhoang.projectmoviestage1.MovieAdapter.MovieAdapterOnClickHandler;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henrikhoang.projectmoviestage1.utility.Network;
import com.example.henrikhoang.projectmoviestage1.utility.OpenMovieJsonUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private TextView mErrorTextView;
    private ProgressBar mLoadingIndicator;

    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movieCatalogue);
        mErrorTextView = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this, getApplicationContext());

        mRecyclerView.setAdapter(mMovieAdapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMovieDataPopular();
    }

    private void loadMovieDataPopular() {
        showMovieDataView();
        new FetchMovieTask().execute();
    }

    private void loadMovieDataRate() {
        showMovieDataView();
        new FetchMovieTaskTopRated().execute();
    }
    
    @Override
    public void onClick(String listedMovie) {
        Context context = this;
        Toast.makeText(context, listedMovie, Toast.LENGTH_SHORT)
                .show();
    }

    private void showMovieDataView() {
        mErrorTextView.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorTextView.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    //activities for menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.action_refresh || menuItemThatWasSelected == R.id.action_popolar) {
            mMovieAdapter.setMovieData(null);
            loadMovieDataPopular();
            return true;
        } else if (menuItemThatWasSelected == R.id.action_top_rated) {
            mMovieAdapter.setMovieData(null);
            loadMovieDataRate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //used when popular option is called
    public class FetchMovieTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {

            URL movieRequestURL = Network.buildURLPopular();

            try {
                String jsonMovieResponse = Network.
                        getResponseFromHttpUrl(movieRequestURL);

                String[] simpleJsonMovieData = OpenMovieJsonUtils
                        .getSimpleMoviePosterFromJson(MainActivity.this, jsonMovieResponse);

                return simpleJsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String[] movieData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if(movieData != null) {
                showMovieDataView();
                mMovieAdapter.setMovieData(movieData);
            } else {
                showErrorMessage();
            }
        }
    }

    // Used when top rated option is called
    public class FetchMovieTaskTopRated extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {
            URL movieRequestURL = Network.buildURLTopRated();
            try {
                String jsonMovieResponse = Network.getResponseFromHttpUrl(movieRequestURL);
                String[] simpleJsonMovieData = OpenMovieJsonUtils.getSimpleMoviePosterFromJson(MainActivity.this,
                        jsonMovieResponse);

                return simpleJsonMovieData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] movieData) {
           mLoadingIndicator.setVisibility(View.INVISIBLE);
            if(movieData != null) {
                showMovieDataView();
                mMovieAdapter.setMovieData(movieData);
            } else { showErrorMessage();}
        }
    }
}

