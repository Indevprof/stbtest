package com.indevstudio.stbtest.sysinfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class GetSysFileInfo {

    String filename;
    HashMap<String, String> items = new LinkedHashMap<>();

    public GetSysFileInfo(String filename) {
        this.filename = filename;
        readSysFileInfo();
    }

    public HashMap<String, String> getItems() {
        return items;
    }

    void readSysFileInfo() {
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
}
