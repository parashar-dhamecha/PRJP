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

        val actionbar = supportActionBar
        actionbar!!.title = "Add Contact"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)



        CreateContact!!.setOnClickListener{


//            val name =etName!!.text.toString()
//            val Em = etEmail!!.text.toString()
//            val Jt =etJobTitle.text.toString()
//            val Jd =etJobDescription!!.text.toString()
//            val cid:Int = etcode!!.text as Int
//            val mn =etMobileNo!!.text.toString()

            if(validation()) {


                var pd = ProgressDialog(this)
                pd.setMessage("Adding Contact")
                pd.isIndeterminate = true
                pd.show()
               // var tok:String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.N9w0jV/v+9PKjqTQVRprezt4+qqzzgZZjY68hHbb/VdLYi/P2x192in+N/puRDAp8zEKMMqdnOPVvgPE6C6bukPznNX+g+jkb5sv8FfeS7Q7UXY/QIHjeYLQJ4Tk0Aziq/VdzyrDQLOq20vUdEYJjwIBiq1pk+R/7xBYPpEEPI6lQtTxggOw72uJnYt/kwviC0i6ppoGmWZHU5sX4ZyNBnhSp5RPMYvQKENl5w3MdkGet6cb9MaMD55LM0Ytqy2DMHMqW4H1t4ql13hFw3c/1MhX/sNci8G6WNp1Bo6qnFwi+eu6ABRNaQ0C4lXKjy4K/9Mj9ZN6dqQLCWdpsbW6Bx1K46PitJsHNo6bqFVlUWyxRHNeIJKu655Ema646p9o62EeO4LDIrUhOJLPMpXXDwbdcgHAsLBpM/TMfOdQlaLsB2d6rOlkqlh0n8ForetQ+M19xiDH0C+YxrDeDlnY6v62VfXDwwayEVEgmjZv2SM/CKPoyQgHsYEh3tGXECqP9UVxmqL+jPbLhIrTlku4Z1LsZXmRVc/gEq1/NJPIU7DtF1PEkfIs4VUHuwGClKyyQ2GfWmecbn5RZ4WF4HZpOr8KUDo5TsLVee+4w0W75MRPSaQZVRBz/AaW5owhvQ1DsBzHmO01XfKxj5oXASQUaSOA6OoAyFgrA+JHMi3g10sjOVFPhiHSDyOPenpYcw7rrxB7pXpVEdnW1gen3oT5BSJ+PzMfFkt2sg0Mg4KRFMMG0/cG6wP5C1W2gKQT2hVENRxfTYeifEjLE0oTVot9g+xlczLrrvAjqJS9SuHk+nHJvXRxzQ5h1YwGl2ecOTud5xdrVjj26lFvfLmEdQRMvKV6IzYxOnoizyvng9j5awF8o0AS+LSMvvrAIi/Tu56J.dhaFBoNQUKEFO6u7hwaPv7I8aT8CgCCM6zxn8_xTErw"
                var token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token","")
                val apiadd = RetrofitClient.getInstance()!!.api as Api
                val calladd =apiadd.addcontact(token,
                   AddContactRequest(etName!!.text.toString(),etEmail!!.text.toString() ,
                       etcode.selectedCountryCodeAsInt, etMobileNo!!.text.toString(),
                       etJobTitle.text.toString(),
                       etJobDescription.text.toString()
                       )) as Call<AddContactResponse>

                calladd?.enqueue(object : Callback<AddContactResponse>{
                    override fun onFailure(call: Call<AddContactResponse>, t: Throwable) {
                        pd.dismiss()
                        Toast.makeText(this@AddContact,"Check your Internet Connection", Toast.LENGTH_LONG).show()
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
            etJobDescription!!.setError("enter a valod job discription")
        } else {
            flagJd= true
        }

//        if (cid.isEmpty() ||!Numberregex.matches(cid)||cid.length>2) {
//
//           // etcode.setError("Enter a valid country name")
//        } else {
//            flagcid= true
//        }

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
