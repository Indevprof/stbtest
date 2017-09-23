package com.indevstudio.stbtest.led;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.indevstudio.stbtest.R;

import java.io.IOException;

public class LedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);
    }

    public void onTest1(View v) {
        try {
            new ChangeLedLight().setLedOn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTest2(View v) {
        try {
            new ChangeLedLight().setLedOff();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTest3(View v) {
        try {
            new ChangeLedLight().setLedFlash();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
