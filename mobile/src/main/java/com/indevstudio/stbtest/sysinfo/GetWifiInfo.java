package com.indevstudio.stbtest.sysinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class GetWifiInfo extends GetSysFileInfo {
    public GetWifiInfo() {
        super(null);

        init();
    }

    void init() {
        try {
            String[] command = {"ifconfig"};
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            boolean startRecord = false;
            while ((s = reader.readLine()) != null) {
                if (!startRecord) {
                    if (s.startsWith("w"))
                        startRecord = true;
                } else {
                    if (s.trim().isEmpty()) {
                        startRecord = false;
                        break;
                    }
                }
                if (startRecord)
                    items.put(s, "");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public HashMap<String, String> getItems() {
        return getAllItems();
    }
}
