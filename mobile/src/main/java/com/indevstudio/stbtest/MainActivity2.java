package com.indevstudio.stbtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.indevstudio.stbtest.exoplayer.SampleChooserActivity;
import com.indevstudio.stbtest.network.NetSpeedSettings;

public class MainActivity2 extends AppCompatActivity {


    TextView text1 = null;
    TextView text2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.requestOtherPermissions(this);


//        LinearLayout layout = new LinearLayout(this);
//
//        super.setContentView(layout);
//        layout.setOrientation(LinearLayout.VERTICAL);
//
//        text1 = new TextView(this);
//        text2 = new TextView(this);
//
//        layout.addView(text1);
//        layout.addView(text2);
//
//        new Timer().schedule(new ShowInfoDynamic(text1, 1), 0, 2000);
//        new Timer().schedule(new ShowInfoDynamic(text2, 2), 0, 2000);
//
//        Button btn1 = new Button(this);
//        Button btn2 = new Button(this);
//        Button btn3 = new Button(this);
//        Button btn4 = new Button(this);
//        btn4.setText("Network test");
//        Button btn5 = new Button(this);
//        btn5.setText("Player test");
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    new ChangeLedLight().setLedOn();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    new ChangeLedLight().setLedOff();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    new ChangeLedLight().setLedFlash();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onNetworkTest(v);
//            }
//        });
//
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onPlayerTest(v);
//            }
//        });
//
//        layout.addView(btn1);
//        layout.addView(btn2);
//        layout.addView(btn3);
//        layout.addView(btn4);
//        layout.addView(btn5);
    }

    public void onNetworkTest(View v) {
        Intent intent = new Intent(this, NetSpeedSettings.class);
        startActivity(intent);
    }

    public void onPlayerTest(View v) {
        Intent intent = new Intent(this, SampleChooserActivity.class);
        startActivity(intent);
    }

}
