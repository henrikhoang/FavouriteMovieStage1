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
    public static String[] getSimpleMovieStringsFromJson(Context context, String movieJsonStr)
            throws JSONException {


        final String RESULT = "results";


        final String TITLE = "title";


        String[] parsedMovieData = null;

        JSONObject movieJson = new JSONObject(movieJsonStr);

        JSONArray movieArray = movieJson.getJSONArray(RESULT);

        parsedMovieData = new String[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++) {
            String movieTitle;
            JSONObject selectedMovie = movieArray.getJSONObject(i);

            movieTitle = selectedMovie.getString(TITLE);

            parsedMovieData[i] = movieTitle;
        }

        return parsedMovieData;

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
            parsedMovieData[i] = "http://image.tmdb.org/t/p/w185" + path;
        }
        return parsedMovieData;
    }

}

