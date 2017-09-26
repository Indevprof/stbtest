package com.indevstudio.stbtest.sysinfo;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.TimerTask;


public class ShowInfoDynamic extends TimerTask {

    private TextView view;
    private int mode;


    public ShowInfoDynamic(TextView view, int mode) {
        this.view = view;
        this.mode = mode;
    }


    @Override
    public void run() {
        if (mode == 1)
            handler.sendEmptyMessage(0x01);
        else if (mode == 2)
            handler.sendEmptyMessage(0x02);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x01)
                view.setText(Double.toString(100.0 - 100.0 * GetHswInfo.getRamAvailableSize() / GetHswInfo.getRamTotleSize()));
            else if (msg.what == 0x02) {
                view.setText(Double.toString(GetHswInfo.getCpuUsage()));
            }
        }
    };
}
