package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadfileModel {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<UploadDatum> data = null;
    @SerializedName("license")
    @Expose
    public UploadLicense license;

    /**
     * No args constructor for use in serialization
     *
     */
    public UploadfileModel() {
    }

    /**
     *
     * @param message
     * @param data
     * @param license
     */
    public UploadfileModel(String message, List<UploadDatum> data, UploadLicense license) {
        super();
        this.message = message;
        this.data = data;
        this.license = license;
    }

}