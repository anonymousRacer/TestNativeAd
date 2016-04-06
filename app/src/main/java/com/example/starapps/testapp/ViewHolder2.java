package com.example.starapps.testapp;

import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/**
 * Created by starapps on 30/03/16.
 */
public class ViewHolder2 extends RecyclerView.ViewHolder {

    private TextView label2;

    public ViewHolder2(View v) {
        super(v);
        label2 = (TextView) v.findViewById(R.id.row_2_text);
    }

    public TextView getLabel2() {
        return label2;
    }

    public void setLabel1(TextView label2) {
        this.label2 = label2;
    }

    public void configureView(Object bookObject) {
        AudioBook book = (AudioBook) bookObject;
        getLabel2().setText("Book: " + book.getTitle());
    }

}