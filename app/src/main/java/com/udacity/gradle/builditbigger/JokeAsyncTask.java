package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeAsyncTask extends AsyncTask<EndPointCallBack, Void, String>{
    private static final String TAG = JokeAsyncTask.class.getSimpleName();
    private static MyApi myApi = null;
    private EndPointCallBack endPointCallBack;

    @Override
    protected String doInBackground(EndPointCallBack... endPointCallBacks) {
        if (myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                    request.setDisableGZipContent(true);
                }
            });
            myApi = builder.build();
        }

        endPointCallBack = endPointCallBacks[0];

        try {
            return myApi.getJokes().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: ", e);
            return "";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: result " + s);
        endPointCallBack.onResultReady(s);
    }
}
