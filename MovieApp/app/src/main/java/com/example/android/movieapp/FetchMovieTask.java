package com.example.android.movieapp;

import android.os.AsyncTask;

import  com.example.android.movieapp.Movie;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * Created by E5757 on 28/02/2018.
 */

public class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {
    private final String LOG_TAG = FetchMovieTask.class.getSimpleName();



    /**
     * TMDb API key
     */
    private final String mApiKey;

    /**
     * Interface / listener
     */
    private final OnTaskCompleted mListener;

    public FetchMovieTask(OnTaskCompleted listener, String apiKey) {
        super();

        mListener = listener;
        mApiKey = apiKey;
    }

    @Override
    protected Movie[] doInBackground(String...  params) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieJsonStr = null;



        try {

            final String TMDB_BASE_URL = "https://api.themoviedb.org/3/discover/movie?";
            final String SORT_BY_PARAM = "sort_by";
            final String API_KEY_PARAM = "api_key";

            Uri builtUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                    .appendQueryParameter(SORT_BY_PARAM, params[0])
                    .appendQueryParameter(API_KEY_PARAM, "9fd00745da779f3e528f54f2734c81e9")
                    .build();

            URL url = new URL(builtUri.toString());


            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }

            movieJsonStr = buffer.toString();


            reader = new BufferedReader(new InputStreamReader(inputStream));

        } catch (java.io.IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            e.printStackTrace();
        }finally {
            // Tidy up: release url connection and buffered reader
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            // Make sense of the JSON data
            return getMovierDataFromJson(movieJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }


        return null;
    }

    private Movie[] getMovierDataFromJson(String movieJsonStr) throws JSONException {

        // JSON tags
        final String TAG_RESULTS = "results";
        final String TAG_ORIGINAL_TITLE = "original_title";
        final String TAG_POSTER_PATH = "poster_path";
        final String TAG_OVERVIEW = "overview";
        final String TAG_VOTE_AVERAGE = "vote_average";
        final String TAG_RELEASE_DATE = "release_date";

//        try{
            // Get the array containing hte movies found
            JSONObject moviesJson = new JSONObject(movieJsonStr);
            JSONArray movieArray = moviesJson.getJSONArray(TAG_RESULTS);



        // Create array of Movie objects that stores data from the JSON string
        Movie[] movies = new Movie[movieArray.length()];

            for(int i = 0; i < movieArray.length(); i++ ){
                movies[i] = new Movie();


                JSONObject movieDetail = movieArray.getJSONObject(i);


                movies[i].setOriginalTitle(movieDetail.getString(TAG_ORIGINAL_TITLE));
                movies[i].setPosterPath(movieDetail.getString(TAG_POSTER_PATH));
                movies[i].setOverview(movieDetail.getString(TAG_OVERVIEW));
                movies[i].setVoteAverage(movieDetail.getDouble(TAG_VOTE_AVERAGE));
                movies[i].setReleaseDate(movieDetail.getString(TAG_RELEASE_DATE));
            }

        return movies;

    }
    @Override
    protected void onPostExecute(Movie[] movies) {
        super.onPostExecute(movies);

        // Notify UI
        mListener.onFetchMoviesTaskCompleted(movies);
    }
}
