package com.dxdevil.pd.prjp.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.jar.Attributes;

public class AddContactRequest {

    public AddContactRequest(String name, String Em, int cid, String mn, String Jt, String Jd)
    {
        this.name=name ;
        this.email=Em;
        this.countryId=cid;
        this.mobileNumber=mn;
        this.jobTitle=Jt;
        this.jobDescription=Jd;



    }




    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("countryId")
    @Expose
    private Integer countryId;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddContactRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddContactRequest withEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public AddContactRequest withCountryId(Integer countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public AddContactRequest withMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public AddContactRequest withJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public AddContactRequest withJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
        return this;
    }

}
