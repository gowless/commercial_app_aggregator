package com.orkotkreditru.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orkotkreditru.MainClass;
import com.orkotkreditru.R;
import com.orkotkreditru.adapters.AdapterCloak;
import com.orkotkreditru.models.get.Data;
import com.orkotkreditru.models.get.Liste;
import com.orkotkreditru.network.Initializator;
import com.orkotkreditru.network.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CloakActivity extends AppCompatActivity {

    ProgressBar progressBar;
    List<Liste> listData;
    RecyclerView recyclerView;

    //img non-ithernet
    ImageView imageView;

    //text non-ithernet
    TextView textView;

    //info tab icon declaring
    ImageView infoTabIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloak);

        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet);
        imageView = findViewById(R.id.non_Ithernet);

        //info tab icon init
        infoTabIcon = findViewById(R.id.info_tab_icon);

        progressBar = findViewById(R.id.progressBar2);

        //check for network connection
        if (isNetworkAvailable()) {

        } else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            infoTabIcon.setVisibility(View.GONE);
        }


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        getJsonData();
        getCarrier();


        infoTabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting info activity
                startActivity(new Intent(CloakActivity.this, InfoDetailsActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public void getJsonData() {
        Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
        Call<Data> call = apiInterfaceCount.getData(MainClass.APP_ID);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                // getting List of Liste objects
                assert response.body() != null;
                listData = response.body().getListe();

                AdapterCloak recyclerAdapterCloakList = new AdapterCloak(getApplicationContext(), MainClass.listDataAll);
                recyclerAdapterCloakList.setDataList(MainClass.listDataAll);
                recyclerView.setAdapter(recyclerAdapterCloakList);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {

            }
        });
    }

    public void getCarrier() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String iso = manager.getSimCountryIso();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}