package com.indevstudio.stbtest.sysinfo;

import java.util.HashMap;

public class GetVersionInfo extends GetSysFileInfo {

    public GetVersionInfo() {
        super("/proc/version");
    }

    @Override
    public HashMap<String, String> getItems() {
        return getAllItems();
    }
}
