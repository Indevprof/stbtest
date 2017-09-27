package com.indevstudio.stbtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.indevstudio.stbtest.exoplayer.PlayerActivity;
import com.indevstudio.stbtest.exoplayer.SampleChooserActivity;
import com.indevstudio.stbtest.led.LedActivity;
import com.indevstudio.stbtest.network.NetSpeedSettings;
import com.indevstudio.stbtest.remote.RemoteControlActivity;
import com.indevstudio.stbtest.sysinfo.SysInfoActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.requestOtherPermissions(this);

        ArrayList<ListviewItem> items = new ArrayList<>();

        items.add(new ListviewItem("1. Детальная программно-аппаратная информация по STB", "test 1"));
        items.add(new ListviewItem("2. Проверка сетевых подключений", "test 2"));
        items.add(new ListviewItem("3. Работа с интерфейсом Wi-Fi", "test 3"));
        items.add(new ListviewItem("4. Работа с интерфейсом Bluetooth", "test 4"));
        items.add(new ListviewItem("5.1. Воспроизведение контента (JSON-плейлист)", "test 5.1"));
        items.add(new ListviewItem("5.2. Воспроизведение контента (выбор файла)", "test 5.2"));
        items.add(new ListviewItem("5.3. Воспроизведение контента (выбор JSON-плейлиста на диске)", "test 5.3"));
        items.add(new ListviewItem("6. Аппаратное декодирование видео", "test 6"));
        items.add(new ListviewItem("7. Демонстрация работы videomark", "test 7"));
        items.add(new ListviewItem("8. HDMI различные разрешения, пропорции и масштабирование видео", "test 8"));
        items.add(new ListviewItem("9. HDMI CEC", "test 9"));
        items.add(new ListviewItem("10. Работа Пульта", "test 10"));
        items.add(new ListviewItem("11. LED индикатор", "test 11"));

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MenuItemsAdapter(this, items, false));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onAction((ListviewItem) listView.getItemAtPosition(position));
            }
        });
    }

    void onAction(ListviewItem item) {
        if (item.getValue().equals("test 1")) {
            sysInfo();
        }
        else
        if (item.getValue().equals("test 2")) {
            ethernetInfo();
        }
        else
        if (item.getValue().equals("test 3")) {
            wifiInfo();
        }
        else
        if (item.getValue().equals("test 4")) {
            bluetoothInfo();
        }
        else
        if (item.getValue().equals("test 5.1")) {
            exoPlayer();
        }
        else
        if (item.getValue().equals("test 5.2")) {
            playMediaFromDisk();
        }
        else
        if (item.getValue().equals("test 5.3")) {
            playlistFromDisk();
        }
        else
        if (item.getValue().equals("test 6")) {

        }
        else
        if (item.getValue().equals("test 7")) {

        }
        else
        if (item.getValue().equals("test 8")) {

        }
        else
        if (item.getValue().equals("test 9")) {

        }
        else
        if (item.getValue().equals("test 10")) {
            remoteControlTest();
        }
        else
        if (item.getValue().equals("test 11")) {
            ledTest();
        }
    }

    void sysInfo() {
        Intent intent = new Intent(this, SysInfoActivity.class);
        startActivity(intent);
    }

    void ethernetInfo() {
        Intent intent = new Intent(this, NetSpeedSettings.class);
        startActivity(intent);
    }

    void wifiInfo() {

    }

    void bluetoothInfo() {

    }

    void exoPlayer() {
        Intent intent = new Intent(this, SampleChooserActivity.class);
        startActivity(intent);
    }

    void playMediaFromDisk() {
        final OpenFileDialog fileDialog = new OpenFileDialog(this);
        fileDialog.setFilter(".*\\.*");
        fileDialog.setOpenDialogListener(new OpenFileDialog.OpenDialogListener() {
            @Override
            public void OnSelectedFile(String fileName) {
                // Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_LONG).show();
                fileDialog.close();

                String uri = fileName;
                String extention = uri.substring(uri.lastIndexOf(".") + 1, uri.length());

                Log.d("MainActivity", "Uri = " + uri + ", Ext = " + extention);
                playContent(uri, extention);
            }
        });
        fileDialog.show();
    }

    void playlistFromDisk() {
        final OpenFileDialog fileDialog = new OpenFileDialog(this);
        fileDialog.setFilter(".*\\.json");
        fileDialog.setOpenDialogListener(new OpenFileDialog.OpenDialogListener() {
            @Override
            public void OnSelectedFile(String fileName) {
                // Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_LONG).show();
                fileDialog.close();

                String uri = fileName;
                String extention = uri.substring(uri.lastIndexOf(".") + 1, uri.length());

                Log.d("MainActivity", "Uri = " + uri + ", Ext = " + extention);

                playJson(uri);
            }
        });
        fileDialog.show();
    }

    public void onTest6(View v) {
    }

    public void onTest7(View v) {
    }

    public void onTest8(View v) {
    }

    public void onTest9(View v) {
    }

    void remoteControlTest() {
        Intent intent = new Intent(this, RemoteControlActivity.class);
        startActivity(intent);
    }

    void ledTest() {
        Intent intent = new Intent(this, LedActivity.class);
        startActivity(intent);
    }

    void playJson(String uri) {
        Intent intent = new Intent(this, SampleChooserActivity.class);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    void playContent(String uri, String extension) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.setData(Uri.parse(uri));
        intent.putExtra(PlayerActivity.EXTENSION_EXTRA, extension);
        intent.setAction(PlayerActivity.ACTION_VIEW);
        startActivity(intent);
    }
}
