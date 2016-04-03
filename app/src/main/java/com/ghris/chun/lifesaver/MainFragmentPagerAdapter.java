package com.ghris.chun.lifesaver;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by chris on 4/1/2016.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public static int PAGE_COUNT = 3;

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new ReadyMainFragment();
            case 1:
                return new MenuFragment();
            case 2:
                return new InformationFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
