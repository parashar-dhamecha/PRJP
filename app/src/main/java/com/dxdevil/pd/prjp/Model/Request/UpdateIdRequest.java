package com.dxdevil.pd.prjp.Model.Request;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateIdRequest {

    @SerializedName("id")
    @Expose
    public String id;
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

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateIdRequest() {
    }

    /**
     *
     * @param countryId
     * @param id
     * @param email
     * @param name
     * @param mobileNumber
     * @param jobDescription
     * @param jobTitle
     */
    public UpdateIdRequest(String id, String name, String email, int countryId, String mobileNumber, String jobTitle, String jobDescription) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.countryId = countryId;
        this.mobileNumber = mobileNumber;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
    }

}