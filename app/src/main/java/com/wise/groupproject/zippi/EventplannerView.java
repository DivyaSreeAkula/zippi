package com.wise.groupproject.zippi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by NAyeem on 3/12/2016.
 */
public class EventplannerView  extends AppCompatActivity

    {
        EventPlannerModel epm;
        Bitmap bitimage;
        TextView organisationname, name, phone, email, address, password, events;
        public ImageView logo;

        String p,n;
        NavigationView mNavigationView;
        DrawerLayout mDrawerLayout;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.eventplannerview);
            logo = (ImageView) findViewById(R.id.logo);

            epm=new EventPlannerModel();

            Intent intent=getIntent();
            p = intent.getStringExtra("phone");
            n = intent.getStringExtra("name");


            bitimage = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mNavigationView = (NavigationView) findViewById(R.id.nav_view);
            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                                                                  @Override
                                                                  public boolean onNavigationItemSelected(MenuItem menuItem) {
                                                                      mDrawerLayout.closeDrawers();
                                                                      if (menuItem.getItemId() == R.id.nav_edit) {
                                                                          Intent i=new Intent(EventplannerView.this,EventPLannerEditing.class);
                                                                          i.putExtra("phone1",p);
                                                                          i.putExtra("name1",n);
                                                                          startActivity(i);
                                                                          finish();
                                                                      }

                                                                      if (menuItem.getItemId() == R.id.nav_logout) {
                                                                          Intent i=new Intent(EventplannerView.this,EventPlannerLogout.class);
                                                                          startActivity(i);
                                                                          finish();
                                                                      }



                                                                      return false;
                                                                  }

                                                              }
            );


            android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,R.string.app_name);

            mDrawerLayout.setDrawerListener(mDrawerToggle);

            mDrawerToggle.syncState();


            showing();


        }

        public void showing()
        {

            organisationname = (TextView) findViewById(R.id.organisationname);
            name = (TextView) findViewById(R.id.name);
            phone = (TextView) findViewById(R.id.phone);
            email = (TextView) findViewById(R.id.email);
            address = (TextView) findViewById(R.id.address);
            events = (TextView) findViewById(R.id.events);
            password = (TextView) findViewById(R.id.password);
            logo = (ImageView) findViewById(R.id.logo);
         //   Parse.initialize(this, "ZNVMVKIqeBnYzER4elpPnqKJ7SVr5BcMPODCRERd", "T6dOJz3NqJ9wpClknMHlNTPLzIKXfQyOGVrhA8NF");

            ParseQuery<ParseObject> query = ParseQuery.getQuery("eventplanner");

            query.whereEqualTo("KEY_PHONENUMBER",p);


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
                            String pwd=object.getString("KEY_PWD");
                            int e1 = object.getInt("KEY_NUMOFEVENTS");
                            organisationname.setText(oName);
                            events.setText("" + e1);
                            email.setText(em);
                            address.setText(add);
                            name.setText(playerName);
                            phone.setText(score);
                            password.setText(pwd);

                        }
                    }
                }
            });

        }



 }



