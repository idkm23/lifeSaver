package com.ghris.chun.lifesaver;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    public static MainActivity instance;

    private ViewPager viewPager;
    private MainFragmentPagerAdapter mAdapter;
    private Boolean unpausing;
    private static String phoneNumber = "tel:2124797990";

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
            Button ready_button = (Button) findViewById(R.id.ready_button);
            ready_button.getBackground().setColorFilter(getResources().getColor(R.color.ready_button_ready), PorterDuff.Mode.SRC);
            ready_button.setText(getString(R.string.ready_button_cancel));
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

    public void initiateFinalCountdown() {
        Intent k = new Intent(MainActivity.this, FinalTimerActivity.class);
        startActivity(k);
    }

    public void initiateEmergencyContact() {
        Intent k = new Intent(MainActivity.this, EmergencyContactActivity.class);
        startActivity(k);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = "tel:" + phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
