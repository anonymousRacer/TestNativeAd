package com.example.starapps.testapp;

import android.content.Context;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.NativeAd;

/**
 * Created by starapps on 04/04/16.
 */

public class AdObject {

    Context context;
    private NativeAd nativeAd;
    private BaseAdapter adapter;

    public AdObject() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public NativeAd getNativeAd() {
        return nativeAd;
    }

    public void setNativeAd(NativeAd nativeAd) {
        this.nativeAd = nativeAd;
    }

    public BaseAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    public void showNativeAd(){
        nativeAd.loadAd();
    }
}
