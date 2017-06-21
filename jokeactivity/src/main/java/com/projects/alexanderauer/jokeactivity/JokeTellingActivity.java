package com.projects.alexanderauer.jokeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * The Activity that provides the TextView with the joke
 */

public class JokeTellingActivity extends AppCompatActivity {

    public static String EXTRA_JOKE = "Joke Extra";

    public JokeTellingActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_teller);
    }
}
