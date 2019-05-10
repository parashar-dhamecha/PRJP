
package com.dxdevil.pd.prjp.Model.Response.Document.DocDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentDetail {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("extension")
    @Expose
    private String extension;
    @SerializedName("uploadedBy")
    @Expose
    private String uploadedBy;
    @SerializedName("documentFileHash")
    @Expose
    private String documentFileHash;
    @SerializedName("documentStatus")
    @Expose
    private Integer documentStatus;
    @SerializedName("creationTime")
    @Expose
    private String creationTime;
    @SerializedName("isVideoConferenceEnabled")
    @Expose
    private Boolean isVideoConferenceEnabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getDocumentFileHash() {
        return documentFileHash;
    }

    public void setDocumentFileHash(String documentFileHash) {
        this.documentFileHash = documentFileHash;
    }

    public Integer getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(Integer documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getIsVideoConferenceEnabled() {
        return isVideoConferenceEnabled;
    }

    public void setIsVideoConferenceEnabled(Boolean isVideoConferenceEnabled) {
        this.isVideoConferenceEnabled = isVideoConferenceEnabled;
    }

}
