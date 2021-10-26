package com.labs.covid19africatracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public TabsAdapter(FragmentManager fm, int i) {
        super(fm );
        this.numOfTabs = i;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new CountriesFrag();
            case 1:
                return new AfricaMapFrag();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
