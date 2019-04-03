package com.dxdevil.pd.prjp.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Otp {
    public Otp(String o){
        otpSentCount =o;
    }

    @SerializedName("otpSentCount")
    @Expose
    private String otpSentCount;

    public String getOtpSentCount() {
        return otpSentCount;
    }

    public void setOtpSentCount(String otpSentCount) {
        this.otpSentCount = otpSentCount;
    }

}
