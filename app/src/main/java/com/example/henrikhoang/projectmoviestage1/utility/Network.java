package com.example.henrikhoang.projectmoviestage1.utility;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by henrikhoang on 5/26/17.
 */

public final class Network {

    private static final String TAG = Network.class.getSimpleName();

    private static final String API_KEY = "";
//insert your own API key here

    private static final String STATIC_MOVIE_URL =
            "https://api.themoviedb.org/3/movie/popular";

    

    final static int NUM_PAGE = 1;
    final static String PAGE_PARAM = "page";
    final static String LANG_PARAM = "original_language";
    final static String language = "en";
    final static String api = "api_key";
    private static final String MOVIE_BASE_URL = STATIC_MOVIE_URL;

    public static URL buildURLPopular() {
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(api, API_KEY)
                .appendQueryParameter(PAGE_PARAM, Integer.toString(NUM_PAGE))
                .appendQueryParameter(LANG_PARAM, language)
                .build();

        URL url = null;
        try
        {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URL" + url);
        return url;
    }

    public static URL buildURLTopRated() {
        Uri builtUri
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
