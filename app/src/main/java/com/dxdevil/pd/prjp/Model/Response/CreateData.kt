package com.dxdevil.pd.prjp.Model.Response


import com.google.gson.annotations.SerializedName

data class CreateData(
    @SerializedName("documentId")
    var documentId: String?
)