package com.orkotkreditru.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//initializing connection to main server
public class Initializator {

    //main URL for connection
    private static final String BASE_URL = "http://api.orbitrush.com/";

    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
