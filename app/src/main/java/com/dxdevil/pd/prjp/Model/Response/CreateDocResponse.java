package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CreateDocResponse {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<CreateDatum> data;
    @SerializedName("license")
    @Expose
    public String license;

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
    public CreateDocResponse(String message, List<CreateDatum> data, String license) {
        super();
        this.message = message;
        this.data = data;
        this.license = license;
    }

}


