package com.indevstudio.stbtest.network;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.indevstudio.stbtest.R;

import org.apache.http.client.HttpClient;

import java.net.URI;
import java.net.URISyntaxException;

public class NetSpeedSettings extends Activity {

    private static final String TAG = "NetSpeedSettings";
    Context mContext;
    private static final boolean DEBUG = true;

    private Button mButton;
    private TextView mTextView, mTextView_1;
    private ProgressBar mProgressBar;
    private HttpClient mHttpClient;
    private FileDownloadTask mTask;
    public static final String FLASH_ROOT = "/sdcard";
    private ProgressHandler mProgressHandler;
    private Runnable mRunnable;
    private Handler mHandler;
    private URI mUri = null;
    private int mTempTime = 5, mTime; //15
    private String mFileName = "Net_Speed_Test_File";
    private String mhttp = "http://speedtest.ftp.otenet.gr/files/test1Mb.db";
    private Boolean mReTestBakFile = false;

    private long receivedCount, contentLength, receivedPerSecond, mReceiveLength = 0;

    private static void LOG(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏

        setContentView(R.layout.net_speed_settings);

        mContext = this;

        mProgressBar = (ProgressBar) findViewById(R.id.net_speed_pb);
        mTextView = (TextView) findViewById(R.id.net_speed_point);
        mTextView_1 = (TextView) findViewById(R.id.net_speed_result);
        mButton = (Button) findViewById(R.id.net_speed_bt);

        mProgressBar.setVisibility(View.GONE);
        mTextView.setText("");
        mTextView_1.setText("");

        mTime = mTempTime;

        try {
            mUri = new URI(mhttp);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mProgressHandler = new ProgressHandler();
        mHttpClient = CustomerHttpClient.getHttpClient();

        // try to start
        mTask = new FileDownloadTask(mHttpClient, mUri, FLASH_ROOT, mFileName,
                1, mContext);
        mTask.setProgressHandler(mProgressHandler);
        mTask.start();


        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (mTime > 0) {
                    mTime--;
                    //  Toast.makeText(mContext, mTime, Toast.LENGTH_SHORT).show();
                    LOG("mTime++++++++++++++:" + mTime);
                    // mHandler.postDelayed(this, 1000);
                    //  LOG("mReceiveLength: " +mReceiveLength);
                    //  mReceiveLength = receivedCount;
                } else if (mTime == 0) {
                    mTime = -1;
                    ShowResult();
                }
            }
        };

        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ReTestNetSpeed();
            }
        });
    }

    private void ShowResult() {
        mProgressBar.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
        mTextView_1.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.VISIBLE);

        mButton.requestFocus();
        setDownloadInfoViews(contentLength, receivedCount,
                receivedPerSecond);
        mTask.stopDownload();
    }

    private void ReTestNetSpeed() {
        mProgressBar.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.VISIBLE);
        mTextView_1.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
        mTime = mTempTime;
        mReceiveLength = 0;

        mTask = new FileDownloadTask(mHttpClient, mUri, FLASH_ROOT, mFileName,
                1, mContext);

        mTask.setProgressHandler(mProgressHandler);
        mTask.start();

    }


    private void ReTestBakFile() {
        mTask.stopDownload();

        LOG("ReTestBakFile+++++++");

        try {
            mUri = new URI(mhttp);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mTask = new FileDownloadTask(mHttpClient, mUri, FLASH_ROOT, mFileName,
                1, mContext);

        mTask.setProgressHandler(mProgressHandler);
        mTask.start();
    }


    private void ShowErrorResult() {
        mProgressBar.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
        mTextView_1.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.VISIBLE);
        mTextView_1.setText(R.string.Net_Speed_error);
        mButton.requestFocus();
        mTask.stopDownload();
    }

    @Override
    public boolean onKeyDown(int arg0, KeyEvent arg1) {
        // TODO Auto-generated method stub

        if (arg0 == KeyEvent.KEYCODE_BACK) {

            if (mTask != null) {
                mTask.stopDownload();
                finish();
            }
        }
        return super.onKeyDown(arg0, arg1);
    }

    private class ProgressHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            int whatMassage = msg.what;

            //	LOG("whatMassage = " +whatMassage);

            switch (whatMassage) {
                case FileDownloadTask.ERR_NOERR: {
                    Bundle b = msg.getData();
                    receivedCount = b.getLong("ReceivedCount", 0);
                    contentLength = b.getLong("ContentLength", 0);
                    receivedPerSecond = b.getLong("ReceivedPerSecond", 0);

                    //	LOG("ReceivedCount+++++ = "+receivedCount);
                    mHandler.postDelayed(mRunnable, 0);
                }
                break;
                case FileDownloadTask.ERR_CONNECT_TIMEOUT: {
                    if (mReTestBakFile == false) {
                        mReTestBakFile = true;
                        ReTestBakFile();
                    } else {
                        mReTestBakFile = false;
                        ShowErrorResult();
                    }
                }
                break;
                case FileDownloadTask.ERR_REQUEST_STOP: {
                }
                break;
                case FileDownloadTask.ERR_NOT_EXISTS: {
                    if (mReTestBakFile == false) {
                        mReTestBakFile = true;
                        ReTestBakFile();
                    } else {
                        mReTestBakFile = false;
                        ShowErrorResult();
                    }
                }
                break;
                default:
                    break;
            }
        }
    }

    private void setDownloadInfoViews(long contentLength, long receivedCount,
                                      long receivedPerSecond) {

        String rate = "";

        mReceiveLength = receivedCount / mTempTime;

        if (mReceiveLength < 1024) {
            rate = String.valueOf(mReceiveLength) + "B/S";
        } else if (mReceiveLength / 1024 > 0
                && mReceiveLength / 1024 / 1024 == 0) {
            rate = String.valueOf(mReceiveLength / 1024) + "KB/S";
        } else if (mReceiveLength / 1024 / 1024 > 0) {
            rate = String.valueOf(mReceiveLength / 1024 / 1024) + "MB/S";
        }

//		if (receivedPerSecond < 1024) {
//			rate = String.valueOf(receivedPerSecond) + "B/S";
//		} else if (receivedPerSecond / 1024 > 0
//				&& receivedPerSecond / 1024 / 1024 == 0) {
//			rate = String.valueOf(receivedPerSecond / 1024) + "KB/S";
//		} else if (receivedPerSecond / 1024 / 1024 > 0) {
//			rate = String.valueOf(receivedPerSecond / 1024 / 1024) + "MB/S";
//		}


        mTextView_1.setText(rate);

    }


    // http://softdl.360tpcdn.com/WanDouJia/WanDouJia_2.56.1.2891bd.exe

    // http://softdl.360tpcdn.com/SoHuVA/SoHuVA_4.0.1.0.exe

    // http://softdl.360tpcdn.com/360sd/360sd_4.2.0.4055_2.exe

    // http://softdl.360tpcdn.com/qqtv/QQLive_8.50.7064.0.exe

}
