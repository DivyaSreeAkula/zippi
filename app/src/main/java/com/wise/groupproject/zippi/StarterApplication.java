package com.wise.groupproject.zippi;

/**
 * Created by DIVYA on 3/26/2016.
 */
import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this, "ZNVMVKIqeBnYzER4elpPnqKJ7SVr5BcMPODCRERd", "T6dOJz3NqJ9wpClknMHlNTPLzIKXfQyOGVrhA8NF");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}

