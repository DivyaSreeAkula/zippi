package com.example.nayeem.zippi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Messaging extends AppCompatActivity {
    Button date,time,send;
   // EditText messagetext;
    TextView phone,dt,dd;
    String message;
    String name1,phn;
    int year_x,month_x,day_x,minute_x,hour_x;
    static final int DIALOG_ID=0, DIALOG_ID1=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        phone = (TextView) findViewById(R.id.phone);
        dd = (TextView) findViewById(R.id.displaydate);
        dt = (TextView) findViewById(R.id.displaytime);
        dd.setVisibility(View.GONE);
        dt.setVisibility(View.GONE);
        Intent i=getIntent();
        name1=i.getStringExtra("name");
        phn=i.getStringExtra("phone");
        phone.setText(name1);



        final Calendar cal= Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
        showTimePickerDialog();

        Button seven = (Button)findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText budget = (EditText) findViewById(R.id.budget);
                EditText four = (EditText) findViewById(R.id.four);
                String budg = String.valueOf(budget.getText());
                String capacity = String.valueOf(four.getText());

                  message = "Hello " + name1 + "! This Is From Yep!The event is on  " +
                        day_x + "/" + month_x + "/" + year_x + " at  " + hour_x +
                        ":" + minute_x + " Within the  budget of  " + budg + ", number of persons invited are: " + capacity + ". Do you agree to do this event?";

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Messaging.this);
                final EditText edittext = new EditText(Messaging.this);
                alertDialog.setTitle("MESSAGE:");
                alertDialog.setMessage(message);
                alertDialog.setView(edittext);

                alertDialog.setPositiveButton("SEND SMS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                         if (message.length() > 0) {
                             try {
                                 SmsManager smsManager=SmsManager.getDefault();
                                 smsManager.sendTextMessage(phn,null,message,null,null);
                                 Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                             }
                             catch(Exception e)
                             {
                                 Toast.makeText(getApplicationContext(), "SMS failed.", Toast.LENGTH_LONG).show();
                                 e.printStackTrace();
                             }


                         } else {
                            Toast.makeText(getBaseContext(), "Enter Message", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent callIntent = new Intent(Messaging.this, Messaging.class);
                        callIntent.putExtra("phone", phn);
                        callIntent.putExtra("name", name1);
                        startActivity(callIntent);
                    }
                });
                alertDialog.show();
            }

        });
        }






    public void showDialogOnButtonClick()
    {
        date = (Button)findViewById(R.id.date);
        date.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);

                    }

                }

        );
    }
    public void showTimePickerDialog()
    {
        time = (Button)findViewById(R.id.time);
        time.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View V)
                    {
                        showDialog(DIALOG_ID1);
                    }


                }
        );
    }
    @Override
    protected Dialog onCreateDialog(int id)
    {

        if(id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerlistener, year_x, month_x, day_x);
        }
        else if(id == DIALOG_ID1) {
            return new TimePickerDialog(this, kTimePickerListener, hour_x, minute_x, true);
        }
        else
            return null;
    }
    public TimePickerDialog.OnTimeSetListener kTimePickerListener =
            new TimePickerDialog.OnTimeSetListener(){
                public void onTimeSet(TimePicker view, int hourOfDay,int minute)
                {
                    hour_x = hourOfDay;
                    minute_x = minute;
                    String time=" "+hour_x+":"+minute_x;
                    dt.setText(time);
                    dt.setVisibility(View.VISIBLE);

                }

            };

    public DatePickerDialog.OnDateSetListener dpickerlistener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x=year;
            month_x=monthOfYear + 1;
            day_x=dayOfMonth;
            String date=" "+day_x+"/"+month_x+"/"+year_x;
            dd.setText(date);
            dd.setVisibility(View.VISIBLE);

        }

    };




}
