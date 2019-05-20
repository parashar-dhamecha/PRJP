package com.dxdevil.pd.prjp.Model.Request

data class DocumentShapeModel(
    var x: Int,
    var xPercentage: Double,
    var y: Int,
    var yPercentage: Double,
    var w: Int,
    var wPercentage: Double,
    var h: Int,
    var hPercentage: Double,
    var p: Int,
    var ratio: String,
    var userId: String,
    var isAnnotation: Boolean,
    var SignatureType: String
)