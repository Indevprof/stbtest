package com.indevstudio.stbtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.indevstudio.stbtest.exoplayer.PlayerActivity;
import com.indevstudio.stbtest.exoplayer.SampleChooserActivity;
import com.indevstudio.stbtest.led.LedActivity;
import com.indevstudio.stbtest.network.NetSpeedSettings;
import com.indevstudio.stbtest.sysinfo.SysInfoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.requestOtherPermissions(this);
    }

    public void onTest1(View v) {
        Intent intent = new Intent(this, SysInfoActivity.class);
        startActivity(intent);
    }

    public void onTest2(View v) {
        Intent intent = new Intent(this, NetSpeedSettings.class);
        startActivity(intent);
    }

    public void onTest3(View v) {
    }

    public void onTest4(View v) {
    }

    public void onTest5(View v) {
        Intent intent = new Intent(this, SampleChooserActivity.class);
        startActivity(intent);
    }

    public void onTest51(View v) {
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

    public void onTest52(View v) {
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

    public void onTest10(View v) {
    }

    public void onTest11(View v) {
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
