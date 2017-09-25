package com.indevstudio.stbtest.led;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LedFlashThread extends Thread {
    public boolean stop = false;

    public static final int TYPE_G = 0;
    public static final int TYPE_R = 1;
    public static final int TYPE_RG = 2;

    int type;
    String lastLed = "";

    public LedFlashThread(String name, int type) {
        super(name);
        this.type = type;
    }

    public void run() {
        while (!stop) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            FileWriter writer = null;
            try {
                writer = new FileWriter("/proc/ledlight/powerled/state");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            BufferedWriter bw = new BufferedWriter(writer);

            try {
                String led = "";
                switch (this.type) {
                    case TYPE_G:
                        led = "on";
                        break;
                    case TYPE_R:
                        led = "off";
                        break;
                    case TYPE_RG:
                        if (lastLed.equals("on"))
                            led = "off";
                        else
                            led = "on";
                        break;
                }
                lastLed = led;

                // Log.d("LedFlashThread", led);

                bw.write(led);
                bw.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                writer = new FileWriter("/proc/ledlight/powerled/state");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bw = new BufferedWriter(writer);

            try {
                bw.write("all_off");
                bw.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
