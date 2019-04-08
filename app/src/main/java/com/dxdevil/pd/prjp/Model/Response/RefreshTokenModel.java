package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;

import com.dxdevil.pd.prjp.Model.Request.RefreshToken;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefreshTokenModel {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<RefreshDatum> data = null;
    @SerializedName("license")
    @Expose
    public Object license;

    /**
     * No args constructor for use in serialization
     *
     */
    public RefreshTokenModel() {
    }

    /**
     *
     * @param message
     * @param data
     * @param license
     */
    public RefreshTokenModel(String message, List<RefreshDatum> data, Object license) {
        super();
        this.message = message;
        this.data = data;
        this.license = license;
    }

}
