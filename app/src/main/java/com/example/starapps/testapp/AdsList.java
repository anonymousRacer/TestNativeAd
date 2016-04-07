package com.example.starapps.testapp;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by starapps on 04/04/16.
 */

public class AdsList extends ArrayList<Object> {

    int adFrequency = 4;
    int firstAdIndex = 2;

    public AdsList() {
    }

    public AdsList(int adFrequency, int firstAdIndex) {
        super();
        this.adFrequency = adFrequency;
        this.firstAdIndex = firstAdIndex;
    }

    boolean isAdIndex(int index) {
        return ( index - firstAdIndex ) % ( adFrequency + 1 ) == 0;
    }

//    @Override
//    public boolean addAll(Collection<?> collection) {
//        int currentSize = this.size(), i;
//        if (currentSize == 0) {
//            i = currentSize;
//        }
//        else {
//            i = currentSize - 1;
//        }
//
//        for(Object obj:collection){
//            if (isAdIndex(i)) {
//                this.add(new AdObject());
//                i++;
//            }
//            this.add(obj);
//            i++;
//        }
//        return true;
//    }
//
//    @Override
//    public boolean addAll(int index, Collection<?> collection) {
//        int i = index;
//        for(Object obj:collection){
//            if (isAdIndex(i)) {
//                this.add(new AdObject());
//                i++;
//            }
//            this.add(obj);
//            i++;
//        }
//        return true;
//    }

}
