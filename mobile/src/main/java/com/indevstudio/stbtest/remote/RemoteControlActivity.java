package com.indevstudio.stbtest.remote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.indevstudio.stbtest.R;

import java.util.HashMap;

public class RemoteControlActivity extends AppCompatActivity {

    HashMap<Integer, Integer> keyMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);

        initKeyMap();
    }

    void initKeyMap() {
        keyMap = new HashMap<>();

//        keyMap.put(2, R.id.button_1_11);
//        keyMap.put(3, R.id.button_1_12);
//        keyMap.put(4, R.id.button_1_13);

        keyMap.put(15, R.id.button_1_21);
        keyMap.put(139, R.id.button_1_22);
//        keyMap.put(4, R.id.button_1_23);

        keyMap.put(103, R.id.button_1_32);

        keyMap.put(105, R.id.button_1_41);
        keyMap.put(97, R.id.button_1_42);
        keyMap.put(106, R.id.button_1_43);

        keyMap.put(108, R.id.button_1_52);

        keyMap.put(122, R.id.button_1_61);
        keyMap.put(119, R.id.button_1_62);
        keyMap.put(123, R.id.button_1_63);

        keyMap.put(104, R.id.button_1_71);
        keyMap.put(113, R.id.button_1_72);
//        keyMap.put(4, R.id.button_1_73);

        keyMap.put(109, R.id.button_1_81);
//        keyMap.put(3, R.id.button_1_82);
//        keyMap.put(4, R.id.button_1_83);

        // Digits
        keyMap.put(2, R.id.button_2_11);
        keyMap.put(3, R.id.button_2_12);
        keyMap.put(4, R.id.button_2_13);

        keyMap.put(5, R.id.button_2_21);
        keyMap.put(6, R.id.button_2_22);
        keyMap.put(7, R.id.button_2_23);

        keyMap.put(8, R.id.button_2_31);
        keyMap.put(9, R.id.button_2_32);
        keyMap.put(10, R.id.button_2_33);

        keyMap.put(11, R.id.button_2_42);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        TextView textView = (TextView) this.findViewById(R.id.text_keyCode);
        textView.setText(String.format("%d", keyCode));

        textView = (TextView) this.findViewById(R.id.text_scanCode);
        textView.setText(String.format("%d", event.getScanCode()));

        textView = (TextView) this.findViewById(R.id.text_sysCode);
        textView.setText(event.getCharacters());

        paintButton(event.getScanCode());

        CheckBox checkBox = (CheckBox) this.findViewById(R.id.checkBox);

        if (checkBox.isChecked())
            return super.onKeyUp(keyCode, event);

        return true;
    }

    void paintButton(int scanCode) {

        Integer resId = keyMap.get(scanCode);
        if (resId != null) {
            Button btn = (Button) this.findViewById(resId);
            if (btn != null) {
                int pl = btn.getPaddingLeft();
                int pr = btn.getPaddingRight();
                int pt = btn.getPaddingTop();
                int pb = btn.getPaddingBottom();
                btn.setBackgroundResource(R.color.colorPrimaryLight);
                btn.setPadding(pl, pt, pr, pb);
            }
        }
    }

}
