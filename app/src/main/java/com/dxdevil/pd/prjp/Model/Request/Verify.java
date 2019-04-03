package com.dxdevil.pd.prjp.Model.Request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Verify {

    @SerializedName("otp")
    @Expose
    public String otp;

    /**
     * No args constructor for use in serialization
     *
     */
    public Verify() {
    }

    /**
     *
     * @param otp
     */
    public Verify(String otp) {
        super();
        this.otp = otp;
    }

}
