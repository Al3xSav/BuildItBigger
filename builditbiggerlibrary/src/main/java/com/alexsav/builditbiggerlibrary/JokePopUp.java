package com.alexsav.builditbiggerlibrary;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

public final class JokePopUp {
    public static void jokeDisplayer(final Context context, final String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        context.startActivity(intent);
    }
}
