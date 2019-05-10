package com.dxdevil.pd.prjp.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateDatum {

    @SerializedName("documentId")
    @Expose
    public String documentId;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateDatum() {
    }

    /**
     *
     * @param documentId
     */
    public CreateDatum(String documentId) {
        super();
        this.documentId = documentId;
    }

}