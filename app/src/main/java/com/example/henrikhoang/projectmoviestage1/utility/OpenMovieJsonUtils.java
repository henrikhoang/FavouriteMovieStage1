package com.example.henrikhoang.projectmoviestage1.utility;

import android.content.ContentValues;
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

        /* Weather information. Each day's forecast info is an element of the "list" array */
        final String RESULT = "results";

        /* All temperatures are children of the "temp" object */
        final String TITLE = "title";

        /* String array to hold each day's weather String */
        String[] parsedMovieData = null;

        JSONObject movieJson = new JSONObject(movieJsonStr);

        JSONArray movieArray = movieJson.getJSONArray(RESULT);

        parsedMovieData = new String[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++) {
            String title;
            JSONObject selectedMovie = movieArray.getJSONObject(i);

            title = selectedMovie.getString(TITLE);

            parsedMovieData[i] = title;
        }

        return parsedMovieData;

    }

}

