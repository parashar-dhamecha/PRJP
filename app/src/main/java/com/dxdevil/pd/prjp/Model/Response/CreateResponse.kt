package com.dxdevil.pd.prjp.Model.Response

data class CreateResponse(
    val data: List<DataCreate>,
    val license: Any,
    val message: String
)