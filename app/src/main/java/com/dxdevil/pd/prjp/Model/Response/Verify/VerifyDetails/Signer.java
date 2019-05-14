
package com.dxdevil.pd.prjp.Model.Verify.VerifyDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signer {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private Object lastName;
    @SerializedName("profileShortName")
    @Expose
    private String profileShortName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("userRole")
    @Expose
    private Integer userRole;
    @SerializedName("isSigned")
    @Expose
    private Boolean isSigned;
    @SerializedName("signType")
    @Expose
    private Integer signType;
    @SerializedName("signButtonText")
    @Expose
    private Object signButtonText;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public String getProfileShortName() {
        return profileShortName;
    }

    public void setProfileShortName(String profileShortName) {
        this.profileShortName = profileShortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Boolean getIsSigned() {
        return isSigned;
    }

    public void setIsSigned(Boolean isSigned) {
        this.isSigned = isSigned;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public Object getSignButtonText() {
        return signButtonText;
    }

    public void setSignButtonText(Object signButtonText) {
        this.signButtonText = signButtonText;
    }

}
