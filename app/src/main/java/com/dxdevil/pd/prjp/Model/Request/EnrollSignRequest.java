package com.dxdevil.pd.prjp.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnrollSignRequest {

    @SerializedName("signatureType")
    @Expose
    public Integer signatureType;
    @SerializedName("ImageBytes")
    @Expose
    public String imageBytes;

    /**
     * No args constructor for use in serialization
     *
     */
    public EnrollSignRequest() {
    }

    /**
     *
     * @param signatureType
     * @param imageBytes
     */
    public EnrollSignRequest(Integer signatureType, String imageBytes) {
        super();
        this.signatureType = signatureType;
        this.imageBytes = imageBytes;
    }

}