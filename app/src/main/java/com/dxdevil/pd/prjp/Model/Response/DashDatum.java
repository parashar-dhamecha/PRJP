package com.dxdevil.pd.prjp.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashDatum {

    @SerializedName("awaitingMySign")
    @Expose
    private Integer awaitingMySign;
    @SerializedName("awaitingOthers")
    @Expose
    private Integer awaitingOthers;
    @SerializedName("completed")
    @Expose
    private Integer completed;
    @SerializedName("expireSoon")
    @Expose
    private Integer expireSoon;

    public Integer getAwaitingMySign() {
        return awaitingMySign;
    }

    public void setAwaitingMySign(Integer awaitingMySign) {
        this.awaitingMySign = awaitingMySign;
    }

    public Integer getAwaitingOthers() {
        return awaitingOthers;
    }

    public void setAwaitingOthers(Integer awaitingOthers) {
        this.awaitingOthers = awaitingOthers;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getExpireSoon() {
        return expireSoon;
    }

    public void setExpireSoon(Integer expireSoon) {
        this.expireSoon = expireSoon;
    }

}