package com.dxdevil.pd.prjp.Model.Request


import com.google.gson.annotations.SerializedName

data class DocumentShapeModelXX(
    @SerializedName("x")
    var x: Int?,
    @SerializedName("xPercentage")
    var xPercentage: Double?,
    @SerializedName("y")
    var y: Int?,
    @SerializedName("yPercentage")
    var yPercentage: Double?,
    @SerializedName("w")
    var w: Int?,
    @SerializedName("wPercentage")
    var wPercentage: Double?,
    @SerializedName("h")
    var h: Int?,
    @SerializedName("hPercentage")
    var hPercentage: Double?,
    @SerializedName("p")
    var p: Int?,
    @SerializedName("ratio")
    var ratio: String?,
    @SerializedName("userId")
    var userId: String?,
    @SerializedName("isAnnotation")
    var isAnnotation: Boolean?,
    @SerializedName("SignatureType")
    var signatureType: String?
)