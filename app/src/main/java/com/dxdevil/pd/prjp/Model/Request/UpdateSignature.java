package com.dxdevil.pd.prjp.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateSignature {

    @SerializedName("signatureType")
    @Expose
    public Integer signatureType;
    @SerializedName("ImageBytes")
    @Expose
    public String imageBytes;

    public UpdateSignature() {
    }

    public UpdateSignature(Integer signatureType, String imageBytes) {
        super();
        this.signatureType = signatureType;
        this.imageBytes = imageBytes;
    }

}