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
import com.dxdevil.pd.prjp.Model.Response.Datum
import kotlinx.android.synthetic.main.activity_add_contact.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AddContact : AppCompatActivity() {


    val Nameregex="""^[A-Za-z]+${'$'}""".toRegex()
    val Numberregex="""^[0-9]+$""".toRegex()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)


        CreateContact!!.setOnClickListener{


//            val name =etName!!.text.toString()
//            val Em = etEmail!!.text.toString()
//            val Jt =etJobTitle.text.toString()
//            val Jd =etJobDescription!!.text.toString()
//            val cid:Int = etcode!!.text as Int
//            val mn =etMobileNo!!.text.toString()


            if(validation()) {


                var pd = ProgressDialog(this)
                pd.setMessage("Adding Contact...")
                pd.isIndeterminate = true
                pd.show()

                var token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token","")
                val apiadd = RetrofitClient.getInstance()!!.api as Api
                val calladd =apiadd.addcontact(token,
                   AddContactRequest( "abc",
                     "abcc@gmail.com",
                       91,
                       "94279666887",
                       "wr",
                       "wr")) as Call<AddContactResponse>

                calladd?.enqueue(object : Callback<AddContactResponse>{
                    override fun onFailure(call: Call<AddContactResponse>, t: Throwable) {
                        pd.dismiss()
                        Toast.makeText(this@AddContact,t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<AddContactResponse>, response: Response<AddContactResponse>) {

                        try {
                            if (response.isSuccessful) {
                                pd.dismiss()
                               // var contactList = response.body()?.data as AddContactDatum
                                print("response"+response.body().toString())
                                var contactList = response.body()?.data as List<AddContactDatum>
                                println("contact list size" + contactList.size)


                                Toast.makeText(this@AddContact, "Success", Toast.LENGTH_SHORT).show()
                               // val intent = Intent(this@AddContact, Contacts::class.java)
                                //startActivity(intent)

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
        var cid = etcode!!.text.toString()
        var mn =etMobileNo!!.text.toString()

        var flagname = false
        var flagEm = false
        var flagJt=false
        var flagJd=false
        var flagcid=false
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
            etJobDescription!!.setError("enter a valod job discription")
        } else {
            flagJd= true
        }

        if (cid.isEmpty() ||!Numberregex.matches(cid)||cid.length>3) {

            etcode!!.setError("Enter a valid company name")
        } else {
            flagcid= true
        }

        if (mn.isEmpty() || mn.length > 15 || !Numberregex.matches(mn)) {
            etMobileNo!!.setError("Enter a valid MobileNo")
        } else {
            flagmn = true
        }

        if(flagname && flagEm &&  flagJt && flagJd && flagcid &&  flagmn) {
            valid = true
        }
        return valid
    }





    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
