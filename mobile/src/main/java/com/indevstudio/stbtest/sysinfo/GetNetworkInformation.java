package com.indevstudio.stbtest.sysinfo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetNetworkInformation {
    public String text = "";
    public String RX = "";

    public GetNetworkInformation() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void init() throws IOException {
        String[] command = {"ifconfig"};
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String s;
        while ((s = reader.readLine()) != null) {
            Log.d("GetHswInfo", "uptime output: " + s);
            this.text += s + " ";

            if (s.contains("RX")) {
                this.RX = s.substring(3);
            }
        }
    }

}
