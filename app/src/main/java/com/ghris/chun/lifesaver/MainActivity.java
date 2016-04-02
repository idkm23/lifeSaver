package com.ghris.chun.lifesaver;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    private MainFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
    }
}
