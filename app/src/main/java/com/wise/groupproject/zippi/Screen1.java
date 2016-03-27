package com.wise.groupproject.zippi;

/**
 * Created by NAyeem on 2/13/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Screen1 extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(R.layout.screen1);
        if (!(isNetworkAvailable())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Screen1.this);
            builder.setMessage("You must have internet access to access this app")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(Screen1.this, Screen1.class));
                            finish();

                        }
                    });


            AlertDialog alert = builder.create();
            alert.setTitle("NO Internet Connection!!");
            alert.setIcon(android.R.drawable.ic_dialog_alert);
            alert.show();


        }
        else
        {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(Screen1.this, HomeFragment.class);
                startActivity(i);

                finish();
            }
        }, 2000);
    }
    }
    boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
