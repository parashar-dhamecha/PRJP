package com.dxdevil.pd.prjp.Model.Response;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profiledatum {

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
    public Object description;
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
    @SerializedName("profileByte")
    @Expose
    public String profileByte;
    @SerializedName("impressions")
    @Expose
    public List<Impression> impressions = null;
    @SerializedName("userTimeZone")
    @Expose
    public String userTimeZone;

    /**
     * No args constructor for use in serialization
     *
     */
    public Profiledatum() {
    }

    /**
     *
     * @param countryId
     * @param impressions
     * @param lastName
     * @param isProfileImage
     * @param organization
     * @param phoneNumber
     * @param email
     * @param description
     * @param userId
     * @param gender
     * @param birthDate
     * @param userTimeZone
     * @param profileByte
     * @param firstName
     * @param jobTitle
     */
    public Profiledatum(String userId, String email, String firstName, String lastName, Object description, Integer gender, Integer countryId, String jobTitle, String organization, String phoneNumber, String birthDate, Boolean isProfileImage, String profileByte, List<Impression> impressions, String userTimeZone) {
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
        this.profileByte = profileByte;
        this.impressions = impressions;
        this.userTimeZone = userTimeZone;
    }

}

