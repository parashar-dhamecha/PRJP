package com.dxdevil.pd.prjp.Model.Request

data class CreateRequest(
    val authenticationTypes: List<Int>,
    val description: String,
    val documentLatitude: Double,
    val documentLongitude: Double,
    val documentShapeModel: List<DocumentShapeModel>,
    val expiryEndDate: String,
    val expiryStartDate: String,
    val extension: String,
    val fileName: String,
    val name: String,
    val observerIds: List<Any>?,
    val processDocumentId: String,
    val reminderBefore: Int,
    val signatures: List<Int>,
    val signerIds: List<String>,
    val signingDueDate: String,
    val signingFlowType: Int,
    val userAgent: String,
    val userIPAddress: String
)