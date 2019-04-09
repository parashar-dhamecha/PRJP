
package com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("documentStatusId")
    @Expose
    private Integer documentStatusId;
    @SerializedName("totalRows")
    @Expose
    private Integer totalRows;
    @SerializedName("documents")
    @Expose
    private List<Document> documents = null;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getDocumentStatusId() {
        return documentStatusId;
    }

    public void setDocumentStatusId(Integer documentStatusId) {
        this.documentStatusId = documentStatusId;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

}
