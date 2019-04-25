package com.dxdevil.pd.prjp.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadFeatures {

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
    public UploadFeatures() {
    }

    /**
     *
     * @param placeholderExpired
     * @param id
     * @param featureName
     */
    public UploadFeatures(String id, String featureName, Boolean placeholderExpired) {
        super();
        this.id = id;
        this.featureName = featureName;
        this.placeholderExpired = placeholderExpired;
    }

}