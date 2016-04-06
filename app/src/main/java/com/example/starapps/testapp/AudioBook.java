package com.example.starapps.testapp;

/**
 * Created by starapps on 30/03/16.
 */
public class AudioBook {

    public AudioBook(String title) {
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;
}
