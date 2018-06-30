package com.udacity.gradle.builditbigger.utils;

import android.content.Context;
import android.view.View;

public final class AdwaresUtils {

    public static void initializeAds(final Context context) {
        // Do nothing
    }

    public static void showBannerAd(final View root) {
        // Do nothing
    }

    public static void showInterstitialAd(final Context context, final Runnable onAdOpened,
                                          final Runnable onAdClosed) {
        onAdOpened.run();
        onAdClosed.run();
    }
}
