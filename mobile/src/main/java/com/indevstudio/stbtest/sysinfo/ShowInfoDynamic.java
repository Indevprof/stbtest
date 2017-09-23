package com.indevstudio.stbtest.sysinfo;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
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
                try {
                    view.setText(Double.toString(100.0 - 100.0 * getRamAvailableSize() / getRamTotleSize()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            else if (msg.what == 0x02) {
                try {
                    view.setText(Double.toString(getCpuUsage()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private double getCpuUsage() throws IOException, InterruptedException {
        FileReader reader = new FileReader("/proc/stat");
        BufferedReader br = new BufferedReader(reader);

        long all1 = 0;
        long idle1 = 0;
        long all2 = 0;
        long idle2 = 0;

        String str = br.readLine();
        StringTokenizer stk = new StringTokenizer(str);
        stk.nextToken();

        for (int i = 0; i < 7; i++) {
            if (i == 3) {
                idle1 = Long.parseLong(stk.nextToken());
                all1 += idle1;

                continue;
            }

            all1 += Long.parseLong(stk.nextToken());
        }

        br.close();
        reader.close();

        Thread.sleep(100);

        reader = new FileReader("/proc/stat");
        br = new BufferedReader(reader);

        str = br.readLine();
        stk = new StringTokenizer(str);
        stk.nextToken();

        for (int i = 0; i < 7; i++) {
            if (i == 3) {
                idle2 = Long.parseLong(stk.nextToken());
                all2 += idle2;

                continue;
            }

            all2 += Long.parseLong(stk.nextToken());
        }

        br.close();
        reader.close();

        return 100.0 * (all2 - all1 - (idle2 - idle1)) / (all2 - all1);
    }


    private Long getRamTotleSize() throws IOException {
        FileReader reader = new FileReader("/proc/meminfo");
        BufferedReader br = new BufferedReader(reader);

        long total = 0;
        String str = null;

        while ((str = br.readLine()) != null)
            if (str.contains("MemTotal:")) {
                StringTokenizer stk = new StringTokenizer(str);
                stk.nextToken();
                total = Long.parseLong(stk.nextToken());
            }


        br.close();
        reader.close();

        return total * 1024;
    }


    private Long getRamAvailableSize() throws IOException {
        FileReader reader = new FileReader("/proc/meminfo");
        BufferedReader br = new BufferedReader(reader);

        long ava = 0;
        String str = null;

        while ((str = br.readLine()) != null)
            if (str.contains("MemAvailable:")) {
                StringTokenizer stk = new StringTokenizer(str);
                stk.nextToken();
                ava = Long.parseLong(stk.nextToken());
            }


        br.close();
        reader.close();

        return ava * 1024;
    }

}
