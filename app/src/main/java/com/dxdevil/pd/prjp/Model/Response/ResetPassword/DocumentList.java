
package com.dxdevil.pd.prjp.Model.Response.ResetPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentList {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("creationTime")
    @Expose
    private String creationTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("documentStatus")
    @Expose
    private Integer documentStatus;
    @SerializedName("extension")
    @Expose
    private String extension;
    @SerializedName("totalRows")
    @Expose
    private Integer totalRows;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(Integer documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

}
