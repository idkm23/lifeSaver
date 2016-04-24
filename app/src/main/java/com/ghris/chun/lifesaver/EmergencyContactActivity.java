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

public class EmergencyContactActivity extends FragmentActivity implements View.OnTouchListener {

    EditText phoneNumberField;
    TextView currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_contact_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setupClickables();

        currentContact = (TextView) findViewById(R.id.current_contact);
        currentContact.setText(MainActivity.instance.getPhoneNumber().substring(4));
        phoneNumberField = (EditText)findViewById(R.id.phone_number_field);
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
        Intent k = new Intent(EmergencyContactActivity.this, MainActivity.class);
        startActivity(k);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {

            case R.id.ok_phone_number:
                returnToMainActivity();
            case R.id.apply_phone_number:
                MainActivity.instance.setPhoneNumber(phoneNumberField.getText().toString());
                currentContact.setText(MainActivity.instance.getPhoneNumber().substring(4));
                break;
        }

        if(v.getId() != R.id.phone_number_field) {
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
        findViewById(R.id.emergency_contact_main).setOnTouchListener(this);
        findViewById(R.id.apply_phone_number).setOnTouchListener(this);
        findViewById(R.id.ok_phone_number).setOnTouchListener(this);
    }
}
