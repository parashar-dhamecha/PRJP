
package com.dxdevil.pd.prjp.Model.Response.ResetPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifySearchRequest {

    @SerializedName("fileHash")
    @Expose
    private String fileHash;
    @SerializedName("transactionHash")
    @Expose
    private Object transactionHash;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public Object getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(Object transactionHash) {
        this.transactionHash = transactionHash;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

}
