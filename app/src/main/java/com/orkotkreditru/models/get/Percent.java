package com.orkotkreditru.models.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Percent {

    @SerializedName("from")
    @Expose
    private Double from;
    @SerializedName("to")
    @Expose
    private Double to;

    public Double getFrom() {
        return from;
    }

    public void setFrom(Double from) {
        this.from = from;
    }

    public Double getTo() {
        return to;
    }

    public void setTo(Double to) {
        this.to = to;
    }

}
