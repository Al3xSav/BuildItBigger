package com.alexsav.jokelibrary;
import java.util.Random;

public class Jokes {
    private String[] jokesList = {
            "I wondered why the frisbee was getting bigger, and then it hit me.",
            "What did the router tell the doctor? \n- It hurts when IP!",
            "What do you call a bear with no teeth? \n- A gummy bear!",
            "What do you call two fat people having a chat? \n- A heavy discussion"
    };

    public String showJokes() {
        int randomIndex = new Random().nextInt(jokesList.length);
        return jokesList[randomIndex];
    }
}
