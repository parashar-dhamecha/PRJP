package com.dxdevil.pd.prjp.Model.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class UpdateIdResponse {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<UpdateIdDatum> data = null;
    @SerializedName("license")
    @Expose
    public Object license;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateIdResponse() {
    }

    /**
     *
     * @param message
     * @param data
     * @param license
     */
    public UpdateIdResponse(String message, List<UpdateIdDatum> data, Object license) {
        super();
        this.message = message;
        this.data = data;
        this.license = license;
    }

}