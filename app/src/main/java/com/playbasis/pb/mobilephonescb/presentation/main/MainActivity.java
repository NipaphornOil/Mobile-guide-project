package com.playbasis.pb.mobilephonescb.presentation.main;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.playbasis.pb.mobilephonescb.R;
import com.playbasis.pb.mobilephonescb.presentation.favorlitelist.FavoriteListFragment;
import com.playbasis.pb.mobilephonescb.presentation.mobilelist.MobileAdapter;
import com.playbasis.pb.mobilephonescb.presentation.mobilelist.MobileListFragment;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private MobileListFragment mobileListFragment;
    private FavoriteListFragment favoriteListFragment;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mobileListFragment = new MobileListFragment();
        favoriteListFragment = new FavoriteListFragment();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mobileListFragment, favoriteListFragment);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpage1);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab1);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.one:
                mobileListFragment.sortByPriceDescending();
                favoriteListFragment.sortByPriceDescending();

                return true;

            case R.id.two:
                mobileListFragment.sortByPriceAscending();
                favoriteListFragment.sortByPriceAscending();

                return true;

            case R.id.three:
                mobileListFragment.sortMobileByRating();
                favoriteListFragment.sortFavoriteByRating();

                return true;

            default:
                return false;
        }


    }

}
