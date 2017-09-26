package com.indevstudio.stbtest.sysinfo;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetMemInfo extends GetSysFileInfo {
    public GetMemInfo() {
        super("/proc/meminfo");

        items.put("FlashTotalSize", String.format("%d kB", getFlashTotalSize()));
        items.put("FlashAvailableSize", String.format("%d kB", getFlashAvailableSize()));
    }

    public static Long getFlashTotalSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();

        return blockSize * totalBlocks / 1024;
    }

    /**
     * You can also refer to APIs that Settings -> Storage uses
     **/
    @SuppressLint("NewApi")
    public static Long getFlashAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();

        return blockSize * availableBlocks / 1024;
    }

    public HashMap<String, String> getItems() {
        HashMap<String, String> names = new LinkedHashMap<>();
        names.put("MemTotal", "RAM (всего)");
        names.put("MemFree", "RAM (свободно)");
        names.put("FlashTotalSize", "Внутренняя память (всего)");
        names.put("FlashAvailableSize", "Внутренняя память (свободно)");

        return getNamedItemsAndItems("Главное", "Прочее", names);
    }

}
