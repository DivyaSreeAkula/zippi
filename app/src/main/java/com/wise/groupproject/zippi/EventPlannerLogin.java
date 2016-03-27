package com.wise.groupproject.zippi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by DIVYA on 3/11/2016.
 */
public class EventPlannerLogin extends Activity {
    EditText phone_no, pwd;
    ParseQuery<ParseObject> query = ParseQuery.getQuery("eventplanner");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginofeventplanner);
        phone_no = (EditText) findViewById(R.id.phone);
        pwd= (EditText) findViewById(R.id.password);

   //     Parse.initialize(this, "ZNVMVKIqeBnYzER4elpPnqKJ7SVr5BcMPODCRERd", "T6dOJz3NqJ9wpClknMHlNTPLzIKXfQyOGVrhA8NF");


    }

    public void login(View v) {
        final String user_pwd = pwd.getText().toString();
        final String user_phn = phone_no.getText().toString();
        if (user_pwd.matches("") || user_pwd.matches("Password") || user_phn.matches("") || user_phn.matches("PHN"))
            Toast.makeText(EventPlannerLogin.this, "Enter All The Details", Toast.LENGTH_SHORT).show();

            //if there exist no event planner with the phn no
        else {

            query.whereEqualTo("KEY_PHONENUMBER", user_phn);
            query.whereEqualTo("KEY_PWD",user_pwd);

                            query.getFirstInBackground(new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject object, com.parse.ParseException e) {
                                    if (object == null) {
                                        Toast.makeText(EventPlannerLogin.this, "Wrong Credentials! Re-Enter", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String score = object.getString("KEY_PHONENUMBER");
                                        String pwd = object.getString("KEY_PWD");
                                        if (score.matches(user_phn) && pwd.matches(user_pwd)) {

                                            Intent intent = new Intent(EventPlannerLogin.this, WelcomeScreen.class);
                                            intent.putExtra("key1", object.getString("KEY_NAME"));
                                            intent.putExtra("key2", user_phn);
                                            startActivity(intent);
                                            finish();
                                        } else
                                            Toast.makeText(EventPlannerLogin.this, "Wrong Credentials! Re-Enter", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }
                    }


                public void register(View v) {
                    startActivity(new Intent(EventPlannerLogin.this, EventplannerRegistration.class));
                    finish();
                }

            }
