package com.ghris.chun.lifesaver;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Button;

/**
 * Created by chris on 4/1/2016.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final int  MENU_POS = 0, READY_MAIN_POS = 1, INFO_POS = 2;
    public static final int PAGE_COUNT = 3;

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case MENU_POS:
                return new MenuFragment();
            case READY_MAIN_POS:
                ReadyMainFragment temp = new ReadyMainFragment();

                return temp;
            case INFO_POS:
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
