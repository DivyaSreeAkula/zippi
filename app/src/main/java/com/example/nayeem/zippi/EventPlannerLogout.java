package com.example.nayeem.zippi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by DIVYA on 3/11/2016.
 */
public class EventPlannerLogout  extends Activity {
    private static int SCREEN1_TIME_OUT = 1000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(EventPlannerLogout.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SCREEN1_TIME_OUT);
    }
}
