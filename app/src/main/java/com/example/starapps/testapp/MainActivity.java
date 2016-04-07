package com.example.starapps.testapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NativeAdsManager.Listener, AdListener {

    RecyclerView recyclerView;
    ArrayList<Object> items;
    private NativeAdsManager listNativeAdsManager;
    int adFrequency = 4;
    int firstAdIndex = 2;
    int maxAds = 0;
    BooksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rvHetro);
        recyclerView.setHasFixedSize(false);

        setItems();

        adapter = new BooksAdapter(this, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        maxAds = ( ( items.size() - firstAdIndex ) / adFrequency ) + 1;
        listNativeAdsManager = new NativeAdsManager(this, "450240575183008_493902080816857", maxAds);
        listNativeAdsManager.setListener(this);
        listNativeAdsManager.loadAds();
    }

    public void setItems() {
        items = new ArrayList<Object>();
        for (int i = 0; i < 25; i++) {
            items.add(new Book("Testing Book" + i));
        }
    }

    @Override
    public void onAdsLoaded() {
        int index = 0;
        while(index != maxAds) {
            NativeAd ad = this.listNativeAdsManager.nextNativeAd();
            ad.setAdListener(this);
            items.add(firstAdIndex + index * (adFrequency + 1), ad);
            index++;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAdLoaded(Ad ad) {

    }

    @Override
    public void onAdError(AdError adError) {

    }

    @Override
    public void onAdClicked(Ad ad) {

    }

    @Override
    public void onError(Ad ad, AdError adError) {

    }

}
