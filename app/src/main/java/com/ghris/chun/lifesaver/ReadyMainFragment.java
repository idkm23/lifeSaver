package com.ghris.chun.lifesaver;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
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
    private static final String FORMAT = "%d:%02d";
    private CountDownTimer timer;
    private boolean ticking = false;
    private int seconds , minutes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ready_main_fragment, container, false);
        counter_textview = (TextView) rootView.findViewById(R.id.countdown_clock);
        ready = (Button) rootView.findViewById(R.id.ready_button);
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
                if(ticking)
                    v.getBackground().setColorFilter(getResources().getColor(R.color.ready_button_cancel_pressed), PorterDuff.Mode.SRC);
                else
                    v.getBackground().setColorFilter(getResources().getColor(R.color.ready_button_ready_pressed), PorterDuff.Mode.SRC);

                break;
            case MotionEvent.ACTION_UP:
                if(ticking)
                    v.getBackground().setColorFilter(getResources().getColor(R.color.ready_button_ready), PorterDuff.Mode.SRC);
                else
                    v.getBackground().setColorFilter(getResources().getColor(R.color.ready_button_cancel), PorterDuff.Mode.SRC);

                toggleTimer();
                break;
        }

        return false;
    }

    public void toggleTimer() {
        if(timer == null) {
            timer = new CountDownTimer(180000, 100) { // adjust the milli seconds here
                public void onTick(long millisUntilFinished) {
                    counter_textview.setText(String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    counter_textview.setText("-:--");
                }
            };
        }

        if(ticking) {
            ready.setText(getString(R.string.ready_button_ready));
            ticking = false;
            timer.cancel();
            counter_textview.setText("3:00");
        } else {
            ready.setText(getString(R.string.ready_button_cancel));
            ticking = true;
            timer.start();
        }
    }

}
