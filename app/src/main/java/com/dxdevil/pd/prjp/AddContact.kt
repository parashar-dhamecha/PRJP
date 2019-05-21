package com.dxdevil.pd.prjp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.AddContactRequest
import com.dxdevil.pd.prjp.Model.Response.AddContactDatum
import com.dxdevil.pd.prjp.Model.Response.AddContactResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_contact.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AddContact : AppCompatActivity() {


    val Nameregex="""^[A-Za-z  ]+${'$'}""".toRegex()
    val Numberregex="""^[0-9]+$""".toRegex()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val actionbar = supportActionBar
        actionbar!!.title = "Add Contact"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)



        CreateContact!!.setOnClickListener{


            if(validation()) {


                var pd = ProgressDialog(this)
                pd.setMessage("Adding Contact")
                pd.isIndeterminate = true
                pd.show()
                var token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token","")
                val apiadd = RetrofitClient.getInstance()!!.api as Api
                val calladd =apiadd.addcontact(token,
                   AddContactRequest(etName!!.text.toString(),etEmail!!.text.toString() ,
                       etcode.selectedCountryCodeAsInt,
                       etMobileNo!!.text.toString(),
                       etJobTitle.text.toString(),
                       etJobDescription.text.toString()
                       )) as Call<AddContactResponse>

                calladd?.enqueue(object : Callback<AddContactResponse>{
                    override fun onFailure(call: Call<AddContactResponse>, t: Throwable) {
                        pd.dismiss()
                        //Toast.makeText(this@AddContact,"Check your Internet Connection", Toast.LENGTH_LONG).show()
                        Snackbar.make(it,"Check your internet connection",Snackbar.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<AddContactResponse>, response: Response<AddContactResponse>) {

                        try {
                            if (response.isSuccessful) {
                                pd.dismiss()
                               // var contactList = response.body()?.data as AddContactDatum
                                print("response"+response.body().toString())
                                var contactList = response.body()?.data as List<AddContactDatum>
                                println("contact list size" + contactList.size)


                                val intent = Intent(this@AddContact, Contacts::class.java)
                                startActivity(intent)

                            } else {
                                pd.dismiss()
                                Toast.makeText(this@AddContact, "error", Toast.LENGTH_SHORT).show()
                            }

                        } catch (e: IOException) {
                            Toast.makeText(applicationContext, "error exception", Toast.LENGTH_SHORT).show()
                        }

                     }

            })
        }
    }

}

    private fun validation() : Boolean{


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
