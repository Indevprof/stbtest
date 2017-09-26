package com.indevstudio.stbtest.sysinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class GetNetInfo extends GetSysFileInfo {
    Context context;

    public GetNetInfo(Context context) {
        super(null);
        this.context = context;

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    getInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void getInfo() {
//        try {
//            List<NetworkInterface> interfaces = null;
//            interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
//            int i = 1;
//            for (NetworkInterface intf : interfaces) {
//                items.put("" + i + " Name", intf.getName());
//                items.put("" + i + " Hardware address", bytesToString(intf.getHardwareAddress()));
//
//                Enumeration<InetAddress> addrList = intf.getInetAddresses();
//                while (addrList.hasMoreElements()) {
//                    InetAddress addr = addrList.nextElement();
//                    items.put("" + i + " addr.getHostName", addr.getHostName());
//                    items.put("" + i + " addr.getAddress", addr.getHostAddress());
//                }
//            }
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }

        String ssid = "";

        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        int i = 1;
        String prexix;
        for (Network network : connManager.getAllNetworks()) {

            NetworkInfo networkInfo = connManager.getNetworkInfo(network);
            String type = networkInfo.getTypeName();
            prexix = type + " ";

            items.put(type, "header");

            // if (networkInfo.isConnected()) {
            if (type.toLowerCase().equals("wifi")) {
                items.put(prexix + " state", networkInfo.getState().name());

                final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    ssid = connectionInfo.getSSID();

                    DhcpInfo d = wifiManager.getDhcpInfo();

                    if (ssid != null)
                        items.put(prexix + " ssid", ssid.replaceAll("\"", ""));

                    items.put(prexix + " ssid", ssid.replaceAll("\"", ""));

                    items.put(prexix + " dns 1: ", intToIp(d.dns1));
                    items.put(prexix + " dns 2: ", intToIp(d.dns2));
                    items.put(prexix + " default gateway: ", intToIp(d.gateway));
                    items.put(prexix + " ip address: ", intToIp(d.ipAddress));
                    // items.put(prexix + " lease time: ", "" + String.format("%.1f sec", (float)d.leaseDuration/1000));
                    items.put(prexix + " subnet mask: ", intToIp(d.netmask));
                    items.put(prexix + " server ip: ", intToIp(d.serverAddress));
                }
            }

            i++;
        }
    }

    public String intToIp(int ip) {
        return String.format("%d.%d.%d.%d",
                (ip & 0xff),
                (ip >> 8 & 0xff),
                (ip >> 16 & 0xff),
                (ip >> 24 & 0xff));
    }

    String bytesToString(byte[] mac) {
        if (mac == null) return "";
        StringBuilder buf = new StringBuilder();
        for (int idx = 0; idx < mac.length; idx++)
            buf.append(String.format("%02X:", mac[idx]));
        if (buf.length() > 0) buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }

    @Override
    public HashMap<String, String> getItems() {
        return getAllItems();
    }
}
