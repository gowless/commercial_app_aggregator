package com.orkotkreditru.network;



import com.orkotkreditru.models.get.Data;
import com.orkotkreditru.models.post.MainExample;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Interface {

    //query for offers
    @GET("offers")
    Call<Data> getData(
            @Query("appId") String appId
    );


    //post for info send
    @POST("stats")
    Call<MainExample> putOfferData(
            @Query("geo") String geo,
            @Query("cpa") String cpa,
            @Query("start_date") String start_date,
            @Query("end_date") String end_date
    );

    //main post json data to server
    @POST("offer-click")
    Call<MainExample> putMainOfferData(
            @Query("geo") String geo,
            @Query("cpa") String cpa,
            @Query("app") String app,
            @Query("offer_name") String offer_name,
            @Query("offer_id") String offer_id,
            @Query("client_id") String client_id,
            @Query("advertising_id") String advertising_id,
            @Query("click_date") String click_date,
            @Query("source") String source,
            @Query("chanel") String chanel,
            @Query("ad_id") String ad_id,
            @Query("campaign") String campaign,
            @Query("campaign_id") String campaign_id,
            @Query("adset") String adset,
            @Query("adset_id") String adset_id,
            @Query("adgroup") String adgroup,
            @Query("adgroup_id") String adgroup_id
    );

    @FormUrlEncoded
    @POST("offer-click")
    Call<MainExample> putMainDataField(
            @Field("geo") String geo,
            @Field("cpa") String cpa,
            @Field("app") String app,
            @Field("offer_name") String offer_name,
            @Field("offer_id") String offer_id,
            @Field("client_id") String client_id,
            @Field("advertising_id") String advertising_id,
            @Field("click_date") String click_date,
            @Field("source") String source,
            @Field("chanel") String chanel,
            @Field("campaign") String campaign,
            @Field("campaign_id") String campaign_id,
            @Field("adset") String adset,
            @Field("adset_id") String adset_id,
            @Field("adgroup") String adgroup,
            @Field("adgroup_id") String adgroup_id
    );


}
