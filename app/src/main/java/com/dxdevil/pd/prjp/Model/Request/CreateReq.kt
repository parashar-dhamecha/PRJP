package com.dxdevil.pd.prjp.Model.Request


import com.google.gson.annotations.SerializedName

data class CreateReq(
    @SerializedName("processDocumentId")
    var processDocumentId: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("fileName")
    var fileName: String?,
    @SerializedName("extension")
    var extension: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("expiryStartDate")
    var expiryStartDate: String?,
    @SerializedName("expiryEndDate")
    var expiryEndDate: String?,
    @SerializedName("signingDueDate")
    var signingDueDate: String?,
    @SerializedName("reminderBefore")
    var reminderBefore: Int?,
    @SerializedName("documentShapeModel")
    var documentShapeModel: List<DocumentShapeModelXX?>?,
    @SerializedName("signingFlowType")
    var signingFlowType: Int?,
    @SerializedName("signerIds")
    var signerIds: List<String?>?,
    @SerializedName("observerIds")
    var observerIds: List<Any?>?,
    @SerializedName("signatures")
    var signatures: List<Int?>?,
    @SerializedName("documentLatitude")
    var documentLatitude: Double?,
    @SerializedName("documentLongitude")
    var documentLongitude: Double?,
    @SerializedName("userAgent")
    var userAgent: String?,
    @SerializedName("userIPAddress")
    var userIPAddress: String?,
    @SerializedName("authenticationTypes")
    var authenticationTypes: List<Int?>?
)