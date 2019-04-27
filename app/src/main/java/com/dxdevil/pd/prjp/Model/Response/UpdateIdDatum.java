package com.dxdevil.pd.prjp.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateIdDatum {

    @SerializedName("Id")
    @Expose
    public String id;
    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("countryId")
    @Expose
    public int countryId;
    @SerializedName("mobileNumber")
    @Expose
    public String mobileNumber;
    @SerializedName("jobTitle")
    @Expose
    public String jobTitle;
    @SerializedName("jobDescription")
    @Expose
    public String jobDescription;
    @SerializedName("shortName")
    @Expose
    public String shortName;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateIdDatum() {
    }

    /**
     *
     * @param countryId
     * @param id
     * @param email
     * @param name
     * @param userId
     * @param shortName
     * @param mobileNumber
     * @param jobDescription
     * @param jobTitle
     */
    public UpdateIdDatum(String id, String userId, String name, String email, int countryId, String mobileNumber, String jobTitle, String jobDescription, String shortName) {
        super();
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.countryId = countryId;
        this.mobileNumber = mobileNumber;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.shortName = shortName;
    }

}
