package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.projects.alexanderauer.jokeactivity.JokeTellingActivity;
import com.projects.alexanderauer.jokinggcm.myJokingApi.MyJokingApi;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inflate the progress bar
        spinner = (ProgressBar)findViewById(R.id.progressBar);
    }

    public void tellJoke(View view) {
        // show the progress bar
        spinner.setVisibility(View.VISIBLE);

        // start the AsyncTask to fetch the joke
        new EndpointsAsyncTask().execute(new Pair<Context, ProgressBar>(this,spinner));
    }
}

class EndpointsAsyncTask extends AsyncTask<Pair<Context,ProgressBar>, Void, String> {
    private static MyJokingApi myJokingApiService = null;
    private Context context;
    private ProgressBar spinner = null;

    @Override
    protected String doInBackground(Pair<Context,ProgressBar>... params) {
        context = params[0].first;
        spinner = params[0].second;

        if(myJokingApiService == null) {  // Only do this once
            MyJokingApi.Builder builder = new MyJokingApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(context.getString(R.string.root_url))
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myJokingApiService = builder.build();
        }

        try {
            // do the web request and return the joke
            return myJokingApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // loading is finished -> hide the progress bar
        if(spinner != null)
            spinner.setVisibility(View.GONE);

        // start the joke telling activity from our android library
        Intent jokeTellerIntent = new Intent(context,JokeTellingActivity.class);

        jokeTellerIntent.putExtra(JokeTellingActivity.EXTRA_JOKE,result);

        context.startActivity(jokeTellerIntent);
    }
}
