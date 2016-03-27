package com.wise.groupproject.zippi;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);


        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                              @Override
                                                              public boolean onNavigationItemSelected(MenuItem menuItem) {
                                                                  mDrawerLayout.closeDrawers();


                                                                  if (menuItem.getItemId() == R.id.nav_about) {
                                                                      startActivity(new Intent(MainActivity.this, AboutFragment.class));

                                                                  }

                                                                  if (menuItem.getItemId() == R.id.nav_homez) {
                                                                      startActivity(new Intent(MainActivity.this, MainActivity.class));
                                                                  }
                                                                  if (menuItem.getItemId() == R.id.nav_sign) {
                                                                      startActivity(new Intent(MainActivity.this, EventplannerRegistration.class));

                                                                  }
                                                                  if (menuItem.getItemId() == R.id.nav_login) {
                                                                      startActivity(new Intent(MainActivity.this, EventPlannerLogin.class));

                                                                  }
                                                                  if (menuItem.getItemId() == R.id.nav_feedback) {
                                                                      startActivity(new Intent(MainActivity.this, feedback.class));

                                                                  }
                                                                  if (menuItem.getItemId() == R.id.nav_help) {
                                                                      startActivity(new Intent(MainActivity.this, help.class));

                                                                  }

                                                                  return false;
                                                              }

                                                          }

        );

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }


}