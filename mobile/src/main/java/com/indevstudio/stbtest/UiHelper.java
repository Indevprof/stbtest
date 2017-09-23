package com.indevstudio.stbtest;

import android.content.Context;
import android.widget.TextView;

public class UiHelper {
    public static void setTextViewStyle(Context context, TextView textView) {
        // textView.setFocusable(true);
        textView.setBackgroundResource(R.drawable.listview_background);
        ((TextView) textView).setTextColor(context.getResources().getColor(R.color.listview_textcolor, context.getTheme()));
    }
}
