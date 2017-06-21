package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;
import android.widget.ProgressBar;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertNotEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    String globalResult = "";

    @Test
    public void checkAsyncTask() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        // start the AsyncTask with an overwritten onPostExecute Method
        new EndpointsAsyncTask(){
            @Override
            protected void onPostExecute(String result) {
                globalResult = result;

                signal.countDown();
            }
        }.execute(new Pair<Context, ProgressBar>(null,null));

        signal.await();

        // check if the retrieved string is not empty
        assertNotEquals(globalResult,"");
    }
}
