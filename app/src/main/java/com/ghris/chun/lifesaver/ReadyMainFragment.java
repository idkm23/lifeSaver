package com.ghris.chun.lifesaver;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

/**
 * Created by chris on 4/1/2016.
 */
public class ReadyMainFragment extends Fragment implements View.OnClickListener {
    private TextView counter_textview;
    private static final String FORMAT = "%d:%02d";
    private CountDownTimer timer;
    private boolean ticking = false;

    // Add Image View Variable
    private ImageView navButton, infoButton, ready;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.ready_main_fragment, container, false);
        counter_textview = (TextView) rootView.findViewById(R.id.countdown_clock);
        ready = (ImageView) rootView.findViewById(R.id.ready_button);
        rootView.findViewById(R.id.ready_button).setOnClickListener(this);

        navButton = (ImageView) rootView.findViewById(R.id.nav_button);
        infoButton = (ImageView) rootView.findViewById(R.id.info_button);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change to info state
                MainActivity.instance.setCurrentFragment(MainFragmentPagerAdapter.INFO_POS);
            }
        });

        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change to info state
                MainActivity.instance.setCurrentFragment(MainFragmentPagerAdapter.MENU_POS);
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() != R.id.ready_button) {
            return;
        }

        ImageView iv = (ImageView)v;

        if(ticking)
            iv.setImageResource(R.mipmap.plainbutton13);
        else
            iv.setImageResource(R.mipmap.redbuttonstop);

        toggleTimer();
    }

    public void toggleTimer() {
        if(timer == null) {
            timer = new CountDownTimer(60000, 100) { // adjust the milli seconds here
                public void onTick(long millisUntilFinished) {
                    counter_textview.setText(String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {

                    MainActivity.instance.initiateActivity(FinalTimerActivity.class);

//                    // counter_textview.setText("-:--");
//                    new AlertDialog.Builder(getContext())
//                            .setTitle("On Hold!")
//                            .setMessage("Are you good? Do you want to hold another 3 mins?")
//                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // continue with delete
//                                    timer.onTick(180000);
//                                }
//                            })
//                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // do nothing
//                                    //// TODO: 4/14/2016 Add state for the "Sending SMS"
//
//                                }
//                            })
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .show();
                }
            };
        }

        if(ticking) {
            ticking = false;
            timer.cancel();
            counter_textview.setText(getString(R.string.timer_duration));
        } else {
            ticking = true;
            timer.start();
        }
    }

}
