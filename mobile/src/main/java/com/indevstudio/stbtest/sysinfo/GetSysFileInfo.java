package com.indevstudio.stbtest.sysinfo;

import com.indevstudio.stbtest.ListviewItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public abstract class GetSysFileInfo {

    String filename;
    HashMap<String, String> items = new LinkedHashMap<>();

    public GetSysFileInfo(String filename) {
        this.filename = filename;
        readSysFileInfo();
    }

    void readSysFileInfo() {
        if (filename == null || filename.isEmpty())
            return;

        long result = 0;

        try {
            FileReader reader = new FileReader(filename);
            BufferedReader br = new BufferedReader(reader);

            long total = 0;
            String str = null;

            while ((str = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(str, ":");
                if (stk.countTokens() > 1)
                    items.put(stk.nextToken().trim(), stk.nextToken().trim());
            }

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract public HashMap<String, String> getItems();

    public HashMap<String, String> getAllItems() {
        return items;
    }

    HashMap<String, String> getNamedItems(HashMap<String, String> names) {
        HashMap<String, String> resultItems = new LinkedHashMap<>();

        for (Map.Entry<String, String> e : names.entrySet()) {
            if (items.keySet().contains(e.getKey())) {
                resultItems.put(e.getValue(), items.get(e.getKey()));
            }
        }
        return resultItems;
    }

    HashMap<String, String> getNamedItemsAndItems(String title1, String title2, HashMap<String, String> names) {
        HashMap<String, String> resultItems = new LinkedHashMap<>();

        resultItems.put(title1, "header");
        resultItems.putAll(getNamedItems(names));
        resultItems.put(title2, "header");
        resultItems.putAll(getAllItems());

        for (Map.Entry<String, String> e : names.entrySet()) {
            resultItems.remove(e.getKey());
        }

        return resultItems;
    }


}
