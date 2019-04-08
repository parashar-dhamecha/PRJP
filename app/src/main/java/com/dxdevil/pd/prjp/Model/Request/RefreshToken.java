package com.dxdevil.pd.prjp.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefreshToken {

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
    public RefreshToken() {
    }

    /**
     *
     * @param token
     * @param refreshToken
     */
    public RefreshToken(String token, String refreshToken) {
        super();
        this.token = token;
        this.refreshToken = refreshToken;
    }

}