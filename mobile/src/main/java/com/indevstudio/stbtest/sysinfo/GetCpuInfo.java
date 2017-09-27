package com.indevstudio.stbtest.sysinfo;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class GetCpuInfo extends GetSysFileInfo {

    public GetCpuInfo() {
        super("/proc/cpuinfo");
    }

    public HashMap<String, String> getItems() {
        HashMap<String, String> names = new LinkedHashMap<>();
        names.put("Processor", "Процессор");
        names.put("CPU revision", "Аппаратная ревизия");
        names.put("Hardware", "Модель процессора");
        names.put("Serial", "Серийный номер");

        names.put("processor", "");

        return super.getNamedItemsAndItems("Главное", "Прочее", names);
    }

}
