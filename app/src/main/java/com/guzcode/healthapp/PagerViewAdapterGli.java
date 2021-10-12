package com.guzcode.healthapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerViewAdapterGli extends FragmentPagerAdapter {

    public PagerViewAdapterGli(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new GliConsult();
                break;
            case 1:
                fragment = new GliCreate();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
