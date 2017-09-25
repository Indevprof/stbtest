package com.indevstudio.stbtest.sysinfo;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.indevstudio.stbtest.R;
import com.indevstudio.stbtest.UiHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SysInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_info);

        ArrayList<SysInfoItem> items = new ArrayList<>();

        GetNetInfo netInfo = new GetNetInfo();

        try {
            items.add(new SysInfoItem("Тест", "header"));
            items.add(new SysInfoItem("Uptime", GetHswInfo.getUpTime()));
            items.add(new SysInfoItem("Ethernet link speed", GetHswInfo.getEthernetLinkSpeed()));
            items.add(new SysInfoItem("Hdcp mode", GetHswInfo.getHdcpMode()));
            items.add(new SysInfoItem("Hdcp version", GetHswInfo.getHdcpVer()));
            items.add(new SysInfoItem("RX", netInfo.RX));
            items.add(new SysInfoItem("Display mode", GetHswInfo.getDisplayMode()));
            items.add(new SysInfoItem("Hdmi authenticated", GetHswInfo.getHdmiAuthenticated()));

            items.add(new SysInfoItem("Основные", "header"));
            items.add(new SysInfoItem("Название модели", GetHswInfo.getModelName().toUpperCase()));
            items.add(new SysInfoItem("Аппаратная ревизия", ""));
            items.add(new SysInfoItem("Серийный номер устройства", GetHswInfo.getSnNumber().toUpperCase()));
            items.add(new SysInfoItem("MAC адреса интерфейсов", String.format("Wi-Fi %s, Ethernet %s", GetHswInfo.getWifiMac(), GetHswInfo.getEthMac()).toUpperCase()));
            items.add(new SysInfoItem("HDCP ключ", ""));

            items.add(new SysInfoItem("Аппаратные компоненты платы", "header"));
            items.add(new SysInfoItem("Процессор", GetHswInfo.getCpuName()));
            items.add(new SysInfoItem("Модель процессора", GetHswInfo.getCpuModelAndSoc()));
            items.add(new SysInfoItem("SN процессора", GetHswInfo.getCpuSerial()));
            items.add(new SysInfoItem("Flash", String.format("Total - %s, Available - %s", GetHswInfo.formatSize(GetHswInfo.getFlashTotalSize()), GetHswInfo.formatSize(GetHswInfo.getFlashAvailableSize()))));
            items.add(new SysInfoItem("RAM", String.format("Total - %s, Available - %s", GetHswInfo.formatSize(GetHswInfo.getRamTotalSize()), GetHswInfo.formatSize(GetHswInfo.getRamAvailableSize()))));
            items.add(new SysInfoItem("Ethernet", GetHswInfo.getEthMac().toUpperCase()));
            items.add(new SysInfoItem("Wi-Fi", GetHswInfo.getWifiMac().toUpperCase()));
            items.add(new SysInfoItem("Bluetooth", ""));
            items.add(new SysInfoItem("USB", ""));
            items.add(new SysInfoItem("HDMI", ""));

            items.add(new SysInfoItem("Ключевые программные компоненты", "header"));
            items.add(new SysInfoItem("bootloader", ""));
            items.add(new SysInfoItem("kernel", GetHswInfo.getKernelVersion().toUpperCase()));
            items.add(new SysInfoItem("Android", GetHswInfo.getAndroidVersion().toUpperCase()));
            items.add(new SysInfoItem("Verimatrix clients (IPTV and OTT)", ""));
            items.add(new SysInfoItem("Widevine library", ""));
            items.add(new SysInfoItem("reference software", ""));


        } catch (IOException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new SysInfoItemsAdapter(this, items));
    }


    private class SysInfoItem {
        String name;
        String value;

        public SysInfoItem(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String toString() {
            if (value == "header")
                return name; // name.toUpperCase();
            return String.format("%s : %s", name, value);
        }
    }


    private class SysInfoItemsAdapter extends ArrayAdapter<SysInfoItem> {

        public SysInfoItemsAdapter(Context context, List<SysInfoItem> items) {
            super(context, android.R.layout.simple_list_item_1, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            SysInfoItem item = getItem(position);
            if (view != null) {
                view.setText(item.toString());
                UiHelper.setTextViewStyle(getContext(), (TextView) view);

                if (item.value == "header") {
                    view.setBackgroundResource(R.color.colorHeader);
                    view.setFocusable(false);
                    view.setTypeface(view.getTypeface(), Typeface.BOLD);
                }
            }
            return view;
        }
    }

}
