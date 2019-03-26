
package com.dxdevil.pd.prjp.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeaturesSupported {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("featureName")
    @Expose
    private String featureName;
    @SerializedName("placeholderExpired")
    @Expose
    private Boolean placeholderExpired;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Boolean getPlaceholderExpired() {
        return placeholderExpired;
    }

    public void setPlaceholderExpired(Boolean placeholderExpired) {
        this.placeholderExpired = placeholderExpired;
    }

}
