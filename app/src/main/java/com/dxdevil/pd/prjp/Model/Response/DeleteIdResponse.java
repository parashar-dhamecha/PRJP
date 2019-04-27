package com.dxdevil.pd.prjp.Model.Response;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteIdResponse {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<Boolean> data = null;
    @SerializedName("license")
    @Expose
    public Object license;

    /**
     * No args constructor for use in serialization
     *
     */
    public DeleteIdResponse() {
    }

    /**
     *
     * @param message
     * @param data
     * @param license
     */
    public DeleteIdResponse(String message, List<Boolean> data, Object license) {
        super();
        this.message = message;
        this.data = data;
        this.license = license;
    }

}