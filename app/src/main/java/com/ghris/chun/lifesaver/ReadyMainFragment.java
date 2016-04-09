package com.ghris.chun.lifesaver;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by chris on 4/1/2016.
 */
public class ReadyMainFragment extends Fragment implements View.OnTouchListener {
    private TextView counter_textview;
    private Button ready;
    private static final String FORMAT = "%02d:%02d";
    private int seconds , minutes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ready_main_fragment, container, false);
        counter_textview = (TextView) rootView.findViewById(R.id.countdown_clock);
        ready = (Button) rootView.findViewById(R.id.ready_button);

        /*
        * Start counting when user hit the button
        * //todo: Fix the time clock for not to collapse with another activity when changing fragments
        * */
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(300000, 1000) { // adjust the milli seconds here
                    public void onTick(long millisUntilFinished) {
                        counter_textview.setText("" + String.format(FORMAT,
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    }

                    public void onFinish() {
                        counter_textview.setText("done!");
                    }
                }.start();
            }
        });
        rootView.findViewById(R.id.ready_button).setOnTouchListener(this);

        return rootView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(v.getId() != R.id.ready_button) {
            return false;
        }

        Log.d("lifeSaver", "" + event.getAction());

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("lifeSaver", "pressed: " + R.color.ready_button_pressed);
                v.getBackground().setColorFilter(getResources().getColor(R.color.ready_button_pressed), PorterDuff.Mode.SRC);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("lifeSaver", "released: " + v.getClass().getName());
                v.getBackground().setColorFilter(getResources().getColor(R.color.ready_button), PorterDuff.Mode.SRC);
                break;
        }

        return false;
    }

}
