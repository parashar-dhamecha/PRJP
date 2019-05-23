package com.dxdevil.pd.prjp.Model.Response


import com.google.gson.annotations.SerializedName

data class CreateResp(
    @SerializedName("data")
    var `data`: List<CreateData?>?,
    @SerializedName("license")
    var license: Any?,
    @SerializedName("message")
    var message: String?
)