package com.example.starapps.testapp;

import android.content.Context;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.NativeAd;

/**
 * Created by starapps on 04/04/16.
 */

public class AdObject implements AdListener {

    Context context;
    private NativeAd nativeAd;
    private BaseAdapter adapter;
    private boolean loading = false;
    private int position;

    public AdObject(Context context, BaseAdapter adapter, int position) {
        this.context = context;
        this.adapter = adapter;
        this.position = position;
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

    public void showNativeAd() {
        if (!loading) {
            this.nativeAd = new NativeAd(context, "450240575183008_493902080816857");
            nativeAd.setAdListener(this);
            nativeAd.loadAd();
            loading = true;
        }
    }

    @Override
    public void onAdLoaded(Ad ad) {
        this.nativeAd = (NativeAd) ad;
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onAdClicked(Ad ad) {

    }

    @Override
    public void onError(Ad ad, AdError adError) {
        Log.d("error", adError.getErrorMessage());
    }

}
