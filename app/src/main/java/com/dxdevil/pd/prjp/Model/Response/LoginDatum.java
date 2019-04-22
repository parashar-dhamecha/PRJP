
package com.dxdevil.pd.prjp.Model.Response;
// This class is for log in
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDatum {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("userName")
    @Expose
    private Object userName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("profileByte")
    @Expose
    private String profileByte;
    @SerializedName("isProfileImage")
    @Expose
    private Boolean isProfileImage;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("documentId")
    @Expose
    private Object documentId;
    @SerializedName("emailConfirmed")
    @Expose
    private Boolean emailConfirmed;
    @SerializedName("userTimeZone")
    @Expose
    private String userTimeZone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getProfileByte() {
        return profileByte;
    }

    public void setProfileByte(String profileByte) {
        this.profileByte = profileByte;
    }

    public Boolean getIsProfileImage() {
        return isProfileImage;
    }

    public void setIsProfileImage(Boolean isProfileImage) {
        this.isProfileImage = isProfileImage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Object getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Object documentId) {
        this.documentId = documentId;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public String getUserTimeZone() {
        return userTimeZone;
    }

    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

}

