package com.dxdevil.pd.prjp.Model.Response.ChangePassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordDatum {

    @SerializedName("redirectToLogin")
    @Expose
    private Boolean redirectToLogin;

    public Boolean getRedirectToLogin() {
        return redirectToLogin;
    }

    public void setRedirectToLogin(Boolean redirectToLogin) {
        this.redirectToLogin = redirectToLogin;
    }

}