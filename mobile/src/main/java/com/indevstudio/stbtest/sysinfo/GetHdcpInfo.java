package com.indevstudio.stbtest.sysinfo;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GetHdcpInfo extends GetSysFileInfo {
    public GetHdcpInfo() {
        super(null);

        items.put("HDCP", "header");
        items.put("HDCP mode", getHdcpMode());
        items.put("HDCP version", getHdcpVer());
    }

    String getHdcpMode() {
        String result = "";

        try {
            FileReader reader = new FileReader("/sys/class/amhdmitx/amhdmitx0/hdcp_mode");
            BufferedReader br = new BufferedReader(reader);

            String s = null;

            while ((s = br.readLine()) != null)
                result += s + " ";

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    String getHdcpVer() {
        String result = "";

        try {
            FileReader reader = new FileReader("/sys/class/amhdmitx/amhdmitx0/hdcp_ver");
            BufferedReader br = new BufferedReader(reader);

            String s = null;

            while ((s = br.readLine()) != null)
                result += s + " ";

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public HashMap<String, String> getItems() {
        return getAllItems();
    }

}
