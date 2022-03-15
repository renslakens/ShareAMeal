package com.lakens.shareameal.dataaccess;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    // Base URL for API.
    private static final String BASE_URL =  "https://shareameal-api.herokuapp.com/api/meal";

    public static String getMealInfo() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String mealJSONString = null;

        try {
            //Make Uri
            Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                    .build();

            //Convert Uri to URl
            URL requestURL = new URL(builtURI.toString());

            //Try URL connection
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Since it's JSON, adding a newline isn't necessary (it won't
                // affect parsing) but it does make debugging a *lot* easier
                // if you print out the completed buffer for debugging.
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            mealJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Close connection and bufferedreader
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d(LOG_TAG, mealJSONString);
        return mealJSONString;
    }
}