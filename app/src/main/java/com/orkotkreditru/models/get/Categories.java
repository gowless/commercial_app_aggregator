package com.orkotkreditru.models.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories {

    @SerializedName("all")
    @Expose
    private Boolean all;
    @SerializedName("zero")
    @Expose
    private Boolean zero;
    @SerializedName("badCreditHistory")
    @Expose
    private Boolean badCreditHistory;

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

    public Boolean getZero() {
        return zero;
    }

    public void setZero(Boolean zero) {
        this.zero = zero;
    }

    public Boolean getBadCreditHistory() {
        return badCreditHistory;
    }

    public void setBadCreditHistory(Boolean badCreditHistory) {
        this.badCreditHistory = badCreditHistory;
    }

}
