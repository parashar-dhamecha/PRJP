package com.dxdevil.pd.prjp.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfile {

    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("gender")
    @Expose
    public Integer gender;
    @SerializedName("countryId")
    @Expose
    public Integer countryId;
    @SerializedName("jobTitle")
    @Expose
    public String jobTitle;
    @SerializedName("organization")
    @Expose
    public String organization;
    @SerializedName("phoneNumber")
    @Expose
    public String phoneNumber;
    @SerializedName("birthDate")
    @Expose
    public String birthDate;
    @SerializedName("isProfileImage")
    @Expose
    public Boolean isProfileImage;
    @SerializedName("userTimeZone")
    @Expose
    public String userTimeZone;
    @SerializedName("profileByte")
    @Expose
    public String profileByte;


    public UpdateProfile() {
    }


    public UpdateProfile(String userId, String email, String firstName, String lastName, String description, Integer gender, Integer countryId, String jobTitle, String organization, String phoneNumber, String birthDate, Boolean isProfileImage, String userTimeZone, String profileByte) {
        super();
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.gender = gender;
        this.countryId = countryId;
        this.jobTitle = jobTitle;
        this.organization = organization;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.isProfileImage = isProfileImage;
        this.userTimeZone = userTimeZone;
        this.profileByte = profileByte;
    }

}