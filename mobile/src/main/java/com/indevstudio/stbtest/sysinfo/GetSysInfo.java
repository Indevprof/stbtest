package com.indevstudio.stbtest.sysinfo;

import android.os.Build;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class GetSysInfo extends GetSysFileInfo {

    public GetSysInfo() {
        super(null);

        items.put("Система", "header");
        items.put("Manufacturer", Build.MANUFACTURER);
        items.put("Board", Build.BOARD);
        items.put("Brand", Build.BRAND);
        items.put("Model", Build.MODEL);
        items.put("Product", Build.PRODUCT);
        items.put("Industrial design", Build.DEVICE);
        items.put("Build ID", Build.DISPLAY);
        items.put("Hardware", Build.HARDWARE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            items.put("Serial", Build.getSerial());
        }
        items.put("Bootloader", Build.BOOTLOADER);
        items.put("Build identifier", Build.FINGERPRINT);

        // items.put("Host", Build.HOST);
        // items.put("Id", Build.ID);
        items.put("Build type", Build.TYPE);
        // items.put("User", Build.USER);

        items.put("Андроид", "header");
        items.put("Version", Build.VERSION.RELEASE);
        items.put("Sdk", Build.VERSION.SDK);
        // items.put("Incremental ", Build.VERSION.INCREMENTAL);
        // items.put("Base", "" + Build.VERSION_CODES.BASE);
    }

    public HashMap<String, String> getItems() {
        return super.getAllItems();
    }

}
