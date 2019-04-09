
package com.dxdevil.pd.prjp.Model.Response.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentHistory {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("activityType")
    @Expose
    private Integer activityType;
    @SerializedName("signType")
    @Expose
    private Integer signType;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("profileShortName")
    @Expose
    private String profileShortName;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("userAgent")
    @Expose
    private String userAgent;
    @SerializedName("ipAddress")
    @Expose
    private String ipAddress;
    @SerializedName("profileImage")
    @Expose
    private Object profileImage;
    @SerializedName("profileImageAvailable")
    @Expose
    private Boolean profileImageAvailable;
    @SerializedName("logTime")
    @Expose
    private Object logTime;
    @SerializedName("historyText")
    @Expose
    private String historyText;
    @SerializedName("declineReason")
    @Expose
    private Object declineReason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfileShortName() {
        return profileShortName;
    }

    public void setProfileShortName(String profileShortName) {
        this.profileShortName = profileShortName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Object getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Object profileImage) {
        this.profileImage = profileImage;
    }

    public Boolean getProfileImageAvailable() {
        return profileImageAvailable;
    }

    public void setProfileImageAvailable(Boolean profileImageAvailable) {
        this.profileImageAvailable = profileImageAvailable;
    }

    public Object getLogTime() {
        return logTime;
    }

    public void setLogTime(Object logTime) {
        this.logTime = logTime;
    }

    public String getHistoryText() {
        return historyText;
    }

    public void setHistoryText(String historyText) {
        this.historyText = historyText;
    }

    public Object getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(Object declineReason) {
        this.declineReason = declineReason;
    }

}
