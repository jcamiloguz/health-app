package com.guzcode.healthapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerViewAdapterAnm extends FragmentPagerAdapter {

    public PagerViewAdapterAnm(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int postition) {
        Fragment fragment = null;
        switch (postition) {
            case 0:
                fragment = new AnmConsult();
                break;
            case 1:
                fragment = new AnmCreate();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
