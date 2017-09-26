/**
 * To get system information like MODEL,Android verison, Linux version, Memory, Flash,
 * please refer to APIs that packages/apps/Settings uses.
 **/

package com.indevstudio.stbtest.sysinfo;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.StringTokenizer;


public class GetHswInfo {

    /**
     * Android can get ModelName by import android.os.Build; and read Build.MODEL or SystemProperties.get(ro.product.model) in Java file.
     * Please refer to packages/apps/Settings/src/com/android/settings/DeviceInfoSettings.java
     * If you want to get it from shell, please run getprop ro.product.model or refer to the function below.
     **/
    public static String getModelName() {
        String result = "";

        try {
            FileReader reader = new FileReader("/system/build.prop");
            BufferedReader br = new BufferedReader(reader);

            String str = null;

            while ((str = br.readLine()) != null)
                if (str.contains("ro.product.model"))
                    result = str.substring(17);

            br.close();
            reader.close();

            result = result.replace("\r\n", "").replace("\n", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * It is recommended to get SN by import android.os.SystemProperties; and SystemProperties.get(ro.serialno) in Java file.
     * If you want to program another serial number, please follow commands below:
     * echo usid > /sys/class/unifykeys/name
     * echo YOURSERIALNUMBER > /sys/class/unifykeys/write
     * sync
     * reboot
     **/
    public static String getSnNumber() {
        String result = null;

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            result = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Wi-Fi MAC address is /sys/class/net/wlan0/address
     * Plesae enable Wi-Fi before get Wi-Fi MAC address
     **/
    public static String getWifiMac() {
        String result = "";

        try {
            FileReader reader = new FileReader("/sys/class/net/wlan0/address");
            BufferedReader br = new BufferedReader(reader);

            result = br.readLine().replace("\r\n", "").replace("\n", "");

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Ethernet is /sys/class/net/eth0/address
     * If you want to program another Ethernet MAC address, please follow commands below:
     * echo mac > /sys/class/unifykeys/name
     * echo YO:UR:MA:CA:DD:RS > /sys/class/unifykeys/write
     * sync
     * reboot
     **/
    public static String getEthMac() {
        String result = "";

        try {
            FileReader reader = new FileReader("/sys/class/net/eth0/address");
            BufferedReader br = new BufferedReader(reader);

            result = br.readLine().replace("\r\n", "").replace("\n", "");

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Temperature is /sys/class/thermal/thermal_zone0/temp
     **/
    public static float getCpuTemp() {
        float result = 0;

        try {
            FileReader reader = new FileReader("/sys/class/thermal/thermal_zone0/temp");
            BufferedReader br = new BufferedReader(reader);

            String str = br.readLine().replace("\r\n", "").replace("\n", "");
            result = Float.parseFloat(str);

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result / 1000;
    }

    /**
     * You can also refer to APIs that Settings -> Storage uses
     **/
    @SuppressLint("NewApi")
    public static Long getFlashTotalSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();

        return blockSize * totalBlocks;
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

        return blockSize * availableBlocks;
    }

    /**
     * You can also refer to APIs that Settings -> Memory uses
     **/
    public static Long getRamTotalSize() {
        long result = 0;

        try {
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

            result = total * 1024;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * You can also refer to APIs that Settings -> Memory uses
     **/
    public static Long getRamAvailableSize() {
        long result = 0;

        try {
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

            result = ava * 1024;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getCpuName() {
        String result = "";

        try {
            FileReader reader = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(reader);

            String str = null;

            while ((str = br.readLine()) != null)
                if (str.contains("Processor")) {
                    StringTokenizer stk = new StringTokenizer(str, ":");
                    stk.nextToken();
                    result = stk.nextToken().trim();
                }


            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getCpuModelAndSoc() {
        String result = "";

        try {
            FileReader reader = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(reader);

            String str = null;

            while ((str = br.readLine()) != null)
                if (str.contains("Hardware")) {
                    StringTokenizer stk = new StringTokenizer(str, ":");
                    stk.nextToken();
                    result = stk.nextToken().trim();
                }

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getCpuSerial() {
        String result = "";

        try {
            FileReader reader = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(reader);

            String str = null;

            while ((str = br.readLine()) != null)
                if (str.contains("Serial")) {
                    StringTokenizer stk = new StringTokenizer(str, ":");
                    stk.nextToken();
                    result = stk.nextToken().trim();
                }


            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * You can also refer to APIs that Settings -> About media box uses
     **/
    public static String getKernelVersion() {
        String result = "";

        try {
            FileReader reader = new FileReader("/proc/version");
            BufferedReader br = new BufferedReader(reader);

            String str = br.readLine();
            StringTokenizer stk = new StringTokenizer(str);

            String tmp1, tmp2;
            tmp1 = stk.nextToken();
            tmp2 = stk.nextToken();

            while (stk.hasMoreTokens()) {
                if (tmp1.equals("Linux") && tmp2.equals("version")) {
                    result = stk.nextToken();
                    break;
                } else {
                    tmp1 = tmp2;
                    tmp2 = stk.nextToken();
                }
            }

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * You can also refer to APIs that Settings -> About media box uses
     **/
    public static String getAndroidVersion() {
        String result = "";

        try {
            FileReader reader = new FileReader("/system/build.prop");
            BufferedReader br = new BufferedReader(reader);

            String str = null;

            while ((str = br.readLine()) != null)
                if (str.contains("ro.build.version.release"))
                    result = str.substring(25);

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.replace("\r\n", "").replace("\n", "");
    }


    public static double getCpuUsage() {
        double result = 0;

        try {
            FileReader reader = new FileReader("/proc/stat");
            BufferedReader br = new BufferedReader(reader);

            long all1 = 0;
            long idle1 = 0;
            long all2 = 0;
            long idle2 = 0;

            String str = br.readLine();
            StringTokenizer stk = new StringTokenizer(str);
            stk.nextToken();

            for (int i = 0; i < 7; i++) {
                if (i == 3) {
                    idle1 = Long.parseLong(stk.nextToken());
                    all1 += idle1;

                    continue;
                }

                all1 += Long.parseLong(stk.nextToken());
            }

            br.close();
            reader.close();

            Thread.sleep(100);

            reader = new FileReader("/proc/stat");
            br = new BufferedReader(reader);

            str = br.readLine();
            stk = new StringTokenizer(str);
            stk.nextToken();

            for (int i = 0; i < 7; i++) {
                if (i == 3) {
                    idle2 = Long.parseLong(stk.nextToken());
                    all2 += idle2;

                    continue;
                }

                all2 += Long.parseLong(stk.nextToken());
            }

            br.close();
            reader.close();

            result = 100.0 * (all2 - all1 - (idle2 - idle1)) / (all2 - all1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Long getRamTotleSize() {
        long result = 0;

        try {
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

            result = total * 1024;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String formatSize(long size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = " КБ";
            size /= 1024;
            if (size >= 1024) {
                suffix = " МБ";
                size /= 1024;
                if (size >= 1024) {
                    suffix = " ГБ";
                    size /= 1024;
                }
            }
        }

        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }

        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }


    public static String getUpTime() {
        String result = "";

        try {
            String[] command = {"uptime"};
            Process process = null;
            process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                Log.d("GetHswInfo", "uptime output: " + s);
                result += s + " ";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getHdcpMode() {
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

    public static String getHdcpVer() {
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

    public static String getEthernetLinkSpeed() {
        String result = "";

        try {
            FileReader reader = new FileReader("/sys/class/ethernet/linkspeed");
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

    public static String getDisplayMode() {
        String result = "";

        try {
            FileReader reader = new FileReader("/sys/class/amhdmitx/amhdmitx0/disp_cap");
            BufferedReader br = new BufferedReader(reader);

            String s = null;

            while ((s = br.readLine()) != null) {
                Log.d("GetHswInfo", "display mode: " + s);
                result += s + " ";
            }

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getHdmiAuthenticated() {
        String result = "";

        try {
            FileReader reader = new FileReader("/sys/module/hdmitx20/parameters/hdmi_authenticated");
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

}
