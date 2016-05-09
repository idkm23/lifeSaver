package com.ghris.chun.lifesaver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class NeedleActivity extends FragmentActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.needle_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected void returnToMainActivity() {
        Intent k = new Intent(NeedleActivity.this, MainActivity.class);
        startActivity(k);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {

        }

        return false;
    }
}
