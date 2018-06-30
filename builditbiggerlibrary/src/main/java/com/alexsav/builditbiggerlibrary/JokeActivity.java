package com.alexsav.builditbiggerlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke";
    private static final String LOG_TAG = JokeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_joke);
        final Intent intent = getIntent();

        if (intent == null || !intent.hasExtra(JokeActivity.JOKE_KEY)) {
            Log.e(LOG_TAG, "Missing joke " + JOKE_KEY);
            finish();
            return;
        }

        String jokeText = intent.getStringExtra(JokeActivity.JOKE_KEY);
        TextView jokeTextView = findViewById(R.id.textViewJoke);

        if (!TextUtils.isEmpty(jokeText)) {
            jokeTextView.setText(jokeText);
        }

    }
}
