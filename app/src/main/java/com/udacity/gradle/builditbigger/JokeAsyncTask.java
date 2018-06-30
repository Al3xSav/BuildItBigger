package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeAsyncTask extends AsyncTask<MainActivityFragment.AsyncTaskCallback, Void, String> {

    private static final String TAG = JokeAsyncTask.class.getSimpleName();
    private static MyApi myApi = null;

    private MainActivityFragment.AsyncTaskCallback asyncTaskCallback = null;

    @Override
    protected final String doInBackground(MainActivityFragment.AsyncTaskCallback... asyncTaskCallbacks) {

        // set http for the appengine
        if (myApi == null) {
            myApi = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // Set Your Computer's IP
                    .setRootUrl("http://192.168.1.21:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(request -> request.setDisableGZipContent(true))
                    .build();
        }

        asyncTaskCallback = asyncTaskCallbacks[0];
        asyncTaskCallback.setLoading(true);

        try {
            return myApi.getJokes().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: ", e);
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.toLowerCase().contains("failed to connect")) {
            s = "No Joke for you.\n Try again later";
        }
        Log.d(TAG, "onPostExecute: result " + s);
        asyncTaskCallback.jokeDisplayer(s);
    }
}
