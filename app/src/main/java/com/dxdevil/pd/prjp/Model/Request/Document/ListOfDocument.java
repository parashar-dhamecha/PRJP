package  com.dxdevil.pd.prjp.Model.Request.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfDocument {

    public ListOfDocument(Integer documentStatusId,
                          Integer currentPage,
                          Boolean isNext,
                          String searchText,
                          String startDate,
                          String endDate,
                          Integer signatureType,
                          String uploadedBy,
                          String signerName,
                          String dateDuration)
    {
        this.documentStatusId=documentStatusId;
        this.currentPage=currentPage;
        this.isNext=isNext;
        this.searchText=searchText;
        this.startDate=startDate;
        this.endDate=endDate;
        this.signatureType=signatureType;
        this.uploadedBy=uploadedBy;
        this.signerName=signerName;
        this.dateDuration=dateDuration;
    }

    @SerializedName("documentStatusId")
    @Expose
    private Integer documentStatusId;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("isNext")
    @Expose
    private Boolean isNext;
    @SerializedName("searchText")
    @Expose
    private String searchText;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("signatureType")
    @Expose
    private Integer signatureType;
    @SerializedName("uploadedBy")
    @Expose
    private String uploadedBy;
    @SerializedName("signerName")
    @Expose
    private String signerName;
    @SerializedName("dateDuration")
    @Expose
    private String dateDuration;

    public Integer getDocumentStatusId() {
        return documentStatusId;
    }

    public void setDocumentStatusId(Integer documentStatusId) {
        this.documentStatusId = documentStatusId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Boolean getIsNext() {
        return isNext;
    }

    public void setIsNext(Boolean isNext) {
        this.isNext = isNext;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getSignatureType() {
        return signatureType;
    }

    public void setSignatureType(Integer signatureType) {
        this.signatureType = signatureType;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public String getDateDuration() {
        return dateDuration;
    }

    public void setDateDuration(String dateDuration) {
        this.dateDuration = dateDuration;
    }

}