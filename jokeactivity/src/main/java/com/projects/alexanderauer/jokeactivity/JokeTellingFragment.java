package com.projects.alexanderauer.jokeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Alex on 12.06.2017.
 */

public class JokeTellingFragment extends Fragment {

    public JokeTellingFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke_teller, container, false);

        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(JokeTellingActivity.EXTRA_JOKE);

        TextView jokeTextView = (TextView) root.findViewById(R.id.joke);

        if (joke != null && !joke.equals("")) {
            jokeTextView.setText(joke);
        }

        return root;
    }

}
