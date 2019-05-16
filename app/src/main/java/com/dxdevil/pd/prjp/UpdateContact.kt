package com.dxdevil.pd.prjp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.UpdateIdRequest
import com.dxdevil.pd.prjp.Model.Response.*
import kotlinx.android.synthetic.main.activity_add_contact.*
import kotlinx.android.synthetic.main.activity_update_contact.*
import kotlinx.android.synthetic.main.activity_update_contact.etEmail
import kotlinx.android.synthetic.main.activity_update_contact.etJobDescription
import kotlinx.android.synthetic.main.activity_update_contact.etJobTitle
import kotlinx.android.synthetic.main.activity_update_contact.etMobileNo
import kotlinx.android.synthetic.main.activity_update_contact.etName
import kotlinx.android.synthetic.main.activity_update_contact.etcode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UpdateContact : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_contact)
        var st = getSharedPreferences("userid", 0) as SharedPreferences

        val actionbar = supportActionBar
        actionbar!!.title = "Edit Contact"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        try {

            var token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")
            val apiup = RetrofitClient.getInstance()!!.api as Api
            var call1 =
                apiup.getcontactid(token, st.getString("userid", "")) as Call<GetContactIdResponse>
            call1.enqueue(object : Callback<GetContactIdResponse> {

                override fun onFailure(call: Call<GetContactIdResponse>, t: Throwable) {

                    Toast.makeText(this@UpdateContact, "Check your internet Connection", Toast.LENGTH_LONG).show()

                }

                override fun onResponse(
                    call: Call<GetContactIdResponse>, response: Response<GetContactIdResponse>
                ) {

                    if (response.isSuccessful) {



                        var obj = response.body()!!.data?.get(0) as GetContactIdResponse.GetContactIdDatum

                        etName.setText(obj.name)
                        etEmail.setText(obj.email)
                        etcode.setCountryForPhoneCode(obj.countryId)
                        etMobileNo.setText(obj.mobileNumber)
                        etJobTitle.setText(obj.jobTitle)
                        etJobDescription.setText(obj.jobDescription)


                        Toast.makeText(this@UpdateContact, "success", Toast.LENGTH_SHORT).show()


                    } else {
                        Toast.makeText(this@UpdateContact, response.message().toString() + response.errorBody(), Toast.LENGTH_LONG).show()
                    }
                }
            }
            )
        } catch (e: Exception) {
            Toast.makeText(this@UpdateContact, e.toString(), Toast.LENGTH_LONG).show()
        }

        SaveContact!!.setOnClickListener {

            if (validation()) {


                try {

                    var pd = ProgressDialog(this)
                    pd.setMessage("Saving Contact")
                    pd.isIndeterminate = true
                    pd.show()


                    var token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")
                    var apiup1 = RetrofitClient.getInstance()!!.api as Api
                    var callupdateid =
                        apiup1.updateid(
                            token,
                            st.getString("userid", "").toString(),
                            UpdateIdRequest(
                                st.getString("userid", "").toString(),
                                etName.text.toString(),
                                etEmail.text.toString(),
                                etcode.selectedCountryCodeAsInt,
                                etMobileNo.text.toString(),
                                etJobTitle.text.toString(),
                                etJobDescription.text.toString()
                            )
                        ) as Call<UpdateIdResponse>
                    callupdateid.enqueue(object : Callback<UpdateIdResponse> {
                        override fun onFailure(call: Call<UpdateIdResponse>, t: Throwable) {
                            pd.dismiss()
                            Toast.makeText(this@UpdateContact, "check your connection", Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<UpdateIdResponse>, response: Response<UpdateIdResponse>) {
                            pd.dismiss()

                            if (response.isSuccessful) {
                                Toast.makeText(this@UpdateContact, "Contact updated successfully", Toast.LENGTH_LONG)
                                    .show()
                                val intent = Intent(this@UpdateContact, Contacts::class.java)
                                startActivity(intent)
                            } else {
                                pd.dismiss()
                                Toast.makeText(this@UpdateContact, response.message().toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                    })
                } catch (e: Exception) {
                    Toast.makeText(this@UpdateContact, "error", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

     fun validation() : Boolean{


        var valid = false


        var name =etName!!.text.toString()
        var Em = etEmail!!.text.toString()
        var Jt =etJobTitle.text.toString()
        var Jd =etJobDescription!!.text.toString()
        //var cid = etcode!!.selectedCountryCode as Int
        var mn =etMobileNo!!.text.toString()

        var flagname = false
        var flagEm = false
        var flagJt=false
        var flagJd=false
        var flagmn = false


        if (name.isEmpty() || !(Nameregex.matches(name))) {
            etName!!.error = "Enter a valid Name"
        } else {
            flagname = true
        }



        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Em).matches()) {
            etEmail!!.error = "Enter a valid Email Address"
            valid = false
        } else {
            flagEm = true
        }



        if (Jt.isEmpty() ||!Nameregex.matches(Jt)) {

            etJobTitle!!.setError("Enter a valid job title")
        } else {
            flagJt = true
        }

        if (Jd.isEmpty() ||!Nameregex.matches(Jd)) {
            etJobDescription!!.setError("enter a valod job description")
        } else {
            flagJd= true
        }

        if (mn.isEmpty() || mn.length > 10|| !Numberregex.matches(mn)) {
            etMobileNo!!.setError("Enter a valid MobileNo")
        } else {
            flagmn = true
        }

        if(flagname && flagEm &&  flagJt && flagJd  &&  flagmn) {
            valid = true
        }
        return valid
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
