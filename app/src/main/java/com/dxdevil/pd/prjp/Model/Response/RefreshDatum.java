package com.dxdevil.pd.prjp.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefreshDatum {

    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("refreshToken")
    @Expose
    public String refreshToken;

    /**
     * No args constructor for use in serialization
     *
     */
    public RefreshDatum() {
    }

    /**
     *
     * @param token
     * @param refreshToken
     */
    public RefreshDatum(String token, String refreshToken) {
        super();
        this.token = token;
        this.refreshToken = refreshToken;
    }

}
