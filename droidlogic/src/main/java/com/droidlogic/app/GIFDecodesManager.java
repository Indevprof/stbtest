package com.droidlogic.app;

import android.graphics.Bitmap;

import java.io.InputStream;

public class GIFDecodesManager {
    private static final String TAG = "GIFDecodesManager";

    static {
        System.loadLibrary("gifdecode_jni");
    }

    private static native void nativeDecodeStream(InputStream istream);
    private static native void nativeDestructor();
    private static native int nativeWidth();
    private static native int nativeHeight();
    private static native int nativeTotalDuration();
    private static native boolean nativeSetCurrFrame(int frameIndex);
    private static native int nativeGetFrameDuration(int frameIndex);
    private static native int nativeGetFrameCount();
    private static native Bitmap nativeGetFrameBitmap(int frameIndex);

    public static void decodeStream(InputStream is) {
        nativeDecodeStream(is);
    }

    public static void destructor() {
        nativeDestructor();
    }

    public static int width() {
        return nativeWidth();
    }

    public static int height() {
        return nativeHeight();
    }

    public static int getTotalDuration() {
        return nativeTotalDuration();
    }

    public static boolean setCurrFrame(int frameIndex) {
        return nativeSetCurrFrame(frameIndex);
    }

    public static int getFrameDuration(int frameIndex) {
        return nativeGetFrameDuration(frameIndex);
    }

    public static int getFrameCount() {
        return nativeGetFrameCount();
    }

    public static Bitmap getFrameBitmap(int frameIndex) {
        return nativeGetFrameBitmap(frameIndex);
    }
}
