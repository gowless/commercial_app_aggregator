package com.orkotkreditru.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.orkotkreditru.MainClass;
import com.orkotkreditru.R;
import com.orkotkreditru.models.get.Data;
import com.orkotkreditru.network.Initializator;
import com.orkotkreditru.network.Interface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BeforeMainActivity extends AppCompatActivity {

    //info tab icon declaring
    ImageView infoTabIcon;

    //img non-ithernet
    ImageView imageView;

    //text non-ithernet
    TextView textView;




    public static Boolean isEmpty;
    SharedPreferences prefs = null;
    public static int numberOfTabs;
    public static String first, second, third;

    //progress
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_main);

        //info tab icon init
        infoTabIcon = findViewById(R.id.info_tab_icon);

        infoTabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting info activity
                startActivity(new Intent(BeforeMainActivity.this, InfoActivity.class));
            }
        });

        progressBar = findViewById(R.id.progressBar3);

        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet);
        imageView = findViewById(R.id.non_Ithernet);


        //check for network connection
        if (isNetworkAvailable()) {

        } else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            infoTabIcon.setVisibility(View.GONE);
        }
        getJsonData();
    }

    //setting to get json file and parse it to models
    public void getJsonData() {

        Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
        Call<Data> call = apiInterfaceCount.getData(MainClass.APP_ID);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                isEmpty = response.body().getCategories().isEmpty();
                if (isEmpty) {
                    progressBar.setIndeterminate(false);
                    nonCategoriesStart();
                } else {
                    progressBar.setIndeterminate(false);
                    mainStart();
                }


            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {

            }
        });

    }

    //starts NonCategory
    private void nonCategoriesStart() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(BeforeMainActivity.this, NonCategoriesActivity.class));
                finish();
            }
        }, 500);
    }

    private void mainStart() {
        startActivity(new Intent(BeforeMainActivity.this, MainActivity.class));
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}