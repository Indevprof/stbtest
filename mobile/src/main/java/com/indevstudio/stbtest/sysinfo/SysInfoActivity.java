package com.indevstudio.stbtest.sysinfo;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.indevstudio.stbtest.ListviewItem;
import com.indevstudio.stbtest.MenuItemsAdapter;
import com.indevstudio.stbtest.R;
import com.indevstudio.stbtest.UiHelper;

import java.util.ArrayList;
import java.util.List;

public class SysInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_info);

        ArrayList<ListviewItem> items = new ArrayList<>();

        items.add(new ListviewItem("Тест", "test"));
        items.add(new ListviewItem("Система", "system"));
        items.add(new ListviewItem("Процессор", "cpu"));
        items.add(new ListviewItem("Память", "mem"));
        items.add(new ListviewItem("Ethernet", "ethernet"));
        items.add(new ListviewItem("Wi-fi", "wifi"));
        items.add(new ListviewItem("Software", "software"));

        final ListView listView = (ListView) findViewById(R.id.headerListView);
        listView.setAdapter(new MenuItemsAdapter(this, items));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onAction((ListviewItem) listView.getItemAtPosition(i));
                view.setSelected(true);
            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onAction((ListviewItem) listView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                clearListView();
            }
        });

/*
        GetNetInfo netInfo = new GetNetInfo();

        ArrayList<ListviewItem> items = new ArrayList<>();

        items.add(new ListviewItem("Тест", "header"));
        items.add(new ListviewItem("Uptime", GetHswInfo.getUpTime()));
        items.add(new ListviewItem("Ethernet link speed", GetHswInfo.getEthernetLinkSpeed()));
        items.add(new ListviewItem("Hdcp mode", GetHswInfo.getHdcpMode()));
        items.add(new ListviewItem("Hdcp version", GetHswInfo.getHdcpVer()));
        items.add(new ListviewItem("RX", netInfo.RX));
        items.add(new ListviewItem("Display mode", GetHswInfo.getDisplayMode()));
        items.add(new ListviewItem("Hdmi authenticated", GetHswInfo.getHdmiAuthenticated()));

        items.add(new ListviewItem("Основные", "header"));
        items.add(new ListviewItem("Название модели", GetHswInfo.getModelName().toUpperCase()));
        items.add(new ListviewItem("Аппаратная ревизия", ""));
        items.add(new ListviewItem("Серийный номер устройства", GetHswInfo.getSnNumber().toUpperCase()));
        items.add(new ListviewItem("MAC адреса интерфейсов", String.format("Wi-Fi %s, Ethernet %s", GetHswInfo.getWifiMac(), GetHswInfo.getEthMac()).toUpperCase()));
        items.add(new ListviewItem("HDCP ключ", ""));

        items.add(new ListviewItem("Аппаратные компоненты платы", "header"));
        items.add(new ListviewItem("Процессор", GetHswInfo.getCpuName()));
        items.add(new ListviewItem("Модель процессора", GetHswInfo.getCpuModelAndSoc()));
        items.add(new ListviewItem("SN процессора", GetHswInfo.getCpuSerial()));
        items.add(new ListviewItem("Flash", String.format("Total - %s, Available - %s", GetHswInfo.formatSize(GetHswInfo.getFlashTotalSize()), GetHswInfo.formatSize(GetHswInfo.getFlashAvailableSize()))));
        items.add(new ListviewItem("RAM", String.format("Total - %s, Available - %s", GetHswInfo.formatSize(GetHswInfo.getRamTotalSize()), GetHswInfo.formatSize(GetHswInfo.getRamAvailableSize()))));
        items.add(new ListviewItem("Ethernet", GetHswInfo.getEthMac().toUpperCase()));
        items.add(new ListviewItem("Wi-Fi", GetHswInfo.getWifiMac().toUpperCase()));
        items.add(new ListviewItem("Bluetooth", ""));
        items.add(new ListviewItem("USB", ""));
        items.add(new ListviewItem("HDMI", ""));

        items.add(new ListviewItem("Ключевые программные компоненты", "header"));
        items.add(new ListviewItem("bootloader", ""));
        items.add(new ListviewItem("kernel", GetHswInfo.getKernelVersion().toUpperCase()));
        items.add(new ListviewItem("Android", GetHswInfo.getAndroidVersion().toUpperCase()));
        items.add(new ListviewItem("Verimatrix clients (IPTV and OTT)", ""));
        items.add(new ListviewItem("Widevine library", ""));
        items.add(new ListviewItem("reference software", ""));

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new SysInfoItemsAdapter(this, items));
*/
    }

    void onAction(ListviewItem item) {
        if (item.getValue().equals("test")) {
            testInfo();
        } else if (item.getValue().equals("system")) {
            systemInfo();
        } else if (item.getValue().equals("cpu")) {
            clearListView();
        } else if (item.getValue().equals("mem")) {
            clearListView();
        } else if (item.getValue().equals("ethernet")) {
            clearListView();
        } else if (item.getValue().equals("wifi")) {
            clearListView();
        } else if (item.getValue().equals("software")) {
            clearListView();
        }
    }

    void clearListView() {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(null);
    }

    void showInfo(ArrayList<ListviewItem> items) {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new SysInfoItemsAdapter(this, items));
    }

    void testInfo() {
        GetNetInfo netInfo = new GetNetInfo();

        ArrayList<ListviewItem> items = new ArrayList<>();

        items.add(new ListviewItem("Uptime", GetHswInfo.getUpTime()));
        items.add(new ListviewItem("Ethernet link speed", GetHswInfo.getEthernetLinkSpeed()));
        items.add(new ListviewItem("Hdcp mode", GetHswInfo.getHdcpMode()));
        items.add(new ListviewItem("Hdcp version", GetHswInfo.getHdcpVer()));
        items.add(new ListviewItem("RX", netInfo.RX));
        items.add(new ListviewItem("Display mode", GetHswInfo.getDisplayMode()));
        items.add(new ListviewItem("Hdmi authenticated", GetHswInfo.getHdmiAuthenticated()));

        showInfo(items);
    }

    void systemInfo() {
        GetNetInfo netInfo = new GetNetInfo();

        ArrayList<ListviewItem> items = new ArrayList<>();

        items.add(new ListviewItem("Название модели", GetHswInfo.getModelName().toUpperCase()));
        items.add(new ListviewItem("Аппаратная ревизия", ""));
        items.add(new ListviewItem("Серийный номер устройства", GetHswInfo.getSnNumber().toUpperCase()));
        items.add(new ListviewItem("MAC адреса интерфейсов", String.format("Wi-Fi %s, Ethernet %s", GetHswInfo.getWifiMac(), GetHswInfo.getEthMac()).toUpperCase()));
        items.add(new ListviewItem("HDCP ключ", ""));

        showInfo(items);
    }

    private class SysInfoItemsAdapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;

        List<ListviewItem> items;

        public SysInfoItemsAdapter(Context context, List<ListviewItem> items) {
            this.context = context;
            this.items = items;
            this.inflater = (LayoutInflater.from(context));
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = inflater.inflate(R.layout.sys_info_item, null);

            ListviewItem item = (ListviewItem) getItem(position);

            TextView textView_text = (TextView) view.findViewById(R.id.text);
            TextView textView_value = (TextView) view.findViewById(R.id.value);

            if (textView_text != null) {
                textView_text.setText(item.getName());
            }
            if (textView_value != null) {
                textView_value.setText(item.getValue());
            }

            if (item.getValue() == "header") {
                view.setBackgroundResource(R.color.colorHeader);
                view.setFocusable(false);
                view.setClickable(false);
                if (textView_text != null) {
                    textView_text.setTypeface(textView_text.getTypeface(), Typeface.BOLD);
                }
                if (textView_value != null) {
                    textView_value.setText("");
                }
            } else
                UiHelper.setViewStyle(context, view);

            return view;
        }
    }

}
