package com.indevstudio.stbtest.sysinfo;

import android.os.Build;

import java.util.HashMap;

public class GetSoftInfo extends GetSysFileInfo {

    public GetSoftInfo() {
        super(null);

        items.put("Андроид", "header");
        items.put("Version", Build.VERSION.RELEASE);
        items.put("Sdk", Build.VERSION.SDK);
    }

    public HashMap<String, String> getItems() {
        return super.getAllItems();
    }

}
