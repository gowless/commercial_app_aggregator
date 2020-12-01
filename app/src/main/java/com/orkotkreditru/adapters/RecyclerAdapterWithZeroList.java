package com.orkotkreditru.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orkotkreditru.R;
import com.orkotkreditru.activities.MainActivity;
import com.orkotkreditru.activities.SplashActivity;
import com.orkotkreditru.models.get.Liste;
import com.orkotkreditru.models.post.MainExample;
import com.orkotkreditru.network.InitializatorIP;
import com.orkotkreditru.network.Interface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class  RecyclerAdapterWithZeroList extends RecyclerView.Adapter< RecyclerAdapterWithZeroList.ViewHolder> {
    public static String campaign, campaign_id, creative_id, creative, adgroup, adgroup_id, string;


    private FirebaseAnalytics mFirebaseAnalytics;

    Context context;

    public void setDataList(List<Liste> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    List<Liste> dataList;

    public RecyclerAdapterWithZeroList(Context context, List<Liste> dataList) {
        this.context = context;
        this.dataList = dataList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == 1) {
            view = inflater.inflate(R.layout.fragment_top, parent, false);
        } else {
            view = inflater.inflate(R.layout.fragment, parent, false);
        }
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Liste liste = dataList.get(position);


        String firstCreditSum = liste.getAmount().getFrom().toString();
        String percentRate = liste.getPercent().getFrom().toString();

        //setting holders to textViews
        holder.firstCreditSum.setText(firstCreditSum + "₴");
        holder.percentRate.setText(percentRate + "%");


        //setting image holder with glide
        Glide.with(context)
                .load(dataList.get(position)
                        .getImg())
                .centerInside()
                .into(holder.imgCompany);


        holder.click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdjustEvent adjustEvent = new AdjustEvent("8q9nkq");
                Adjust.trackEvent(adjustEvent);

                //switch case
                switch (MainActivity.net) {
                    case "Facebook Installs":
                        fbAdsGetData();  //init vars
                        //putting data to offer_click on CRM
                        putJsonData(
                                "ua", liste.getCpa(), "com.orkotkreditru", liste.getOfferName(), liste.getOfferId().toString(),
                                getId(), MainActivity.adid,
                                getCurrentTime(), MainActivity.net, "-", campaign,
                                campaign_id, creative, creative_id,
                                adgroup, adgroup_id);

                    case "Google Ads UAC":
                        googleAdsGetData();  //init vars
                        //putting data to offer_click on CRM
                        putJsonData(
                                "ua", liste.getCpa(), "com.orkotkreditru", liste.getOfferName(), liste.getOfferId().toString(),
                                getId(), SplashActivity.ad_id,
                                getCurrentTime(), MainActivity.net, "-", campaign,
                                campaign_id, "-", "-",
                                adgroup, "-");

                    case "Organic":
                        //putting data to offer_click on CRM
                        putJsonData(
                                "ua", liste.getCpa(), "com.orkotkreditru", liste.getOfferName(), liste.getOfferId().toString(),
                                getId(), SplashActivity.ad_id,
                                getCurrentTime(), MainActivity.net, "organic", "organic",
                                "organic", "organic", "organic",
                                "organic", "organic");

                    case "Unattributed":
                        //putting data to offer_click on CRM
                        putJsonData(
                                "ua", liste.getCpa(), "com.orkotkreditru", liste.getOfferName(), liste.getOfferId().toString(),
                                getId(), SplashActivity.ad_id,
                                getCurrentTime(), MainActivity.net, "unattributed",
                                "unattributed",
                                "unattributed", "unattributed", "unattributed",
                                "unattributed", "unattributed");


                }

                mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, liste.getOfferId().toString());
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, liste.getOfferName());
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, bundle);

                //starting default web-browser to current tab wit main URL
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(parseLinkFromApi(position)));
                v.getContext().startActivity(browserIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //declaring items
        ConstraintLayout click_layout;
        TextView firstCreditSum, percentRate;
        ImageView imgCompany;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //initializing
            click_layout = itemView.findViewById(R.id.click_layout);
            button = itemView.findViewById(R.id.button);
            imgCompany = itemView.findViewById(R.id.imgCompany);
            firstCreditSum = itemView.findViewById(R.id.firstCreditSum);
            percentRate = itemView.findViewById(R.id.percentRate);

        }
    }

    @Override
    public int getItemViewType(int position) {

        if (dataList.get(position).getTop()) {
            return 1;
        } else {
            return 2;
        }
    }

    //setting to get json file and parse it to models
    public void putJsonData(String geo, String cpa, String app, String offer_name, String offer_id, String client_id, String advertising_id, String click_date, String source, String chanel, String campaing, String campaing_id, String adset, String adset_id, String adgroup, String adgroup_id) {
        //interface init
        Interface apiInterfaceCount = InitializatorIP.getClient().create(Interface.class);
        //main callback
        Call<MainExample> call = apiInterfaceCount.putMainDataField(geo, cpa, app, offer_name, offer_id, client_id, advertising_id, click_date, source, chanel, campaing, campaing_id, adset, adset_id, adgroup, adgroup_id);
        call.enqueue(new Callback<MainExample>() {
            @Override
            public void onResponse(@NonNull Call<MainExample> call, @NonNull Response<MainExample> response) {
                //tagged successful callback
                Log.d("TAGS", "Successful");
            }

            @Override
            public void onFailure(@NonNull Call<MainExample> call, @NonNull Throwable t) {

            }
        });

    }

    //getting current time for post request
    public String getCurrentTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss-dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public String getId() {


        @SuppressLint("HardwareIds")
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }


    //change variables
    public void fbAdsGetData() {
        //adgroup
        adgroup = MainActivity.adg.substring(0, MainActivity.adg.indexOf("(") - 1);
        adgroup_id = MainActivity.adg.substring(MainActivity.adg.indexOf("(") + 1, MainActivity.adg.indexOf(")"));

        //creative
        creative = MainActivity.cre.substring(0, MainActivity.cre.indexOf("(") - 1);
        creative_id = MainActivity.cre.substring(MainActivity.cre.indexOf("(") + 1, MainActivity.cre.indexOf(")"));

        //campaing
        campaign = MainActivity.cam.substring(0, MainActivity.cam.indexOf("(") - 1);
        campaign_id = MainActivity.cam.substring(MainActivity.cam.indexOf("(") + 1, MainActivity.cam.indexOf(")"));

    }

    public void googleAdsGetData() {
        campaign = MainActivity.cam.substring(0, MainActivity.cam.indexOf("(") - 1);
        campaign_id = MainActivity.cam.substring(MainActivity.cam.indexOf("(") + 1, MainActivity.cam.indexOf(")"));

    }


    public String parseLinkFromApi(int position) {

        // https://tds.pdl-profit.com?affid=18827&offer_id=1158&subid={client_id}&subid2={advertising_id}&subid3={app}&utm_source={source}&utm_campaign={campaign}&utm_adgroup={adgroup}&utm_adposition={adset}&utm_creative={chanel}"
        final Liste liste = dataList.get(position);
        //Main URI declaring and initialising
        String mainEditedURI = liste.getUrl();
        //manipulating with main string, changing parameters
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{client_id}"), getId());
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{advertising_id}"), SplashActivity.ad_id);
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{app}"), "com.orkotkreditru");

        //if organic/non-organic campaign
        if (MainActivity.net.equals("Organic")) {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{source}"), "organic");
        } else if (MainActivity.net.equals("Unattributed")) {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{source}"), "unattributed");
        } else {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{source}"), MainActivity.net);
        }


        //if organic/non-organic campaign
        if (MainActivity.cam == "") {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{campaign}"), "organic");
        } else {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{campaign}"), MainActivity.cam);

        }


        //if organic/non-organic adgroup
        if (MainActivity.adg == "") {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{adgroup}"), "organic");
        } else {
            mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{adgroup}"), MainActivity.adg);
        }


        //if organic/non-organic adset
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{adset}"), "organic");

        //if organic/non-organic chanel
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{chanel}"), "organic");

        //if organic/non-organic chanel
        mainEditedURI = mainEditedURI.replaceAll(Pattern.quote("{geo}"), "ru");

        Log.d("FINISH", mainEditedURI);
        return mainEditedURI;
    }
}
