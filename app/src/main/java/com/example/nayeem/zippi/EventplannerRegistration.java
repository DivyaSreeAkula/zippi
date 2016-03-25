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
import android.support.v7.app.AppCompatActivity;
import android.telephony.gsm.SmsManager;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import  com.example.nayeem.zippi.MultiSpinner.MultiSpinnerListener;
import com.parse.CountCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EventplannerRegistration extends AppCompatActivity {
    int j = 0;
    Bitmap bitimage;
    EditText organisationname, name, phone, email, address, password, events;
    private static int RESULT_LOAD_IMG = 1;
    public ImageView b1;

    String message;
    Integer max, min;
    String planner_name ;
    String planner_organisationname ;
    String planner_email;
    String planner_password ;
    String planner_phone ;
    String planner_address ;
    int planner_events,planners_id,planners_otp;



    EventPlannerModel planners;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrationformofeventplanner);
        planners = new EventPlannerModel();
        b1 = (ImageView) findViewById(R.id.logo);
        bitimage = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        bitimage = Bitmap.createScaledBitmap(bitimage, 190, 110, false);
        min = 100000;
        max = 999999;


        //display of dropdown list view
        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.country_arrays));
        MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.mySpinner1);
        multiSpinner.setItems(list, "SELECT EVENTS");


        ParseQuery<ParseObject> query = ParseQuery.getQuery("eventplanner");
        query.whereEqualTo("KEY_OTP",0);
        query.countInBackground(new CountCallback() {
            @Override
            public void done(int count, ParseException e) {
                // TODO Auto-generated method stub
                if (e == null) {
                    num = count;
                }
                planners_id = count + 1;//1
                planners.otp = 0;//2

            }
        });
    }


    //go to gallery
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
                b1.setImageBitmap(bitimage);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }




    //email id verification
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


        try {
            if (eve.contains("0"))
                planners.wedding = "Y";//11
            else
                planners.wedding = "N";

            if (eve.contains("1"))
                planners.birthday = "Y";
            else//12
                planners.birthday = "N";

            if (eve.contains("2"))
                planners.conference = "Y";//13
            else
                planners.conference = "N";

            if (eve.contains("3"))
                planners.workshop = "Y";
            else//14
                planners.workshop = "N";

            if (eve.contains("4"))
                planners.splfuncs = "Y";
            else//16
                planners.splfuncs = "N";

        } catch (NullPointerException e) {
        }

        organisationname = (EditText) findViewById(R.id.organisationname);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        events = (EditText) findViewById(R.id.events);

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
            Toast.makeText(EventplannerRegistration.this, "Enter All THE Details", Toast.LENGTH_SHORT).show();


        else {

            int valid = 0;

            //password validation
            if (planner_password.length() <= 3)
                Toast.makeText(EventplannerRegistration.this, "The number of Characters entered as password should be atleast 4!", Toast.LENGTH_SHORT).show();
            else {
                valid++;
                //name validation
                if (planner_name.length() <= 3)
                    Toast.makeText(EventplannerRegistration.this, "The number of Characters entered as name should be atleast 4!", Toast.LENGTH_SHORT).show();

                else {
                    valid++;
                    //phn validation
                    if (planner_phone.length() < 10 || planner_phone.length() > 10)
                        Toast.makeText(EventplannerRegistration.this, "Invalid number!", Toast.LENGTH_SHORT).show();
                    else {
                        valid++;
                        //email id validation
                        if (!isValidEmaillId(planner_email.trim()))
                            Toast.makeText(EventplannerRegistration.this, "InValid Email Address.", Toast.LENGTH_SHORT).show();
                        else {
                            valid++;
                            if (valid == 4) {
                                ParseQuery<ParseObject> query = ParseQuery.getQuery("eventplanner");
                                query.whereEqualTo("KEY_PHONENUMBER", planner_phone);
                                query.countInBackground(new CountCallback() {
                                    @Override
                                    public void done(int count, ParseException e) {
                                        // TODO Auto-generated method stub
                                        if (e == null) {

                                            if (count != 0) {
                                                Toast.makeText(EventplannerRegistration.this, "Already Registered as a Event Planner!", Toast.LENGTH_SHORT).show();

                                            } else {
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
                                                Toast.makeText(EventplannerRegistration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(EventplannerRegistration.this, MainActivity.class));
                                                finish();

                                            }

                                        }

                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }




    void add()
    {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitimage.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] img1 = stream.toByteArray();
        ParseObject testObject = new ParseObject("eventplanner");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("eventplanner");
        query.whereEqualTo("KEY_PHONENUMBER", "9676526691");
        testObject.put("KEY_ROWID", 4);
        testObject.put("KEY_PHONENUMBER",planners.phone_number);
        testObject.put("KEY_NAME", planners.name);
        testObject.put("KEY_ORGANISATIONNAME",planners.organisationname);
        testObject.put("KEY_PWD",planners.pwd);
        testObject.put("KEY_EMAIL", planners.email);
        testObject.put("KEY_ADDRESS", planners.address);
        testObject.put("KEY_NUMOFEVENTS",planners.events);
        testObject.put("KEY_RATING", 1);
        testObject.put("KEY_OTP", 0);
        testObject.put("KEY_WEDDING", planners.wedding);
        testObject.put("KEY_BIRTHDAY", planners.birthday);
        testObject.put("KEY_WORKSHOPS", planners.workshop);
        testObject.put("KEY_SPECIALFUNCTIONS", planners.splfuncs);
        testObject.put("KEY_CONFERENCES", planners.conference);
        ParseFile pFile = new ParseFile("frames.png", img1);

        try {
            pFile.save();
            testObject.put("KEY_LOGO", pFile);

        } catch (com.parse.ParseException e) {
            Toast.makeText(EventplannerRegistration.this, "Data not SAVED", Toast.LENGTH_LONG).show();
        }

        testObject.saveInBackground();


    }

    }








