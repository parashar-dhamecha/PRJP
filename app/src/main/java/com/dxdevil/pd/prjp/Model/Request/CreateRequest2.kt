package com.dxdevil.pd.prjp.Model.Request

data class CreateRequest2(
    val processDocumentId: String,
    val name: String,
    val fileName: String,
    val extension: String,
    val description: String,
    val expiryStartDate: String,
    val expiryEndDate: String,
    val signingDueDate: String,
    val reminderBefore: Int,
    val documentShapeModel: List<DocumentShapeModel>,
    val signingFlowType: Int,
    val signerIds: List<String>,
    val observerIds: List<String>?,
    val signatures: List<Int>,
    val documentLatitude: Double,
    val documentLongitude: Double,
    val userAgent: String?,
    val userIPAddress: String,
    val authenticationTypes: List<Int>
)