package com.ghris.chun.lifesaver;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chris on 4/1/2016.
 */
public class ReadyMainFragment extends Fragment implements View.OnTouchListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ready_main_fragment, container, false);
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
