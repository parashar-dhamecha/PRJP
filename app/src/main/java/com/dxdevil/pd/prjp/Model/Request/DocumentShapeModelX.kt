package com.dxdevil.pd.prjp.Model.Request

data class DocumentShapeModelX(
    val CreationTime: String,
    val Id: String,
    val Itype: String,
    val SignatureType: String,
    val UpdatedTime: String,
    val h: Int,
    val hPercentage: Int,
    val imName: String,
    val isAnnotation: Boolean,
    val isForSigning: Boolean,
    val isReplaced: Boolean,
    val p: Int,
    val ratio: String,
    val userId: String,
    val userName: String,
    val w: Int,
    val wPercentage: Int,
    val x: Int,
    val xPercentage: Int,
    val y: Int,
    val yPercentage: Int
)