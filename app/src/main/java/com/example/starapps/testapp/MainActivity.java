package com.example.starapps.testapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdsList items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rvHetro);

        setItems();

        BooksAdapter adapter = new BooksAdapter(this, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setItems() {
        items = new AdsList();

        ArrayList<Book> bookArrayList = new ArrayList<Book>();
        for (int i = 0; i < 10; i++) {
            bookArrayList.add(new Book("Testing Book" + i));
        }

        items.addAll(bookArrayList);
    }

}
