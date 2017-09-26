package com.indevstudio.stbtest;

import android.Manifest;
import android.app.Activity;

public class App {
    private static final int PERMISSION_REQUEST_CODE = 1;

    public static void requestOtherPermissions(Activity activity) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            String[] permissions = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
            };

            activity.requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
    }
}
