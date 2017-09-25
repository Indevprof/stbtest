package com.indevstudio.stbtest.led;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.indevstudio.stbtest.R;

import java.io.IOException;

public class LedActivity extends AppCompatActivity {

    ChangeLedLight changeLedLight = new ChangeLedLight();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);
    }

    @Override
    protected void onStop() {
        super.onStop();

        try {
            changeLedLight.setLedG();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onOnG(View v) {
        try {
            changeLedLight.setLedG();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onOnR(View v) {
        try {
            changeLedLight.setLedR();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBlinkG(View v) {
        try {
            changeLedLight.setLedFlashG();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBlinkR(View v) {
        try {
            changeLedLight.setLedFlashR();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBlinkRG(View v) {
        try {
            changeLedLight.setLedFlashRG();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onOff(View v) {
        try {
            changeLedLight.setLedOff();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
