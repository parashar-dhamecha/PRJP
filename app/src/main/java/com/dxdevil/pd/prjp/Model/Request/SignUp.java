
package com.dxdevil.pd.prjp.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUp {

    public SignUp(String fn,
                  String ln,
                  String em,String Pass,String Cpass,String jt,String cn,String cid,String phno,String lat ,String lon,String agent,String tofset)
    {
        firstName=fn;
        lastName=ln;
        email=em;
        password=Pass;
        confirmPassword=Cpass;
        jobTitle=jt;
        countryId=cid;
        phoneNumber =phno;
        userLatitude=lat;
        userLongitude=lon;
        userAgent=agent;
        userTimeZoneOffSet=tofset;
    }

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("confirmPassword")
    @Expose
    private String confirmPassword;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("countryId")
    @Expose
    private String countryId;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("userLatitude")
    @Expose
    private String userLatitude;
    @SerializedName("userLongitude")
    @Expose
    private String userLongitude;
    @SerializedName("userAgent")
    @Expose
    private String userAgent;
    @SerializedName("userTimeZoneOffSet")
    @Expose
    private String userTimeZoneOffSet;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(String userLatitude) {
        this.userLatitude = userLatitude;
    }

    public String getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(String userLongitude) {
        this.userLongitude = userLongitude;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserTimeZoneOffSet() {
        return userTimeZoneOffSet;
    }

    public void setUserTimeZoneOffSet(String userTimeZoneOffSet) {
        this.userTimeZoneOffSet = userTimeZoneOffSet;
    }

}
