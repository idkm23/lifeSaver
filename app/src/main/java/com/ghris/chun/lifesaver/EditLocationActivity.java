package com.ghris.chun.lifesaver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class EditLocationActivity extends FragmentActivity implements View.OnTouchListener {

    EditText locationField;
    TextView currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_location_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setupClickables();

        currentLocation = (TextView) findViewById(R.id.current_location);
        currentLocation.setText(MainActivity.getLocation());
        locationField = (EditText)findViewById(R.id.location_field);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(currentLocation != null) {
            currentLocation.setText(MainActivity.getLocation());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected void returnToMainActivity() {
        Intent k = new Intent(EditLocationActivity.this, MainActivity.class);
        startActivity(k);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {

            case R.id.ok_location:
                MainActivity.setLocation(locationField.getText().toString());
                currentLocation.setText(MainActivity.getLocation());
                break;

            case R.id.cancel_location:
                returnToMainActivity();
                break;

        }

        if(v.getId() != R.id.location_field) {
            hideSoftKeyboard();
        }

        return false;
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(INPUT_METHOD_SERVICE);

        if(getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void setupClickables() {
        findViewById(R.id.edit_location_main).setOnTouchListener(this);
        findViewById(R.id.cancel_location).setOnTouchListener(this);
        findViewById(R.id.ok_location).setOnTouchListener(this);
    }
}
