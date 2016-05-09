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
public class MenuFragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.menu_fragment, container, false);

        rootView.findViewById(R.id.change_contact).setOnClickListener(this);
        rootView.findViewById(R.id.set_timer).setOnClickListener(this);
        rootView.findViewById(R.id.needle_exchange).setOnClickListener(this);
        rootView.findViewById(R.id.detox_centers).setOnClickListener(this);
        rootView.findViewById(R.id.edit_location).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.change_contact:
                MainActivity.instance.initiateActivity(EmergencyContactActivity.class);
                break;

            case R.id.set_timer:
                break;

            case R.id.needle_exchange:
                MainActivity.instance.initiateActivity(NeedleActivity.class);
                break;

            case R.id.detox_centers:
                MainActivity.instance.initiateActivity(DetoxActivity.class);
                break;

            case R.id.edit_location:
                MainActivity.instance.initiateActivity(EditLocationActivity.class);
                break;
        }
    }
}
