package com.orkotkreditru.models.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainExample {

    @SerializedName("geo")
    @Expose
    private String geo;
    @SerializedName("cpa")
    @Expose
    private String cpa;
    @SerializedName("app")
    @Expose
    private String app;
    @SerializedName("offer_name")
    @Expose
    private String offerName;
    @SerializedName("offer_id")
    @Expose
    private Integer offerId;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("advertising_id")
    @Expose
    private String advertisingId;
    @SerializedName("click_date")
    @Expose
    private String clickDate;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("chanel")
    @Expose
    private String chanel;
    @SerializedName("ad_id")
    @Expose
    private String adId;
    @SerializedName("campaign")
    @Expose
    private String campaign;
    @SerializedName("campaign_id")
    @Expose
    private String campaignId;
    @SerializedName("adset")
    @Expose
    private String adset;
    @SerializedName("adset_id")
    @Expose
    private String adsetId;
    @SerializedName("adgroup")
    @Expose
    private String adgroup;
    @SerializedName("adgroup_id")
    @Expose
    private String adgroupId;

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getCpa() {
        return cpa;
    }

    public void setCpa(String cpa) {
        this.cpa = cpa;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAdvertisingId() {
        return advertisingId;
    }

    public void setAdvertisingId(String advertisingId) {
        this.advertisingId = advertisingId;
    }

    public String getClickDate() {
        return clickDate;
    }

    public void setClickDate(String clickDate) {
        this.clickDate = clickDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChanel() {
        return chanel;
    }

    public void setChanel(String chanel) {
        this.chanel = chanel;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getAdset() {
        return adset;
    }

    public void setAdset(String adset) {
        this.adset = adset;
    }

    public String getAdsetId() {
        return adsetId;
    }

    public void setAdsetId(String adsetId) {
        this.adsetId = adsetId;
    }

    public String getAdgroup() {
        return adgroup;
    }

    public void setAdgroup(String adgroup) {
        this.adgroup = adgroup;
    }

    public String getAdgroupId() {
        return adgroupId;
    }

    public void setAdgroupId(String adgroupId) {
        this.adgroupId = adgroupId;
    }

}
