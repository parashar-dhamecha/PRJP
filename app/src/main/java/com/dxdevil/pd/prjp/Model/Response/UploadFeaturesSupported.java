package com.dxdevil.pd.prjp.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadFeaturesSupported {

    @SerializedName("Id")
    @Expose
    public String id;
    @SerializedName("featureName")
    @Expose
    public String featureName;
    @SerializedName("placeholderExpired")
    @Expose
    public Boolean placeholderExpired;

    /**
     * No args constructor for use in serialization
     *
     */
    public UploadFeaturesSupported() {
    }

    /**
     *
     * @param placeholderExpired
     * @param id
     * @param featureName
     */
    public UploadFeaturesSupported(String id, String featureName, Boolean placeholderExpired) {
        super();
        this.id = id;
        this.featureName = featureName;
        this.placeholderExpired = placeholderExpired;
    }

}