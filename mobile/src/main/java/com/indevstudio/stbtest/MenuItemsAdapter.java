package com.indevstudio.stbtest;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MenuItemsAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

    List<ListviewItem> items;

    public MenuItemsAdapter(Context context, List<ListviewItem> items) {
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
        view = inflater.inflate(R.layout.menu_item, null);

        ListviewItem item = (ListviewItem) getItem(position);

        TextView textView_text = (TextView) view.findViewById(R.id.text);

        if (textView_text != null) {
            textView_text.setText(item.getName());
        }
        if (item.getValue() == "header") {
            view.setBackgroundResource(R.color.colorHeader);
            view.setFocusable(false);
            view.setClickable(false);
            if (textView_text != null) {
                // textView_text.setTypeface(textView_text.getTypeface(), Typeface.BOLD);
                textView_text.setTextColor(context.getResources().getColor(R.color.colorTextColorHeader, context.getTheme()));
            }
        } else
            UiHelper.setViewStyle(context, view);

        return view;
    }

}
