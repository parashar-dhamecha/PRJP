
package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;

import com.dxdevil.pd.prjp.Model.Response.FeaturesSupported;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class License {

    @SerializedName("acknowledgementMessage")
    @Expose
    private Object acknowledgementMessage;
    @SerializedName("informationMessage")
    @Expose
    private Object informationMessage;
    @SerializedName("displayRedRibbon")
    @Expose
    private Object displayRedRibbon;
    @SerializedName("featuresSupported")
    @Expose
    private List<FeaturesSupported> featuresSupported = null;
    @SerializedName("supportedFileTypes")
    @Expose
    private String supportedFileTypes;
    @SerializedName("expired")
    @Expose
    private Boolean expired;

    public Object getAcknowledgementMessage() {
        return acknowledgementMessage;
    }

    public void setAcknowledgementMessage(Object acknowledgementMessage) {
        this.acknowledgementMessage = acknowledgementMessage;
    }

    public Object getInformationMessage() {
        return informationMessage;
    }

    public void setInformationMessage(Object informationMessage) {
        this.informationMessage = informationMessage;
    }

    public Object getDisplayRedRibbon() {
        return displayRedRibbon;
    }

    public void setDisplayRedRibbon(Object displayRedRibbon) {
        this.displayRedRibbon = displayRedRibbon;
    }

    public List<FeaturesSupported> getFeaturesSupported() {
        return featuresSupported;
    }

    public void setFeaturesSupported(List<FeaturesSupported> featuresSupported) {
        this.featuresSupported = featuresSupported;
    }

    public String getSupportedFileTypes() {
        return supportedFileTypes;
    }

    public void setSupportedFileTypes(String supportedFileTypes) {
        this.supportedFileTypes = supportedFileTypes;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

}
