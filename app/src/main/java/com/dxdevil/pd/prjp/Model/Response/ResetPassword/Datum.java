
package com.dxdevil.pd.prjp.Model.Response.ResetPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

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
