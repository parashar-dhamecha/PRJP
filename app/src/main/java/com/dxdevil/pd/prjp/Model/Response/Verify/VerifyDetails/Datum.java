
package com.dxdevil.pd.prjp.Model.Verify.VerifyDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("documentDetail")
    @Expose
    private DocumentDetail documentDetail;
    @SerializedName("documentHistory")
    @Expose
    private List<DocumentHistory> documentHistory = null;
    @SerializedName("signedByCurrentUser")
    @Expose
    private Boolean signedByCurrentUser;
    @SerializedName("signedByAll")
    @Expose
    private Boolean signedByAll;
    @SerializedName("sequentialFlow")
    @Expose
    private Boolean sequentialFlow;
    @SerializedName("signingFlowType")
    @Expose
    private Integer signingFlowType;
    @SerializedName("liveSignatureStartDate")
    @Expose
    private Object liveSignatureStartDate;
    @SerializedName("liveSignatureEndDate")
    @Expose
    private Object liveSignatureEndDate;
    @SerializedName("signers")
    @Expose
    private List<Signer> signers = null;
    @SerializedName("observers")
    @Expose
    private List<Object> observers = null;
    @SerializedName("rejectedByCurrentUser")
    @Expose
    private Boolean rejectedByCurrentUser;
    @SerializedName("notarization")
    @Expose
    private Notarization notarization;
    @SerializedName("documentVerifiedByBlockchain")
    @Expose
    private Boolean documentVerifiedByBlockchain;
    @SerializedName("QRContent")
    @Expose
    private Object qRContent;
    @SerializedName("ownerName")
    @Expose
    private Object ownerName;
    @SerializedName("ownerEmail")
    @Expose
    private Object ownerEmail;
    @SerializedName("ownerIPAddress")
    @Expose
    private Object ownerIPAddress;
    @SerializedName("ownerBrowser")
    @Expose
    private Object ownerBrowser;
    @SerializedName("ownerDevice")
    @Expose
    private Object ownerDevice;
    @SerializedName("ownerLat")
    @Expose
    private Object ownerLat;
    @SerializedName("ownerLong")
    @Expose
    private Object ownerLong;

    public DocumentDetail getDocumentDetail() {
        return documentDetail;
    }

    public void setDocumentDetail(DocumentDetail documentDetail) {
        this.documentDetail = documentDetail;
    }

    public List<DocumentHistory> getDocumentHistory() {
        return documentHistory;
    }

    public void setDocumentHistory(List<DocumentHistory> documentHistory) {
        this.documentHistory = documentHistory;
    }

    public Boolean getSignedByCurrentUser() {
        return signedByCurrentUser;
    }

    public void setSignedByCurrentUser(Boolean signedByCurrentUser) {
        this.signedByCurrentUser = signedByCurrentUser;
    }

    public Boolean getSignedByAll() {
        return signedByAll;
    }

    public void setSignedByAll(Boolean signedByAll) {
        this.signedByAll = signedByAll;
    }

    public Boolean getSequentialFlow() {
        return sequentialFlow;
    }

    public void setSequentialFlow(Boolean sequentialFlow) {
        this.sequentialFlow = sequentialFlow;
    }

    public Integer getSigningFlowType() {
        return signingFlowType;
    }

    public void setSigningFlowType(Integer signingFlowType) {
        this.signingFlowType = signingFlowType;
    }

    public Object getLiveSignatureStartDate() {
        return liveSignatureStartDate;
    }

    public void setLiveSignatureStartDate(Object liveSignatureStartDate) {
        this.liveSignatureStartDate = liveSignatureStartDate;
    }

    public Object getLiveSignatureEndDate() {
        return liveSignatureEndDate;
    }

    public void setLiveSignatureEndDate(Object liveSignatureEndDate) {
        this.liveSignatureEndDate = liveSignatureEndDate;
    }

    public List<Signer> getSigners() {
        return signers;
    }

    public void setSigners(List<Signer> signers) {
        this.signers = signers;
    }

    public List<Object> getObservers() {
        return observers;
    }

    public void setObservers(List<Object> observers) {
        this.observers = observers;
    }

    public Boolean getRejectedByCurrentUser() {
        return rejectedByCurrentUser;
    }

    public void setRejectedByCurrentUser(Boolean rejectedByCurrentUser) {
        this.rejectedByCurrentUser = rejectedByCurrentUser;
    }

    public Notarization getNotarization() {
        return notarization;
    }

    public void setNotarization(Notarization notarization) {
        this.notarization = notarization;
    }

    public Boolean getDocumentVerifiedByBlockchain() {
        return documentVerifiedByBlockchain;
    }

    public void setDocumentVerifiedByBlockchain(Boolean documentVerifiedByBlockchain) {
        this.documentVerifiedByBlockchain = documentVerifiedByBlockchain;
    }

    public Object getQRContent() {
        return qRContent;
    }

    public void setQRContent(Object qRContent) {
        this.qRContent = qRContent;
    }

    public Object getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(Object ownerName) {
        this.ownerName = ownerName;
    }

    public Object getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(Object ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Object getOwnerIPAddress() {
        return ownerIPAddress;
    }

    public void setOwnerIPAddress(Object ownerIPAddress) {
        this.ownerIPAddress = ownerIPAddress;
    }

    public Object getOwnerBrowser() {
        return ownerBrowser;
    }

    public void setOwnerBrowser(Object ownerBrowser) {
        this.ownerBrowser = ownerBrowser;
    }

    public Object getOwnerDevice() {
        return ownerDevice;
    }

    public void setOwnerDevice(Object ownerDevice) {
        this.ownerDevice = ownerDevice;
    }

    public Object getOwnerLat() {
        return ownerLat;
    }

    public void setOwnerLat(Object ownerLat) {
        this.ownerLat = ownerLat;
    }

    public Object getOwnerLong() {
        return ownerLong;
    }

    public void setOwnerLong(Object ownerLong) {
        this.ownerLong = ownerLong;
    }

}
