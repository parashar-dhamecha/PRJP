package com.dxdevil.pd.prjp.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyDocList {

    public VerifyDocList(String filehash,
                          Integer currentPage,
                          Object transactionHash,
                         String searchText)
    {
        this.fileHash=filehash;
        this.transactionHash=transactionHash;
        this.currentPage=currentPage;
        this.searchText=searchText;
    }


    @SerializedName("fileHash")
    @Expose
    private String fileHash;
    @SerializedName("transactionHash")
    @Expose
    private Object transactionHash;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("searchText")
    @Expose
    private String searchText;

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

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

}