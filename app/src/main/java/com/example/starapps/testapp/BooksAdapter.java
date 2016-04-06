package com.example.starapps.testapp;

/**
 * Created by starapps on 30/03/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by starapps on 30/03/16.
 */
public class BooksAdapter extends BaseAdapter {

    BooksAdapter(Context context, AdsList items) {
        super(context, items);
    }

    @Override
    RecyclerView.ViewHolder loadChildViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View v = inflater.inflate(R.layout.row_type_1, parent, false);
        return new ViewHolder1(v);
    }

    @Override
    void populateChild(RecyclerView.ViewHolder holder, int position) {
        ViewHolder1 vh = (ViewHolder1)holder;
        vh.configureView(new Book("" + position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView label1;

        public ViewHolder1(View v) {
            super(v);
            label1 = (TextView) v.findViewById(R.id.row_1_text);
        }

        public TextView getLabel1() {
            return label1;
        }

        public void setLabel1(TextView label1) {
            this.label1 = label1;
        }

        public void configureView(Object bookObject) {
            Book book = (Book) bookObject;
            getLabel1().setText("Book: " + book.getTitle());
        }

    }

}
