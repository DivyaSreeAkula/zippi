package com.wise.groupproject.zippi;

/**
 * Created by NAyeem on 2/14/2016.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabFragment extends Fragment{
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 6 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.tab_layout,null);

        tabLayout = (TabLayout) x.findViewById(R.id.tabs);

        viewPager = (ViewPager) x.findViewById(R.id.viewpager);



        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
               case 0 : return new HomeFragment();
                case 1 : return new WorkshopFragment();
                case 2 : return new ConferenceFragment();
                case 3 : return new BirthdayFragment();
                case 4 : return new WeddingFragment();
                case 5 : return new SpecialfunctionFragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Home";
                case 1 :
                    return "Workshop";
                case 2 :
                    return "Conference";
                case 3 :
                    return "Birthday";
                case 4:
                    return "Wedding";
                case 5 :
                    return "Special Function";

            }
            return null;
        }
    }

}

