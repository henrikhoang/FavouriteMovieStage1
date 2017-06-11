package com.example.henrikhoang.projectmoviestage1.utility;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by henrikhoang on 5/29/17.
 */

public final class OpenMovieJsonUtils {
    public static String getSimpleMovieStringsFromJson(Context context, String movieJsonStr, String posterParsed)
            throws JSONException {

        final String RESULT = "results";
        final String TITLE = "original_title";
        final String RELEASE_DATE = "release_date";
        final String VOTE = "vote_average";
        final String PLOT = "overview";

        String parsedMovieDetail = null;

        JSONObject movieJson = new JSONObject(movieJsonStr);

//        String[] poster_path = getSimpleMoviePosterFromJson(context, movieJsonStr);

//        title, release date, movie poster, vote average, and plot synopsis.

        JSONArray movieArray = movieJson.getJSONArray(RESULT);


//        compare = new String[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++) {
            String movieTitle;
            String releaseDate;
            String overview;
            String vote;
            String poster_found;

            JSONObject selectedMovie = movieArray.getJSONObject(i);

            movieTitle = "Original Title: " + selectedMovie.getString(TITLE);
            releaseDate = "Release Date: " + selectedMovie.getString(RELEASE_DATE);
            overview = "Plot Synopsis: " + selectedMovie.getString(PLOT);
            vote = "Vote Average: " + selectedMovie.getInt(VOTE);
            poster_found = "http://image.tmdb.org/t/p/w500" + selectedMovie.getString("poster_path");



            if (poster_found == posterParsed) {
                parsedMovieDetail = movieTitle + "\n\n\n" + releaseDate + "\n\n\n" + overview +
                        "\n\n\n" + vote ;
                    } else { return null;}
        }

        return parsedMovieDetail;

    }

    public static String[] getSimpleMoviePosterFromJson(Context context, String movieJsonStr) throws JSONException {
        final String RESULT = "results";
        String[] parsedMovieData;
        final String POSTER = "poster_path";

        JSONObject movieJson = new JSONObject(movieJsonStr);
        JSONArray movieArray = movieJson.getJSONArray(RESULT);

        parsedMovieData = new String[movieArray.length()];

        for (int i = 0; i< movieArray.length(); i++) {
            String path;
            JSONObject selectedMovie = movieArray.getJSONObject(i);
            path = selectedMovie.getString(POSTER);
            parsedMovieData[i] = "http://image.tmdb.org/t/p/w500" + path;
        }
        return parsedMovieData;
    }

}

