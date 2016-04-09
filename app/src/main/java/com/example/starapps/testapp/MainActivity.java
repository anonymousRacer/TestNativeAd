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

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Book> items;
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
    }

    public void setItems() {
        items = new ArrayList<Book>();
        for (int i = 0; i < 20; i++) {
            items.add(new Book("Testing Book" + i));
        }
    }

}
