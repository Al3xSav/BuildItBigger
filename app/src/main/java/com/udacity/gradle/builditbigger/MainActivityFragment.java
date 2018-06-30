package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexsav.builditbiggerlibrary.JokePopUp;
import com.ekalips.fancybuttonproj.FancyButton;
import com.udacity.gradle.builditbigger.utils.AdwaresUtils;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    FancyButton jokeButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeButton = root.findViewById(R.id.show_jokes_button);
        jokeButton.setOnClickListener(v -> showJokes(v.getContext().getApplicationContext()));
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."

        AdwaresUtils.initializeAds(root.getContext());
        AdwaresUtils.showBannerAd(root);
        return root;
    }


    private void showJokes(final Context context) {

        final MainActivity mainActivity = (MainActivity) getActivity();

        if (mainActivity != null) {
            mainActivity.setIdlingResource(false);
        }

        new JokeAsyncTask().execute(new AsyncTaskCallback() {
            @Override
            public void setLoading(final boolean loading) {
                if (mainActivity != null && !mainActivity.isFinishing()) {
                    mainActivity.runOnUiThread(() -> {
                        if (loading) {
                            jokeButton.setClickable(false);
                            jokeButton.collapse();
                        } else {
                            jokeButton.setClickable(true);
                            jokeButton.expand();
                        }
                    });
                }
            }

            @Override
            public void jokeDisplayer(String joke) {
                AdwaresUtils.showInterstitialAd(context, () -> {
                    if (mainActivity != null) {
                        mainActivity.setIdlingResource(true);
                    }
                }, () -> {
                    if (mainActivity != null && !mainActivity.isFinishing()
                            && !mainActivity.isDestroyed()) {
                        JokePopUp.jokeDisplayer(context, joke);
                    }
                    setLoading(false);
                });
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public interface AsyncTaskCallback {
        void setLoading(final boolean loading);

        void jokeDisplayer(final String joke);
    }

}
