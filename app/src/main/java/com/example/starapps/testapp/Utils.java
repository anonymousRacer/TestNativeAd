package com.example.starapps.testapp;

/**
 * Created by starapps on 30/03/16.
 */
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public void getModifiedItems(List<Object> items) {
        int n =  ( items.size() + 2 ) / 4;
        for (int i = 1; i <= n; i++) {
            items.add( 5 * i - 3 , new Object());
        }
    }

}
