package com.indevstudio.stbtest;

public class ListviewItem {

    String name;
    String value;

    public ListviewItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == "header")
            return name;

        return String.format("%s : %s", name, value);
    }

    public String getName() {
        if (name == null)
            return "";
        return name.trim();
    }

    public String getValue() {
        if (value == null)
            return "";
        return value.trim();
    }
}
