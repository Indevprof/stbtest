package com.indevstudio.stbtest.sysinfo;

import android.os.Build;

import com.indevstudio.stbtest.ListviewItem;

import java.util.HashMap;

public class GetDisplayInfo extends GetSysFileInfo {

    public GetDisplayInfo() {
        super(null);

        items.put("Режимы", "header");
        String s = GetHswInfo.getDisplayMode();
        for(String modeStr: s.split(" "))
            items.put(modeStr, "");

        items.put("HDMI", "header");
        items.put("Hdmi authenticated", GetHswInfo.getHdmiAuthenticated());
    }

    public HashMap<String, String> getItems() {
        return super.getAllItems();
    }

}
