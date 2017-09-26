package com.indevstudio.stbtest.sysinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class GetEthernetInfo extends GetSysFileInfo {
    public GetEthernetInfo() {
        super(null);

        readIfconfig();
    }

    void readIfconfig() {
        try {
            String[] command = {"ifconfig"};
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            boolean startRecord = false;
            while ((s = reader.readLine()) != null) {
                if (!startRecord) {
                    if (s.startsWith("e"))
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
