package com.indevstudio.stbtest.sysinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        HashMap<String, String> resultItems = new LinkedHashMap<>();

        ArrayList<String> itemsToRemove = new ArrayList<>();

        for (Map.Entry<String, String> e : items.entrySet()) {
            String s = e.getKey();

            if (s == null) continue;

            if (s.toLowerCase().contains("link encap")) {
                s = s.trim();
                String title = s.substring(0, s.indexOf(" "));
                if (title != null && !title.isEmpty()) {
                    resultItems.put(title, "header");
                    resultItems.put("Hardware", s.replace(title, "").trim());
                    itemsToRemove.add(e.getKey());
                }
            }

            if (s.toLowerCase().contains("cast")) {
                s = s.trim();
                resultItems.put("Mode", s.trim());
                itemsToRemove.add(e.getKey());
            }

            if (s.toLowerCase().contains("inet6")) {
                s = s.trim();
                resultItems.put("Inet6 address", s.toLowerCase().replaceAll("inet6 addr:", "").trim());
                itemsToRemove.add(e.getKey());
            }

            if (s.toLowerCase().contains("rx packets")) {
                s = s.trim();
                resultItems.put("RX packets", s.trim());
                itemsToRemove.add(e.getKey());
            }

            if (s.toLowerCase().contains("tx packets")) {
                s = s.trim();
                resultItems.put("TX packets", s.trim());
                itemsToRemove.add(e.getKey());
            }

            if (s.toLowerCase().contains("collisions")) {
                s = s.trim();
                resultItems.put("Collisions", s.trim());
                itemsToRemove.add(e.getKey());
            }

            if (s.toLowerCase().contains("rx bytes")) {
                s = s.trim();
                resultItems.put("RX\\TX bytes", s.trim());
                itemsToRemove.add(e.getKey());
            }

            if (s.toLowerCase().contains("interrupt")) {
                s = s.trim();
                resultItems.put("Interrupt", s.toLowerCase().replaceAll("interrupt", "").replaceAll(":", "").trim());
                itemsToRemove.add(e.getKey());
            }
        }

        resultItems.putAll(items);

        for (String s : itemsToRemove)
            resultItems.remove(s);

        return resultItems;
    }
}
