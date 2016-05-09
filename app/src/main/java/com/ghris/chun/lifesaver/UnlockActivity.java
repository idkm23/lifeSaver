package com.ghris.chun.lifesaver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;

public class UnlockActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_to_unlock);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ((SlideButton) findViewById(R.id.unlockButton)).setSlideButtonListener(new SlideButtonListener() {
            @Override
            public void handleSlide() {
                goToLocationActivity();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected void goToLocationActivity() {
        Intent k = new Intent(UnlockActivity.this, LocationActivity.class);
        startActivity(k);
    }
}
