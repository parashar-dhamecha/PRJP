package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Boolean> data = null;
    @SerializedName("license")
    @Expose
    private Object license;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Boolean> getData() {
        return data;
    }

    public void setData(List<Boolean> data) {
        this.data = data;
    }

    public Object getLicense() {
        return license;
    }

    public void setLicense(Object license) {
        this.license = license;
    }

}