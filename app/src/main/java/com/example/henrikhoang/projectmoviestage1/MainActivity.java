package com.example.henrikhoang.projectmoviestage1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.henrikhoang.projectmoviestage1.MovieAdapter.MovieAdapterOnClickHandler;
import com.example.henrikhoang.projectmoviestage1.utility.Network;
import com.example.henrikhoang.projectmoviestage1.utility.OpenMovieJsonUtils;

import org.parceler.Parcels;

import java.net.URL;
import java.util.List;

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
    public void onClick(Film film) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(context, destinationClass);
        intent.putExtra("film", Parcels.wrap(film));
        startActivity(intent);

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //activities for menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                mMovieAdapter.setMovieData(null);
                loadMovieDataPopular();
                return true;
            case R.id.action_popular:
                mMovieAdapter.setMovieData(null);
                loadMovieDataPopular();
                return true;
            case R.id.action_top_rated:
                mMovieAdapter.setMovieData(null);
                loadMovieDataRate();
                return true;
            case R.id.tv_sort_by:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //used when popular option is called
    public class FetchMovieTask extends AsyncTask<String, Void, List<Film>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Film> doInBackground(String... params) {

            URL movieRequestURL = Network.buildURLPopular();

            try {
                String jsonMovieResponse = Network.
                        getResponseFromHttpUrl(movieRequestURL);

                List<Film> movies = OpenMovieJsonUtils.getSimpleMovieStringsFromJson(MainActivity.this,
                        jsonMovieResponse);
            return movies;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Film> movies) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movies != null) {
                showMovieDataView();
                mMovieAdapter.setMovieData(movies);
            } else {
                showErrorMessage();
            }
        }
    }

    // Used when top rated option is called
    public class FetchMovieTaskTopRated extends AsyncTask<String, Void, List<Film>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Film> doInBackground(String... params) {
            URL movieRequestURL = Network.buildURLTopRated();
            try {
                String jsonMovieResponse = Network.getResponseFromHttpUrl(movieRequestURL);
                List<Film> movies = OpenMovieJsonUtils.getSimpleMovieStringsFromJson(MainActivity.this,
                        jsonMovieResponse);
//                films = movies;
                return movies;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Film> movies) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movies != null) {
                showMovieDataView();
                mMovieAdapter.setMovieData(movies);
            } else {
                showErrorMessage();
            }
        }
    }

}

