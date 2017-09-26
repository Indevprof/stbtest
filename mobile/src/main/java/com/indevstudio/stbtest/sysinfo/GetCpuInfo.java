package com.indevstudio.stbtest.sysinfo;

public class GetCpuInfo extends GetSysFileInfo {

    public GetCpuInfo() {
        super("/proc/cpuinfo");
    }
}
