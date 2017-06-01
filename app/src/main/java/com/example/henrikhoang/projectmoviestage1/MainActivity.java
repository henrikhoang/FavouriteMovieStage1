package com.example.henrikhoang.projectmoviestage1;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

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

        mMovieAdapter = new MovieAdapter(this);

        mRecyclerView.setAdapter(mMovieAdapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMovieData();
    }

    private void loadMovieData() {

        showMovieDataView();
        String choice = "popular";
        new FetchMovieTask().execute(choice);
    }
    private void what() {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.action_refresh) {
            Context context = MainActivity.this;
            String message = "Error occured";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {
            if (params.length == 0) return null;

            String selectedSearch = params[0];

            URL movieRequestURL = Network.buildURL(selectedSearch);

            try {
                String jsonMovieResponse = Network.
                        getResponseFromHttpUrl(movieRequestURL);

                String[] simpleJsonMovieData = OpenMovieJsonUtils
                        .getSimpleMovieStringsFromJson(MainActivity.this, jsonMovieResponse);

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
                mMovieAdapter.setmMovieData(movieData);
            } else {
                showErrorMessage();
            }
        }
    }
}

