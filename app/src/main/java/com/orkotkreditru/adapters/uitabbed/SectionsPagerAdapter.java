package com.orkotkreditru.adapters.uitabbed;



import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.orkotkreditru.MainClass;
import com.orkotkreditru.fragments.FragmentAllList;
import com.orkotkreditru.fragments.FragmentWithBadList;
import com.orkotkreditru.fragments.FragmentZeroList;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_TITLES = new String[]{MainClass.first, MainClass.second, MainClass.third};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentAllList();
                break;
            case 1:
                fragment = new FragmentZeroList();
                break;
            case 2:
                fragment = new FragmentWithBadList();
                break;
        }
        assert fragment != null;
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return MainClass.numberOfTabs;
    }
}