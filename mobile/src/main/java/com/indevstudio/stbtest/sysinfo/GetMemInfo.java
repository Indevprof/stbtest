package com.indevstudio.stbtest.sysinfo;

public class GetMemInfo extends GetSysFileInfo {
    public GetMemInfo() {
        super("/proc/meminfo");
    }
}
