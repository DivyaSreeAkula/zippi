package com.example.nayeem.zippi;

/**
 * Created by DIVYA on 3/13/2016.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Aneh Thakur on 5/7/2015.
 */
public class  MultiSpinner extends Spinner implements
        OnMultiChoiceClickListener, DialogInterface.OnCancelListener
{
        String str="Selected Events are: ";
        private List<String> listitems;
        private boolean[] checked;
        public static final String MyPREFERENCES = "MyPrefs" ;
        Context context=getContext();
        SharedPreferences sharedpreferences;
        String num="";

        public  MultiSpinner(Context context)
        {
                super(context);
        }

        public  MultiSpinner(Context arg0, AttributeSet arg1)
        {
                super(arg0, arg1);
        }

        public  MultiSpinner(Context arg0, AttributeSet arg1, int arg2)
        {
                super(arg0, arg1, arg2);
        }

        @Override
        public void onClick(DialogInterface dialog, int ans, boolean isChecked)
        {
                if (isChecked)
                        checked[ans] = true;
                else
                        checked[ans] = false;
        }


        @Override
        public void onCancel(DialogInterface dialog)
        {
                for (int i = 0; i < listitems.size(); i++)
                {
                        if (checked[i])
                        {
                                str=str+" "+listitems.get(i)+",";
                                num=num+" "+i;
                        }
                }


                AlertDialog.Builder alert1 = new AlertDialog.Builder(getContext());
                alert1.setTitle("Items:");
                alert1.setMessage(str);
                alert1.setPositiveButton("Ok", null);
                alert1.show();
                sharedpreferences = context.getSharedPreferences(MyPREFERENCES, context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("event", num);
//                Toast.makeText(context,num, Toast.LENGTH_LONG).show();
                num="";
                editor.commit();
                str="Selected Events are: ";
        }

        @Override
        public boolean performClick()
        {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMultiChoiceItems(
                        listitems.toArray(new CharSequence[listitems.size()]), checked, this);
                builder.setPositiveButton("done",
                        new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {

                                        dialog.cancel();

                                }
                        });
                builder.setOnCancelListener(this);
                builder.show();
                return true;
        }

        public void setItems(List<String> items, String allText)
        {
                this.listitems = items;

                checked = new boolean[items.size()];
                for (int i = 0; i < checked.length; i++)
                        checked[i] =false;


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, new String[] { allText });
                setAdapter(adapter);



        }

        public interface  MultiSpinnerListener
        {
                public void onItemschecked(boolean[] checked);
        }

}