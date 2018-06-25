package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alexsav.builditbiggerlibrary.JokeActivity;


public class MainActivity extends AppCompatActivity implements EndPointCallBack {

    private ProgressBar progressBar;
    private final CountingIdlingResource idlingResource = new CountingIdlingResource("MainActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new JokeAsyncTask().execute(this);
        idlingResource.increment();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResultReady(String result) {
        idlingResource.decrement();
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE, result);
        startActivity(intent);
    }

    public CountingIdlingResource getIdlingResource() {
        return idlingResource;
    }
}
