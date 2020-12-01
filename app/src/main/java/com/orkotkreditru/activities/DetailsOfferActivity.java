package com.orkotkreditru.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.orkotkreditru.MainClass;
import com.orkotkreditru.R;
import com.orkotkreditru.models.get.Data;
import com.orkotkreditru.network.Initializator;
import com.orkotkreditru.network.Interface;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsOfferActivity extends AppCompatActivity {

    //progressbar declaring
    ProgressBar progressBar;

    //scroll view declaring
    ScrollView scrollView;

    //declaring toolbar
    Toolbar toolbar;

    //string position number
    Integer position;

    //string textName
    TextView textName, textAdress, textNumber, textMail, textSite, textPercent, textLicense, textTerms, textFistCredit, textNextCredit, textYUR;

    //image of offer
    ImageView imageView;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_offer);

        //initializing toolbar
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        //initializing views
        textTerms = findViewById(R.id.textTerms);
        progressBar = findViewById(R.id.progressBar4);
        scrollView = findViewById(R.id.scrollView2);
        imageView = findViewById(R.id.imageOffer);
        textName = findViewById(R.id.textName);
        textAdress = findViewById(R.id.textAdress);
        textNumber = findViewById(R.id.textNumber);
        textMail = findViewById(R.id.textMail);
        textSite = findViewById(R.id.textSite);
        textPercent = findViewById(R.id.textPercent);
        textLicense = findViewById(R.id.textLicense);
        textFistCredit = findViewById(R.id.textFirstCredit);
        textNextCredit = findViewById(R.id.textNextCredit);
        textYUR = findViewById(R.id.textYUR);

        //getting intent
        Intent intent = getIntent();
        //getting extra number from cloak adapter
        position = intent.getIntExtra("position", 0);
        //starting method
        getJsonData();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsOfferActivity.this, CloakActivity.class));
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.setVisibility(View.VISIBLE);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.GONE);
            }
        }, 1500);

        textTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsOfferActivity.this, InfoDetailsActivity.class);
                intent.putExtra("check", 1);
                startActivity(intent);
            }
        });

    }


    //getting jsonData from server
    public void getJsonData() {
        Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
        Call<Data> call = apiInterfaceCount.getData(MainClass.APP_ID);
        call.enqueue(new Callback<Data>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {

                //if response successful
                if (response.isSuccessful()) {

                    //parsing data to views
                    String URL = response.body().getListe().get(position).getImg();
                    textName.setText(response.body().getListe().get(position).getOfferName().toUpperCase());
                    textAdress.setText("• " + response.body().getListe().get(position).getOfferName());
                    textNumber.setText("• " + response.body().getListe().get(position).getDetail().getPhone());
                    textMail.setText("• " + response.body().getListe().get(position).getDetail().getEmail());
                    textSite.setText("• " + response.body().getListe().get(position).getDetail().getSite());
                    textPercent.setText("• " + response.body().getListe().get(position).getDetail().getApr().toString() + "%");
                    textLicense.setText("• " + response.body().getListe().get(position).getDetail().getLicense());
                    textFistCredit.setText("• " + response.body().getListe().get(position).getAmount().getFrom() + "₴");
                    textNextCredit.setText("• " + response.body().getListe().get(position).getAmount().getTo() + "₴");
                    textYUR.setText("• " + response.body().getListe().get(position).getDetail().getAddress());

                    //parsing image to imageview
                    Glide.with(DetailsOfferActivity.this)
                            .load(URL)
                            .centerInside()
                            .into(imageView);


                }
            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DetailsOfferActivity.this, CloakActivity.class));
    }
}