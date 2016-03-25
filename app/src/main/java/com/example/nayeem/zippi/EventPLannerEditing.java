package com.example.nayeem.zippi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EventPLannerEditing extends AppCompatActivity {
    EventPlannerModel epm;
    Bitmap bitimage;
    EditText organisationname, name, phone, email, address, password, events;
    private static int RESULT_LOAD_IMG = 1;
    public ImageView logo;
    String planner_organisationname;
    String planner_email;
    String planner_password;
    String planner_address;
    int planner_events, j;
    int id1;
    String p, n, id;
    NavigationView mNavigationView;
    String planner_name, planner_phone;
    DrawerLayout mDrawerLayout;
    EventPLannerEditing validity;
    Integer max, min;
    EventPlannerModel planners;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventplannerediting);
        validity = new EventPLannerEditing();
        logo = (ImageView) findViewById(R.id.logo);
        epm = new EventPlannerModel();
        planners = new EventPlannerModel();

        organisationname = (EditText) findViewById(R.id.organisationname);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        events = (EditText) findViewById(R.id.events);
        password = (EditText) findViewById(R.id.password);
        bitimage = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        Intent intent = getIntent();
        p = intent.getStringExtra("phone1");
        n = intent.getStringExtra("name1");
        min = 100000;
        max = 999999;

        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.country_arrays));
        MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.mySpinner1);
        multiSpinner.setItems(list, "SELECT EVENTS");


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                                                              @Override
                                                              public boolean onNavigationItemSelected(MenuItem menuItem) {
                                                                  mDrawerLayout.closeDrawers();


                                                                  if (menuItem.getItemId() == R.id.nav_homez) {
                                                                      Intent i = new Intent(EventPLannerEditing.this, EventPlannerLogout.class);
                                                                      i.putExtra("phone", p);
                                                                      i.putExtra("name", n);
                                                                      startActivity(i);
                                                                      finish();
                                                                  }


                                                                  return false;
                                                              }

                                                          }
        );


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();


        showing();


    }

    public void showing() {


        ParseQuery<ParseObject> query = ParseQuery.getQuery("eventplanner");

        query.whereEqualTo("KEY_PHONENUMBER", p);


        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, com.parse.ParseException e) {

                if (object == null) {
                    Log.d("score", "The getFirst request failed.");
                } else {

                    ParseFile thumbnail = (ParseFile) object.getParseFile("KEY_LOGO");
                    if (thumbnail != null) {
                        thumbnail.getDataInBackground(new GetDataCallback() {

                            @Override
                            public void done(byte[] data, com.parse.ParseException e) {

                                if (e == null) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    logo.setImageBitmap(bmp);
                                    bitimage = bmp;


                                } else {
                                    Log.e("paser after downloade", " null");
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
                        String pwd = object.getString("KEY_PWD");
                        id1 = object.getInt("KEY_ROWID");
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


    public void gotogallery(View v) {
        startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), RESULT_LOAD_IMG);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == -1 && data != null) {
                String[] filePathColumn = new String[]{"_data"};
                Cursor cursor = getContentResolver().query(data.getData(), filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();
                bitimage = BitmapFactory.decodeFile(filePath);
                bitimage = Bitmap.createScaledBitmap(bitimage, 190, 110, false);
                logo.setImageBitmap(bitimage);


            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    public final static boolean isValidEmaillId(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public void submit(View view) {

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String eve = sharedpreferences.getString("event", null);
        String[] a = {"0", "1", "2", "3", "4"};

        try {
            if (eve.contains(a[0]))
                planners.wedding = "Y";
            else
                planners.wedding = "N";

            if (eve.contains(a[1]))
                planners.birthday = "Y";
            else
                planners.birthday = "N";

            if (eve.contains(a[2]))
                planners.conference = "Y";
            else
                planners.conference = "N";

            if (eve.contains(a[3]))
                planners.workshop = "Y";
            else
                planners.workshop = "N";

            if (eve.contains(a[4]))
                planners.splfuncs = "Y";
            else
                planners.splfuncs = "N";

        } catch (NullPointerException e) {
        }


        planner_name = name.getText().toString();//3
        planner_organisationname = name.getText().toString();//4
        planner_email = email.getText().toString();//5
        planner_password = password.getText().toString();//6
        planner_phone = phone.getText().toString();//7
        planner_address = address.getText().toString();//8
        String event = events.getText().toString();//9
        planner_events = Integer.parseInt(event);//10


        //null values validation in all fields
        if (planner_password.matches("") ||
                planner_name.matches("") ||
                planner_organisationname.matches("") ||
                planner_phone.matches("") ||
                planner_email.matches("") ||
                planner_address.matches("") ||
                planner_events == 0)
            Toast.makeText(EventPLannerEditing.this, "Enter All THE Details", Toast.LENGTH_SHORT).show();


        else {

            int valid = 0;

            //password validation
            if (planner_password.length() <= 3)
                Toast.makeText(EventPLannerEditing.this, "The number of Characters entered as password should be atleast 4!", Toast.LENGTH_SHORT).show();
            else {
                valid++;
                //name validation
                if (planner_name.length() <= 3)
                    Toast.makeText(EventPLannerEditing.this, "The number of Characters entered as name should be atleast 4!", Toast.LENGTH_SHORT).show();

                else {
                    valid++;
                    //phn validation
                    if (planner_phone.length() < 10 || planner_phone.length() > 10)
                        Toast.makeText(EventPLannerEditing.this, "Invalid number!", Toast.LENGTH_SHORT).show();
                    else {
                        valid++;
                        //email id validation
                        if (!isValidEmaillId(planner_email.trim()))
                            Toast.makeText(EventPLannerEditing.this, "InValid Email Address.", Toast.LENGTH_SHORT).show();
                        else {
                            valid++;
                            if (valid == 4) {

                                                planners.bitmap = bitimage;
                                                planners.name = planner_name;
                                                planners.organisationname = planner_organisationname;
                                                planners.pwd = planner_password;
                                                planners.email = planner_email;
                                                planners.phone_number = planner_phone;
                                                planners.address = planner_address;
                                                planners.events = planner_events;
                                                planners.rating = 1;


                                                add();

                                                Toast.makeText(EventPLannerEditing.this, "Registered Successfully", Toast.LENGTH_SHORT).

                                                        show();

                                                startActivity(new Intent(EventPLannerEditing.this, MainActivity.class));

                                                finish();


                            }

                        }
                    }
                }
            }

        }
    }

    void add() {


        ParseQuery<ParseObject> query = ParseQuery.getQuery("eventplanner");
        query.whereEqualTo("KEY_ROWID", id1);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject testObject, com.parse.ParseException e) {

                if (e == null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitimage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] img1 = stream.toByteArray();
                    testObject.put("KEY_PHONENUMBER", p);
                    testObject.put("KEY_PHONENUMBER", planners.phone_number);
                    testObject.put("KEY_NAME", planners.name);
                    testObject.put("KEY_ORGANISATIONNAME", planners.organisationname);
                    testObject.put("KEY_PWD", planners.pwd);
                    testObject.put("KEY_EMAIL", planners.email);
                    testObject.put("KEY_ADDRESS", planners.address);
                    testObject.put("KEY_NUMOFEVENTS", planners.events);
                    testObject.put("KEY_RATING", 1);
                    testObject.put("KEY_OTP", 0);
                    testObject.put("KEY_WEDDING", planners.wedding);
                    testObject.put("KEY_BIRTHDAY", planners.birthday);
                    testObject.put("KEY_WORKSHOPS", planners.workshop);
                    testObject.put("KEY_SPECIALFUNCTIONS", planners.splfuncs);
                    testObject.put("KEY_CONFERENCES", planners.conference);
                    ParseFile pFile = new ParseFile("logo.png", img1);

                    try {
                        pFile.save();
                        testObject.put("KEY_LOGO", pFile);

                    } catch (com.parse.ParseException e1) {
                        Toast.makeText(EventPLannerEditing.this, "Data not SAVED", Toast.LENGTH_LONG).show();
                    }

                    testObject.saveInBackground();


                } else {
                    Log.d("Post retrieval", "Error: " + e.getMessage());
                }
            }
        });


    }
}
