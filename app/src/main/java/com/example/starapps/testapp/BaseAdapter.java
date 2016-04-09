package com.example.starapps.testapp;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;

/**
 * Created by starapps on 31/03/16.
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    ArrayList items;
    ArrayList<AdObject> adsList;

    private final int AD_TYPE = 0, CHILD_TYPE = 1;
    private int AD_FREQUENCY = 4, AD_FIRST_INDEX = 2;

    abstract void populateChild(RecyclerView.ViewHolder holder, int position);

    abstract RecyclerView.ViewHolder loadChildViewHolder(LayoutInflater inflater, ViewGroup parent);

    BaseAdapter(Context context, ArrayList items) {
        this.context = context;
        this.items = items;
        adsList = new ArrayList<AdObject>();
    }

    BaseAdapter(Context context, ArrayList items, int AD_FIRST_INDEX, int AD_FREQUENCY) {
        this.context = context;
        this.items = items;
        this.AD_FREQUENCY = AD_FREQUENCY;
        this.AD_FIRST_INDEX = AD_FIRST_INDEX;
        adsList = new ArrayList<AdObject>();
    }

    private boolean isAPTerm(int term) {
        return ( term - AD_FIRST_INDEX ) % ( AD_FREQUENCY + 1 ) == 0;
    }

    private int getTermIndex(int term) {
        return ( term - AD_FIRST_INDEX ) / ( AD_FREQUENCY + 1 );
    }

    @Override
    public int getItemViewType(int position) {
        if ( isAPTerm(position) ) {
            return AD_TYPE;
        }
        return CHILD_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case AD_TYPE:
                View v = inflater.inflate(R.layout.row_list_native_ad, parent, false);
                viewHolder = new NativeAdViewHolder(v, context);
                break;
            case CHILD_TYPE:
                viewHolder = loadChildViewHolder(inflater, parent);
                break;
            default:
                viewHolder = loadChildViewHolder(inflater, parent);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int termIndex = getTermIndex(position);
        switch (holder.getItemViewType()) {
            case AD_TYPE:
                NativeAdViewHolder viewHolder = (NativeAdViewHolder) holder;

                if ( termIndex + 1 > adsList.size() ) {
                    adsList.add(termIndex, new AdObject(context, this, position));
                }

                if (adsList.get(termIndex).getNativeAd() != null )  {
                    if (adsList.get(termIndex).getNativeAd().isAdLoaded()) {
                        viewHolder.configureView(adsList.get(termIndex).getNativeAd());
                    }
                }
                else {
                    adsList.get(termIndex).showNativeAd();
                }
                break;
            case CHILD_TYPE:
                if (position > 2) {
                    populateChild(holder, position - termIndex - 1);
                }
                else {
                    populateChild(holder, position);
                }
                break;
        }
    }

    private static class NativeAdViewHolder extends RecyclerView.ViewHolder {

        private TextView nativeAdTitle;
        private TextView nativeAdBody;
        private TextView nativeAdSocialContext;
        private Button nativeAdCallToAction;
        private ImageView nativeAdIcon;
        private MediaView nativeAdMedia;
        private View adView;
        private Context context;

        public NativeAdViewHolder(View adView, Context context) {
            super(adView);
            this.adView = adView;
            this.context = context;
            nativeAdIcon = (ImageView)adView.findViewById(R.id.native_ad_icon);
            nativeAdTitle = (TextView)adView.findViewById(R.id.native_ad_title);
            nativeAdBody = (TextView)adView.findViewById(R.id.native_ad_body);
            nativeAdMedia = (MediaView)adView.findViewById(R.id.native_ad_media);
            nativeAdSocialContext = (TextView)adView.findViewById(R.id.native_ad_social_context);
            nativeAdCallToAction = (Button)adView.findViewById(R.id.native_ad_call_to_action);
        }

        public void configureView(NativeAd nativeAd) {

            // Setting the Text.
            nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
            nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
            nativeAdTitle.setText(nativeAd.getAdTitle());
            nativeAdBody.setText(nativeAd.getAdBody());

            // Downloading and setting the ad icon.
            NativeAd.Image adIcon = nativeAd.getAdIcon();
            //NativeAd.downloadAndDisplayImage(adIcon, nativeAdIcon);

            // Download and setting the cover image.
            NativeAd.Image adCoverImage = nativeAd.getAdCoverImage();
            int bannerWidth = adCoverImage.getWidth();
            int bannerHeight = adCoverImage.getHeight();
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            int mediaWidth = adView.getWidth() > 0 ? adView.getWidth() : metrics.widthPixels;
            nativeAdMedia.setLayoutParams(new LinearLayout.LayoutParams(
                    mediaWidth,
                    Math.min(
                            (int) (((double) mediaWidth / (double) bannerWidth) * bannerHeight),
                            metrics.heightPixels / 3)));
            nativeAdMedia.setNativeAd(nativeAd);
            nativeAd.registerViewForInteraction(adView);
        }

    }

}
