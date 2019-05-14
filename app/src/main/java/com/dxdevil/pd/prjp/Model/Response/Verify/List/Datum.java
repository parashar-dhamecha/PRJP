
package com.dxdevil.pd.prjp.Model.Response.Verify.List;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("totalPage")
    @Expose
    private Integer totalPage;
    @SerializedName("totalRows")
    @Expose
    private Integer totalRows;
    @SerializedName("documentList")
    @Expose
    private List<DocumentList> documentList = null;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public List<DocumentList> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<DocumentList> documentList) {
        this.documentList = documentList;
    }

}
