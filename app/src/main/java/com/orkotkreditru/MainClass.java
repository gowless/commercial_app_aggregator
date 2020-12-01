package com.orkotkreditru;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatRadioButton;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnAttributionChangedListener;
import com.orkotkreditru.models.get.Data;
import com.orkotkreditru.models.get.Liste;
import com.orkotkreditru.network.Initializator;
import com.orkotkreditru.network.Interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainClass extends Application {
    public static List<Liste> listDataAll;
    public static List<Liste> listDataBad;
    public static List<Liste> listDataZero;
    public static ArrayList<String> values;
    public static ArrayList<String> labels;
    public static String response_main;


    //url base
    public static final String APP_ID = "com.kreditonlinenakartuua";
    public static Boolean isEmpty;
    SharedPreferences prefs = null;
    public static int numberOfTabs;
    public static String first, second, third;

    //vars
    public static String trackerToken, trackerName, network, campaign, adgroup, creative, adid;
    public static Float font;


    //setting to get json file and parse it to models
    public void getJsonData() {

        Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
        Call<Data> call = apiInterfaceCount.getData(APP_ID);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                assert response.body() != null;
                listDataAll = response.body().getListe();

                isEmpty = response.body().getCategories().isEmpty();
                numberOfTabs = response.body().getCategories().size();

                Iterator<Liste> i = listDataAll.iterator();
                while (i.hasNext()){
                    Liste s = i.next();
                    if (!s.getCategories().getAll()){
                        i.remove();
                    } else if (s.getHidden()){
                        i.remove();
                    } else {

                    }
                }




                switch (numberOfTabs) {
                    case 0:
                        break;
                    case 1:
                        first = response.body().getCategories().get(0).getLabel();
                        break;
                    case 2:
                        first = response.body().getCategories().get(0).getLabel();
                        second = response.body().getCategories().get(1).getLabel();
                        break;
                    case 3:
                        first = response.body().getCategories().get(0).getLabel();
                        second = response.body().getCategories().get(1).getLabel();
                        third = response.body().getCategories().get(2).getLabel();
                        break;
                }


            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {

            }
        });

    }



    //setting to get json file and parse it to models
    public void getJsonData2() {

        Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
        Call<Data> call = apiInterfaceCount.getData(APP_ID);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                assert response.body() != null;
                listDataBad = response.body().getListe();

                Iterator<Liste> i = listDataBad.iterator();
                while (i.hasNext()){
                    Liste s = i.next();
                    if (!s.getCategories().getBadCreditHistory()){
                        i.remove();
                    } else if (s.getHidden()){
                        i.remove();
                    } else {

                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {

            }
        });

    }

    //setting to get json file and parse it to models
    public void getJsonData3() {

        Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
        Call<Data> call = apiInterfaceCount.getData(APP_ID);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                assert response.body() != null;
                listDataZero = response.body().getListe();

                Iterator<Liste> i = listDataZero.iterator();
                while (i.hasNext()){
                    Liste s = i.next();
                    if (!s.getCategories().getZero()){
                        i.remove();
                    } else if(s.getHidden()){
                        i.remove();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {

            }
        });

    }





    @Override
    public void onCreate() {
        super.onCreate();
        values = new ArrayList<String>();
        labels = new ArrayList<String>();
        getJsonData();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        URLConnection connection = null;
        try {
            connection = new URL("https://api.orbitrush.com/offers?appId=com.kreditonlinenakartuua").openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(Scanner scanner = new Scanner(connection.getInputStream());){
            response_main = scanner.useDelimiter("\\A").next();
            Log.d("RESP", response_main);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(response_main);
            JSONArray jsonArray = jsonObject.getJSONArray("categories");
            Log.d("RESP", jsonArray.toString());
            for (int i=0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                Log.d("RESP", object.getString("value"));
                String s = object.getString("value");
                String d = object.getString("label");
                values.add(s);
                labels.add(d);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Configure adjust SDK.
        String appToken = "ekkl5aivleyo";
        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;
        AdjustConfig config = new AdjustConfig(this, appToken, environment);
        // enable all logs
        config.setLogLevel(LogLevel.VERBOSE);
        font = getResources().getConfiguration().fontScale;
        config.setOnAttributionChangedListener(new OnAttributionChangedListener() {
            @Override
            public void onAttributionChanged(AdjustAttribution attribution) {
                SharedPreferences settings = getSharedPreferences("LOCAL", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                trackerToken = attribution.trackerToken;
                trackerName = attribution.trackerName;
                network = attribution.network;
                campaign = attribution.campaign;
                adgroup = attribution.adgroup;
                creative = attribution.creative;
                adid = attribution.adid;


                //put to sharedprefs
                editor.putString("trackerToken", trackerToken);
                editor.putString("trackerName", trackerName);
                editor.putString("network", network);
                editor.putString("campaign", campaign);
                editor.putString("adgroup", adgroup);
                editor.putString("creative", creative);
                editor.putString("adid", adid);
                editor.commit();
            }
        });
        Adjust.onCreate(config);

        registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());
    }

    private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityPreStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPreResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            Adjust.onResume();
        }

        @Override
        public void onActivityPostResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPrePaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {
            Adjust.onPause();
        }

        @Override
        public void onActivityPostPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPreStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPreSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityPreDestroyed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostDestroyed(@NonNull Activity activity) {

        }

        //...
    }




}
