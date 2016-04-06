package com.ghris.chun.lifesaver;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    public static MainActivity instance;
    private ViewPager viewPager;
    private MainFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        viewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(MainFragmentPagerAdapter.READY_MAIN_POS);
    }
}
