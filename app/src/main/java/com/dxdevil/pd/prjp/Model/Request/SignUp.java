
package com.dxdevil.pd.prjp.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUp {

     public SignUp
             (String FirstName,
              String LastName,
              String Email,
              String Password,
              String Confirm,
              String JobTitle,
              String CompanyName,
               int CountryId,
              String MobileNo,
              String Latitude,
              String Longitude,
              String useragent,
              String Timezone,
              String TimeZoneOffset)

     {
         this.firstName = FirstName;
         this.lastName= LastName;
         this.email = Email;
         this.phoneNumber = MobileNo;
         this.password = Password;
         this.confirmPassword =Confirm;
         this.jobTitle=JobTitle;
         this.companyName = CompanyName;
         this.countryId = CountryId;
         this.userLatitude =Latitude;
         this.userLongitude =Longitude;
         this.userTimeZone =Timezone;
         this.userAgent = useragent;
         this.userTimeZoneOffSet=TimeZoneOffset;

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
    private Integer countryId;
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
    @SerializedName("userTimeZone")
    @Expose
    private String userTimeZone;
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

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
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

    public String getUserTimeZone() {
        return userTimeZone;
    }

    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    public String getUserTimeZoneOffSet() {
        return userTimeZoneOffSet;
    }

    public void setUserTimeZoneOffSet(String userTimeZoneOffSet) {
        this.userTimeZoneOffSet = userTimeZoneOffSet;
    }

}
