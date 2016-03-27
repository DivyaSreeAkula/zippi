package com.wise.groupproject.zippi;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import android.view.View;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.GetDataCallback;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by DIVYA on 2/25/2016.
 */
public class AboutEventPlanners extends Activity{

    String phn,name1,towhom;
    EventPlannerModel epm;
    ImageView logo;
    int i=1;
    TextView organisationname, name, phone, email, address,  events;
    String orgname;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abouteventplanners);

        epm=new EventPlannerModel();
        Bundle bundle = getIntent().getExtras();
        int value = bundle.getInt("myData");

        orgname=Integer.toString(value);
        organisationname = (TextView) findViewById(R.id.organisationname);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.address);
        events = (TextView) findViewById(R.id.events);

        logo=(ImageView)findViewById(R.id.logo);

       ParseQuery<ParseObject> query = ParseQuery.getQuery("eventplanner");

        query.whereEqualTo("KEY_ROWID", value);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, com.parse.ParseException e) {

                if (!(object == null)) {


                    ParseFile thumbnail = (ParseFile) object.getParseFile("KEY_LOGO");
                    if (thumbnail != null) {
                        thumbnail.getDataInBackground(new GetDataCallback() {

                            @Override
                            public void done(byte[] data, com.parse.ParseException e) {

                                if (e == null) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    logo.setImageBitmap(bmp);


                                }
                            }
                        });
                        String score = object.getString("KEY_PHONENUMBER");
                        String playerName = object.getString("KEY_NAME");
                        String oName = object.getString("KEY_ORGANISATIONNAME");
                        String em = object.getString("KEY_EMAIL");
                        String add = object.getString("KEY_ADDRESS");
                        int e1 = object.getInt("KEY_NUMOFEVENTS");
                        String ra = object.getString("KEY_RATING");
                        organisationname.setText(oName);
                        events.setText("" + e1);
                        email.setText(em);
                        address.setText(add);
                        name.setText(playerName);
                        phone.setText(score);
                        phn=score;
                        name1=playerName;
                        towhom=em;

                    }
                }
            }
        });

    }


    public void calling(View v) {


        String num = phone.getText().toString();
        if (num.matches(""))
            Toast.makeText(AboutEventPlanners.this, "Invalid Number", Toast.LENGTH_SHORT).show();
        else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + num));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(callIntent);
        }
    }

    public void messaging(View v)
    {
        Intent callIntent = new Intent(AboutEventPlanners.this,Messaging.class);
        callIntent.putExtra("phone",phn);
        callIntent.putExtra("name", name1);
        startActivity(callIntent);
    }
    public void mail(View v)

    {
        Intent callIntent = new Intent(AboutEventPlanners.this,SendEmail.class);
        callIntent.putExtra("value",towhom);
        startActivity(callIntent);


    }



}
