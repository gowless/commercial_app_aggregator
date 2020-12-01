package com.orkotkreditru.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresApi;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orkotkreditru.R;


public class SplashActivity extends Activity {
    public static String  tt, tn, net, cam, adg, cre;

    //carrier name string
    String carrier;


    //static AD_ID
    public static String ad_id;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences settings = getSharedPreferences("LOCAL", Context.MODE_PRIVATE);

        tt = settings.getString("trackerToken", "");
        tn = settings.getString("trackerName", "");
        net = settings.getString("network", "");
        cam = settings.getString("campaign", "");
        adg = settings.getString("adgroup", "");
        cre = settings.getString("creative", "");

        // applyAdjustSDK();
        //firebase log
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);

        //get ad_id
        getID();

        //getting carrier ISO name
        getCarrier();

                    if (carrier.equals("ua")) {
                        getBeforeMain();
                    } else {
                        getCloak();
                    }



        //checking if statements
    }


    //starting CloakActivity
    public void getCloak() {

        //init handler
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, CloakActivity.class));
                finish();
            }
        }, 1500);

    }

    //get carrier name
    public void getCarrier() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        carrier = manager.getSimCountryIso();
    }

    public void getBeforeMain(){
        //init handler
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, BeforeMainActivity.class));
                finish();
            }
        }, 1500);
    }

    public void getID() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(SplashActivity.this);
                    ad_id = adInfo != null ? adInfo.getId() : null;

                    assert ad_id != null;

                } catch (Exception e) {

                }
            }
        });
    }


}