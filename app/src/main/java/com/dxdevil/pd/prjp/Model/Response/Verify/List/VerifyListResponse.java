
package com.dxdevil.pd.prjp.Model.Response.Verify.List;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyListResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("license")
    @Expose
    private Object license;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Object getLicense() {
        return license;
    }

    public void setLicense(Object license) {
        this.license = license;
    }

}
