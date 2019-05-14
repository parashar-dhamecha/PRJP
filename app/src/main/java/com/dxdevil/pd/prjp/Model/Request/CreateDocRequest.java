package com.dxdevil.pd.prjp.Model.Request;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateDocRequest implements Serializable
{

    @SerializedName("processDocumentId")
    @Expose
    public String processDocumentId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("fileName")
    @Expose
    public String fileName;
    @SerializedName("extension")
    @Expose
    public String extension;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("expiryStartDate")
    @Expose
    public String expiryStartDate;
    @SerializedName("expiryEndDate")
    @Expose
    public String expiryEndDate;
    @SerializedName("signingDueDate")
    @Expose
    public String signingDueDate;
    @SerializedName("reminderBefore")
    @Expose
    public Integer reminderBefore;
    @SerializedName("documentShapeModel")
    @Expose
    public List<DocumentShapeModelj> documentShapeModel = null;
    @SerializedName("signingFlowType")
    @Expose
    public Integer signingFlowType;
    @SerializedName("signerIds")
    @Expose
    public List<String> signerIds = null;
    @SerializedName("observerIds")
    @Expose
    public List<Object> observerIds = null;
    @SerializedName("signatures")
    @Expose
    public List<Integer> signatures = null;
    @SerializedName("documentLatitude")
    @Expose
    public Double documentLatitude;
    @SerializedName("documentLongitude")
    @Expose
    public Double documentLongitude;
    @SerializedName("userAgent")
    @Expose
    public String userAgent;
    @SerializedName("userIPAddress")
    @Expose
    public String userIPAddress;
    @SerializedName("authenticationTypes")
    @Expose
    public List<Integer> authenticationTypes = null;
    private final static long serialVersionUID = 1451998794135227070L;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateDocRequest() {
    }

    /**
     *
     * @param signingDueDate
     * @param authenticationTypes
     * @param userAgent
     * @param documentLongitude
     * @param processDocumentId
     * @param expiryEndDate
     * @param signatures
     * @param documentShapeModel
     * @param signerIds
     * @param extension
     * @param signingFlowType
     * @param description
     * @param name
     * @param userIPAddress
     * @param fileName
     * @param observerIds
     * @param reminderBefore
     * @param documentLatitude
     * @param expiryStartDate
     */
    public CreateDocRequest(String processDocumentId, String name, String fileName, String extension, String description, String expiryStartDate, String expiryEndDate, String signingDueDate, Integer reminderBefore, List<DocumentShapeModelj> documentShapeModel, Integer signingFlowType, List<String> signerIds, List<Object> observerIds, List<Integer> signatures, Double documentLatitude, Double documentLongitude, String userAgent, String userIPAddress, List<Integer> authenticationTypes) {
        super();
        this.processDocumentId = processDocumentId;
        this.name = name;
        this.fileName = fileName;
        this.extension = extension;
        this.description = description;
        this.expiryStartDate = expiryStartDate;
        this.expiryEndDate = expiryEndDate;
        this.signingDueDate = signingDueDate;
        this.reminderBefore = reminderBefore;
        this.documentShapeModel = documentShapeModel;
        this.signingFlowType = signingFlowType;
        this.signerIds = signerIds;
        this.observerIds = observerIds;
        this.signatures = signatures;
        this.documentLatitude = documentLatitude;
        this.documentLongitude = documentLongitude;
        this.userAgent = userAgent;
        this.userIPAddress = userIPAddress;
        this.authenticationTypes = authenticationTypes;
    }

}