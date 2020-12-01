package com.orkotkreditru.models.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Liste {

    @SerializedName("percent")
    @Expose
    private Percent percent;
    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("term")
    @Expose
    private Term term;
    @SerializedName("detail")
    @Expose
    private Detail detail;
    @SerializedName("push")
    @Expose
    private Push push;
    @SerializedName("categories")
    @Expose
    private Categories categories;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("offer_name")
    @Expose
    private String offerName;
    @SerializedName("offer_id")
    @Expose
    private Integer offerId;
    @SerializedName("cpa")
    @Expose
    private String cpa;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("top")
    @Expose
    private Boolean top;
    @SerializedName("isHidden")
    @Expose
    private Boolean isHidden;


    public Percent getPercent() {
        return percent;
    }

    public void setPercent(Percent percent) {
        this.percent = percent;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public Push getPush() {
        return push;
    }

    public void setPush(Push push) {
        this.push = push;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCpa() {
        return cpa;
    }

    public void setCpa(String cpa) {
        this.cpa = cpa;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        this.isHidden = hidden;
    }

}
