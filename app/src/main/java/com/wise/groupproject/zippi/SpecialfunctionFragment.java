package com.wise.groupproject.zippi;

/**
 * Created by NAyeem on 2/14/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class SpecialfunctionFragment  extends Fragment {

    ListView lvHomePage;

    String name;
    int rating;
    TextView txt;
    ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listviewofeventplanners, container, false);
        lvHomePage = (ListView) view.findViewById(R.id.listView);
        txt=(TextView)view.findViewById(R.id.textView);
        adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.each);



        lvHomePage.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String txt = ((TextView)view).getText().toString();

                ParseQuery<ParseObject> query1 = ParseQuery.getQuery("eventplanner");

                query1.whereEqualTo("KEY_ORGANISATIONNAME", txt);
                query1.getFirstInBackground(new GetCallback<ParseObject>() {
                                                @Override
                                                public void done(ParseObject object, com.parse.ParseException e) {

                                                    if (!(object == null)) {
                                                        int playerName = object.getInt("KEY_ROWID");
                                                        Bundle bundle = new Bundle();
                                                        bundle.putInt("myData", playerName);
                                                        Intent in = new Intent(getActivity(), AboutEventPlanners.class);
                                                        in.putExtras(bundle);
                                                        startActivity(in);
                                                    }

                                                }

                                            }
                );
            }
        });
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("eventplanner");
        query.whereEqualTo("KEY_SPECIALFUNCTIONS", "y");
        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> nameList, com.parse.ParseException e) {
                if (e == null)

                {
                    for (int i = 0; i < nameList.size(); i++) {
                        ParseObject object = (ParseObject) nameList.get(i);
                        rating = object.getInt("KEY_RATING");
                        name = object.getString("KEY_ORGANISATIONNAME");
                        adapter.add(name);

                    }


                }


            }


        });
        lvHomePage.setAdapter(adapter);

        return view;
    }

}
