package com.indevstudio.stbtest.led;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChangeLedLight {

    static int flag = 0;
    static LedFlashThread thread = new LedFlashThread("flashLed", LedFlashThread.TYPE_G);

    public void setLedG() throws IOException {
        stopLedFlashThread();

        FileWriter writer = new FileWriter("/proc/ledlight/powerled/state");
        BufferedWriter bw = new BufferedWriter(writer);

        bw.write("on");

        bw.close();
        writer.close();
    }

    public void setLedR() throws IOException {
        stopLedFlashThread();

        FileWriter writer = new FileWriter("/proc/ledlight/powerled/state");
        BufferedWriter bw = new BufferedWriter(writer);

        bw.write("off");

        bw.close();
        writer.close();
    }

    public void setLedOff() throws IOException {
        stopLedFlashThread();

        FileWriter writer = new FileWriter("/proc/ledlight/powerled/state");
        BufferedWriter bw = new BufferedWriter(writer);

        bw.write("all_off");

        bw.close();
        writer.close();
    }

    public void setLedFlashG() throws IOException {
        stopLedFlashThread();

        if (flag == 0) {
            thread = new LedFlashThread("flashLed", LedFlashThread.TYPE_G);
            thread.start();
            flag = 1;
        }
    }

    public void setLedFlashR() throws IOException {
        stopLedFlashThread();

        if (flag == 0) {
            thread = new LedFlashThread("flashLed", LedFlashThread.TYPE_R);
            thread.start();
            flag = 1;
        }
    }

    public void setLedFlashRG() throws IOException {
        stopLedFlashThread();

        if (flag == 0) {
            thread = new LedFlashThread("flashLed", LedFlashThread.TYPE_RG);
            thread.start();
            flag = 1;
        }
    }

    void stopLedFlashThread() {
        if (flag == 1) {
            thread.stop = true;
            flag = 0;

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
