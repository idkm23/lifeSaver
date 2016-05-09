package com.ghris.chun.lifesaver;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    public static MainActivity instance;
    private static String phoneNumber = "tel:2124797990";
    private static String location = "no location specified";

    private ViewPager viewPager;
    private MainFragmentPagerAdapter mAdapter;
    private Boolean unpausing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lifeSaver", "createdMain");
        setContentView(R.layout.activity_main);
        instance = this;
        unpausing = false;

        loadPager();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(unpausing) {
            ImageView ready_button = (ImageView) findViewById(R.id.ready_button);
            ready_button.setImageResource(R.mipmap.plainbutton13);
            ((TextView) findViewById(R.id.countdown_clock)).setText("1:00");
        }
    }

    public void onPause() {
        super.onPause();
        unpausing = true;
    }

    public void loadPager() {
        viewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(MainFragmentPagerAdapter.READY_MAIN_POS);
    }

    public void setCurrentFragment(int arg) {
        viewPager.setCurrentItem(arg);
    }

    public void initiateActivity(Class c) {
        Intent k = new Intent(MainActivity.this, c);
        startActivity(k);
    }

    public static void setPhoneNumber(String phoneNumber) {
        MainActivity.phoneNumber = "tel:" + phoneNumber;
    }
    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setLocation(String location) {
        MainActivity.location = location;
    }
    public static String getLocation() {
        return location;
    }
}
