package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateSignatureModel {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<String> data = null;
    @SerializedName("license")
    @Expose
    public Object license;

    public UpdateSignatureModel() {
    }

    public UpdateSignatureModel(String message, List<String> data, Object license) {
        super();
        this.message = message;
        this.data = data;
        this.license = license;
    }

}
