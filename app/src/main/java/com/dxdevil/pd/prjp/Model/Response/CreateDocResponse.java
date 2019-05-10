package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateDocResponse {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    @SerializedName("license")
    @Expose
    public Object license;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateDocResponse() {
    }

    /**
     *
     * @param message
     * @param data
     * @param license
     */
    public CreateDocResponse(String message, List<Datum> data, Object license) {
        super();
        this.message = message;
        this.data = data;
        this.license = license;
    }

}


class Datum {

    @SerializedName("documentId")
    @Expose
    public String documentId;

    /**
     * No args constructor for use in serialization
     */
    public Datum() {
    }

    /**
     * @param documentId
     */
    public Datum(String documentId) {
        super();
        this.documentId = documentId;
    }
}