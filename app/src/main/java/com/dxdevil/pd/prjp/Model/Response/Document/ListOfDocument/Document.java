
package com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("extension")
    @Expose
    private String extension;
    @SerializedName("creationTime")
    @Expose
    private String creationTime;
    @SerializedName("uploadedBy")
    @Expose
    private String uploadedBy;
    @SerializedName("signingDueDate")
    @Expose
    private String signingDueDate;
    @SerializedName("documentStatusForUser")
    @Expose
    private Integer documentStatusForUser;
    @SerializedName("displaySignButton")
    @Expose
    private Boolean displaySignButton;
    @SerializedName("displayDownloadButton")
    @Expose
    private Boolean displayDownloadButton;
    @SerializedName("displayRemindButton")
    @Expose
    private Boolean displayRemindButton;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getSigningDueDate() {
        return signingDueDate;
    }

    public void setSigningDueDate(String signingDueDate) {
        this.signingDueDate = signingDueDate;
    }

    public Integer getDocumentStatusForUser() {
        return documentStatusForUser;
    }

    public void setDocumentStatusForUser(Integer documentStatusForUser) {
        this.documentStatusForUser = documentStatusForUser;
    }

    public Boolean getDisplaySignButton() {
        return displaySignButton;
    }

    public void setDisplaySignButton(Boolean displaySignButton) {
        this.displaySignButton = displaySignButton;
    }

    public Boolean getDisplayDownloadButton() {
        return displayDownloadButton;
    }

    public void setDisplayDownloadButton(Boolean displayDownloadButton) {
        this.displayDownloadButton = displayDownloadButton;
    }

    public Boolean getDisplayRemindButton() {
        return displayRemindButton;
    }

    public void setDisplayRemindButton(Boolean displayRemindButton) {
        this.displayRemindButton = displayRemindButton;
    }

}
