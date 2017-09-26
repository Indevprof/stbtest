package com.indevstudio.stbtest.led;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.indevstudio.stbtest.ListviewItem;
import com.indevstudio.stbtest.MenuItemsAdapter;
import com.indevstudio.stbtest.R;

import java.io.IOException;
import java.util.ArrayList;

public class LedActivity extends AppCompatActivity {

    ChangeLedLight changeLedLight = new ChangeLedLight();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);

        ArrayList<ListviewItem> items = new ArrayList<>();

        items.add(new ListviewItem("Зеленый", "on_G"));
        items.add(new ListviewItem("Красный", "on_R"));
        items.add(new ListviewItem("Мигание зеленый", "flash_G"));
        items.add(new ListviewItem("Мигание красный", "flash_R"));
        items.add(new ListviewItem("Мигание зеленый-красный", "flash_RG"));
        items.add(new ListviewItem("Выключен", "off"));

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MenuItemsAdapter(this, items));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onAction((ListviewItem) listView.getItemAtPosition(position));
            }
        });
    }

    void onAction(ListviewItem item) {
        try {
            if (item.getValue().equals("on_G")) {
                changeLedLight.setLedG();
            } else if (item.getValue().equals("on_R")) {
                changeLedLight.setLedR();
            } else if (item.getValue().equals("flash_G")) {
                changeLedLight.setLedFlashG();
            } else if (item.getValue().equals("flash_R")) {
                changeLedLight.setLedFlashR();
            } else if (item.getValue().equals("flash_RG")) {
                changeLedLight.setLedFlashRG();
            } else if (item.getValue().equals("off")) {
                changeLedLight.setLedOff();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        try {
            changeLedLight.setLedG();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
