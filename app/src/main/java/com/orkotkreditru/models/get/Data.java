package com.orkotkreditru.models.get;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("appId")
    @Expose
    private String appId;
    @SerializedName("list")
    @Expose
    private List<Liste> liste;
    @SerializedName("categories")
    @Expose
    private List<Category> categories;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<Liste> getListe() {
        return liste;
    }

    public void setListe(List<Liste> liste) {
        this.liste = liste;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
