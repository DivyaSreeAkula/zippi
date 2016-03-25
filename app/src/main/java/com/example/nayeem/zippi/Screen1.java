package com.example.nayeem.zippi;

/**
 * Created by NAyeem on 2/13/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.parse.Parse;
import com.parse.ParseObject;

public class Screen1 extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this," enter your application id","enter your client key");
        setContentView(R.layout.screen1);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                Intent i = new Intent(Screen1.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        },2000);
    }

}