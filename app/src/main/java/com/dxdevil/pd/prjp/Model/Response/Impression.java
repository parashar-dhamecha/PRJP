package com.dxdevil.pd.prjp.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Impression {

    @SerializedName("imageBytes")
    @Expose
    public String imageBytes;
    @SerializedName("signatureType")
    @Expose
    public Integer signatureType;

    /**
     * No args constructor for use in serialization
     *
     */
    public Impression() {
    }

    /**
     *
     * @param signatureType
     * @param imageBytes
     */
    public Impression(String imageBytes, Integer signatureType) {
        super();
        this.imageBytes = imageBytes;
        this.signatureType = signatureType;
    }

}
