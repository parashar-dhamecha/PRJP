package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileModel {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<Profiledatum> data = null;
    @SerializedName("license")
    @Expose
    public Object license;


    public ProfileModel() {
    }


    public ProfileModel(String message, List<Profiledatum> data, Object license) {
        super();
        this.message = message;
        this.data = data;
        this.license = license;
    }

}
