package com.ghris.chun.lifesaver;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class FinalTimerActivity extends FragmentActivity implements View.OnTouchListener{

    public View rootView;
    public TextView counter_textview;
    private CountDownTimer shortTimer;
    private boolean leftInstance;
    private MediaPlayer beepSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_timer_main);
        leftInstance = false;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        beepSound = MediaPlayer.create(this, R.raw.beep);
        beepSound.setLooping(true);
        beepSound.start();
    }

    @Override
    public void onResume() {
        super.onResume();

        if(leftInstance) {
            returnToMainActivity();
        }

        rootView = findViewById(R.id.final_timer_main);
        counter_textview = (TextView) rootView.findViewById(R.id.countdown_clock);
        rootView.findViewById(R.id.final_timer_button).setOnTouchListener(this);
        startShortTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        shortTimer.cancel();
        beepSound.stop();
        leftInstance = true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Log.d("lifeSaver", "" + event.getAction());
        if(v.getId() != R.id.final_timer_button) {
            return false;
        }

        returnToMainActivity();

        return false;
    }

    protected void startShortTimer() {
        if(shortTimer != null) {
            shortTimer.cancel();
        }


        shortTimer = new CountDownTimer(16000, 100) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {
                counter_textview.setText(String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                String url = MainActivity.instance.getPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));

                if (ActivityCompat.checkSelfPermission(MainActivity.instance.getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }
            }
        };

        shortTimer.start();
    }

    protected void returnToMainActivity() {
        Intent k = new Intent(FinalTimerActivity.this, MainActivity.class);
        startActivity(k);
    }
}
