package com.playbasis.pb.mobileguide.presentation.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.playbasis.pb.mobileguide.presentation.favorlitelist.FavoriteListFragment;
import com.playbasis.pb.mobileguide.presentation.mobilelist.MobileListFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private MobileListFragment mobileListFragment;
    private FavoriteListFragment favoriteListFragment;

    public SectionsPagerAdapter(FragmentManager fm, MobileListFragment mobileListFragment, FavoriteListFragment favoriteListFragment) {
        super(fm);
        this.mobileListFragment = mobileListFragment;
        this.favoriteListFragment = favoriteListFragment;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = mobileListFragment;

        switch (position){
            case 0:
                fragment = mobileListFragment;
                break;
            case 1:
                fragment = favoriteListFragment;
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

}