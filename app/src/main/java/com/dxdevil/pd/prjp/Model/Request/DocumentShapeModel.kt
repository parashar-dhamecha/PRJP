package com.dxdevil.pd.prjp.Model.Request

data class DocumentShapeModel(
    var SignatureType: String,
    var h: Int,
    var hPercentage: Double,
    var isAnnotation: Boolean,
    var p: Int,
    var ratio: String,
    var userId: String,
    var w: Int,
    var wPercentage: Double,
    var x: Int,
    var xPercentage: Double,
    var y: Int,
    var yPercentage: Double
)