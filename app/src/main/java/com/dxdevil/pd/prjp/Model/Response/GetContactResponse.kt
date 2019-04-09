package com.dxdevil.pd.prjp.Model.Response

import com.dxdevil.pd.prjp.ContactModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class ContactList(
    @SerializedName("contact")
    var contacts: ArrayList<ContactModel>
)


data class Datum (

    @SerializedName("Id")
    var id: String,
    @SerializedName("userId")
    var userId: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("countryId")
    var countryId: Integer,
    @SerializedName("mobileNumber")
    var mobileNumber: String,
    @SerializedName("jobTitle")
    var jobTitle: String,
    @SerializedName("jobDescription")
    var jobDescription: String,
    @SerializedName("shortName")
    var shortName: String
)


 data class GetContactResponse (


     @SerializedName("message")
     var message:String,
     @SerializedName("data")
     var data: List<Datum>? = null,
     @SerializedName("license")
     var license:Object
 )



