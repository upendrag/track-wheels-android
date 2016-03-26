package com.trackwheels.utilities;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RegisterAsyncTask extends AsyncTask<String, Void, String> {

    InputStream is = null;

    @Override
    protected String doInBackground(String... params) {
        try {
            Log.i("Filter", params[0]);

            URL url = new URL(Constants.Server.serverPath + "/register/" + params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("Filter", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = Utility.readIt(is, 500);
            Log.d("Filter", contentAsString);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}

