/**
 * To get system information like MODEL,Android verison, Linux version, Memory, Flash,
 * please refer to APIs that packages/apps/Settings uses.
 **/

package com.indevstudio.stbtest.sysinfo;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;


public class GetHswInfo {

    /**Android can get ModelName by import android.os.Build; and read Build.MODEL or SystemProperties.get(ro.product.model) in Java file.
     * Please refer to packages/apps/Settings/src/com/android/settings/DeviceInfoSettings.java
     * If you want to get it from shell, please run getprop ro.product.model or refer to the function below.**/
    public String getModelName() throws IOException {
        FileReader reader = new FileReader("/system/build.prop");
        BufferedReader br = new BufferedReader(reader);

        String str = null;
        String value = null;

        while ((str = br.readLine()) != null)
            if (str.contains("ro.product.model"))
                value = str.substring(17);

        br.close();
        reader.close();

        return value.replace("\r\n", "").replace("\n", "");
    }

    /**It is recommended to get SN by import android.os.SystemProperties; and SystemProperties.get(ro.serialno) in Java file.
     * If you want to program another serial number, please follow commands below:
     * echo usid > /sys/class/unifykeys/name
     * echo YOURSERIALNUMBER > /sys/class/unifykeys/write
     * sync
     * reboot
     **/
    public String getSnNumber() {
        String value = null;

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            value = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    /**Wi-Fi MAC address is /sys/class/net/wlan0/address
     * Plesae enable Wi-Fi before get Wi-Fi MAC address**/
    public String getWifiMac() throws IOException {
        FileReader reader = new FileReader("/sys/class/net/wlan0/address");
        BufferedReader br = new BufferedReader(reader);

        String value = br.readLine().replace("\r\n", "").replace("\n", "");

        br.close();
        reader.close();

        return value;
    }

    /**Ethernet is /sys/class/net/eth0/address
     * If you want to program another Ethernet MAC address, please follow commands below:
     * echo mac > /sys/class/unifykeys/name
     * echo YO:UR:MA:CA:DD:RS > /sys/class/unifykeys/write
     * sync
     * reboot**/
    public String getEthMac() throws IOException {
        FileReader reader = new FileReader("/sys/class/net/eth0/address");
        BufferedReader br = new BufferedReader(reader);

        String value = br.readLine().replace("\r\n", "").replace("\n", "");

        br.close();
        reader.close();

        return value;
    }

    /** Temperature is /sys/class/thermal/thermal_zone0/temp**/
    public float getCPUTemp() throws IOException {
        FileReader reader = new FileReader("/sys/class/thermal/thermal_zone0/temp");
        BufferedReader br = new BufferedReader(reader);

        String str = br.readLine().replace("\r\n", "").replace("\n", "");
        float value = Float.parseFloat(str);

        br.close();
        reader.close();

        return value / 1000;
    }

    /**You can also refer to APIs that Settings -> Storage uses**/
    @SuppressLint("NewApi")
    public Long getFlashTotalSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();

        return blockSize * totalBlocks;
    }

    /**You can also refer to APIs that Settings -> Storage uses**/
    @SuppressLint("NewApi")
    public Long getFlashAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();

        return blockSize * availableBlocks;
    }

    /**You can also refer to APIs that Settings -> Memory uses**/
    public Long getRamTotalSize() throws IOException {
        FileReader reader = new FileReader("/proc/meminfo");
        BufferedReader br = new BufferedReader(reader);

        long total = 0;
        String str = null;

        while ((str = br.readLine()) != null)
            if (str.contains("MemTotal:")) {
                StringTokenizer stk = new StringTokenizer(str);
                stk.nextToken();
                total = Long.parseLong(stk.nextToken());
            }


        br.close();
        reader.close();

        return total * 1024;
    }

    /**You can also refer to APIs that Settings -> Memory uses**/
    public Long getRamAvailableSize() throws IOException {
        FileReader reader = new FileReader("/proc/meminfo");
        BufferedReader br = new BufferedReader(reader);

        long ava = 0;
        String str = null;

        while ((str = br.readLine()) != null)
            if (str.contains("MemAvailable:")) {
                StringTokenizer stk = new StringTokenizer(str);
                stk.nextToken();
                ava = Long.parseLong(stk.nextToken());
            }


        br.close();
        reader.close();

        return ava * 1024;
    }

    /**You can also refer to APIs that Settings -> About media box uses**/
    public String getKernelVersion() throws IOException {
        FileReader reader = new FileReader("/proc/version");
        BufferedReader br = new BufferedReader(reader);

        String str = br.readLine();
        StringTokenizer stk = new StringTokenizer(str);

        String value = null, tmp1, tmp2;
        tmp1 = stk.nextToken();
        tmp2 = stk.nextToken();

        while (stk.hasMoreTokens()) {
            if (tmp1.equals("Linux") && tmp2.equals("version")) {
                value = stk.nextToken();
                break;
            } else {
                tmp1 = tmp2;
                tmp2 = stk.nextToken();
            }
        }

        br.close();
        reader.close();

        return value;
    }

    /**You can also refer to APIs that Settings -> About media box uses**/
    public String getAndroidVersion() throws IOException {
        FileReader reader = new FileReader("/system/build.prop");
        BufferedReader br = new BufferedReader(reader);

        String str = null;
        String value = null;

        while ((str = br.readLine()) != null)
            if (str.contains("ro.build.version.release"))
                value = str.substring(25);

        br.close();
        reader.close();

        return value.replace("\r\n", "").replace("\n", "");
    }

}
