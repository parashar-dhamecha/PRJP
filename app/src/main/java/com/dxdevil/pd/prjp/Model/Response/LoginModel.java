
package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<LoginDatum> data = null;
    @SerializedName("license")
    @Expose
    private License license;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LoginDatum> getData() {
        return data;
    }

    public void setData(List<LoginDatum> data) {
        this.data = data;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

}
