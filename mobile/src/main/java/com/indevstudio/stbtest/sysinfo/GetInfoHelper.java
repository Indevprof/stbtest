package com.indevstudio.stbtest.sysinfo;

import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetInfoHelper {
    public static String extractValue(String src, String regex) {
        String result = "";
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(src);
            if (matcher.find()) {
                result = matcher.group(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
