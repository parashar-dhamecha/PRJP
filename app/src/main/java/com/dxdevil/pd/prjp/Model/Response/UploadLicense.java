package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadLicense {

    @SerializedName("acknowledgementMessage")
    @Expose
    public Object acknowledgementMessage;
    @SerializedName("informationMessage")
    @Expose
    public Object informationMessage;
    @SerializedName("displayRedRibbon")
    @Expose
    public Object displayRedRibbon;
    @SerializedName("featuresSupported")
    @Expose
    public List<UploadFeaturesSupported> featuresSupported;
    @SerializedName("supportedFileTypes")
    @Expose
    public String supportedFileTypes;
    @SerializedName("expired")
    @Expose
    public Boolean expired;

    /**
     * No args constructor for use in serialization
     *
     */
    public UploadLicense() {
    }

    /**
     *
     * @param supportedFileTypes
     * @param expired
     * @param informationMessage
     * @param displayRedRibbon
     * @param featuresSupported
     * @param acknowledgementMessage
     */
    public UploadLicense(Object acknowledgementMessage, Object informationMessage, Object displayRedRibbon, List<UploadFeaturesSupported> featuresSupported, String supportedFileTypes, Boolean expired) {
        super();
        this.acknowledgementMessage = acknowledgementMessage;
        this.informationMessage = informationMessage;
        this.displayRedRibbon = displayRedRibbon;
        this.featuresSupported = featuresSupported;
        this.supportedFileTypes = supportedFileTypes;
        this.expired = expired;
    }

}