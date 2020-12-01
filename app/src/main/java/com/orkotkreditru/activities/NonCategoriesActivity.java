package com.orkotkreditru.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
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
import com.orkotkreditru.adapters.NonCategoriesAllAdapter;
import com.orkotkreditru.models.get.Data;
import com.orkotkreditru.models.get.Liste;
import com.orkotkreditru.network.Initializator;
import com.orkotkreditru.network.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NonCategoriesActivity extends AppCompatActivity {

    public static String  tt, tn, net, cam, adg, cre;

    //context
    Context context;

    //recyclerview
    RecyclerView recyclerView;

    //progress bar
    ProgressBar progressBar;

    //info tab icon declaring
    ImageView infoTabIcon;

    //img non-ithernet
    ImageView imageView;

    //text non-ithernet
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_categories);

        SharedPreferences settings = getSharedPreferences("LOCAL", Context.MODE_PRIVATE);

        tt = settings.getString("trackerToken", "");
        tn = settings.getString("trackerName", "");
        net = settings.getString("network", "");
        cam = settings.getString("campaign", "");
        adg = settings.getString("adgroup", "");
        cre = settings.getString("creative", "");




        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet);
        imageView = findViewById(R.id.non_Ithernet);

        //recyclerview
        recyclerView = findViewById(R.id.recyclerView);

        //progress bar
        progressBar = findViewById(R.id.progressBar);



        //info tab icon init
        infoTabIcon = findViewById(R.id.info_tab_icon);

        //check for network connection
        if(isNetworkAvailable()){

        } else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            infoTabIcon.setVisibility(View.GONE);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        NonCategoriesAllAdapter recyclerAdapterAllMain = new NonCategoriesAllAdapter(getApplicationContext(), MainClass.listDataAll);
        recyclerAdapterAllMain.setDataList(MainClass.listDataAll);
        recyclerView.setAdapter(recyclerAdapterAllMain);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);


        //calling function of clicked tab info icon on top
        infoTabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting info activity
                startActivity(new Intent(NonCategoriesActivity.this,  InfoNonCategoryActivity.class));
            }
        });



    }




    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}