package com.ghris.chun.lifesaver;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chris on 4/1/2016.
 */
public class MenuFragment extends Fragment implements View.OnTouchListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.menu_fragment, container, false);
        rootView.findViewById(R.id.emergencyContact).setOnTouchListener(this);

        return rootView;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch(v.getId()) {
            case R.id.emergencyContact:

                MainActivity.instance.initiateEmergencyContact();
                break;
        }

        return false;
    }
}
