package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.app.ProgressDialog

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dxdevil.pd.prjp.Model.Request.SignUp
import com.dxdevil.pd.prjp.Model.Request.VerifyUser
import com.dxdevil.pd.prjp.Model.Response.SignUpModel
import com.dxdevil.pd.prjp.Model.Response.UserExistResponse
import kotlinx.android.synthetic.main.activity_registration.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


val Passwordregex = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@${'$'}!%*?&])[A-Za-z\d@${'$'}!%*?&]{8,}${'$'}""".toRegex()
val Nameregex="""^[A-Za-z]+${'$'}""".toRegex()
val Numberregex="""^[0-9]+$""".toRegex()



class Registration : AppCompatActivity() {

    @SuppressLint("MissingPermission")



    //@SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)




        loginlink!!.setOnClickListener{                                //link to the login activity
            startActivity(Intent(this@Registration, LoginActivity::class.java))
        }


        btregister!!.setOnClickListener {


            val fname=etFirstName!!.text.toString()
            val lname=etLastName!!.text.toString()
            val email= etEmail!!.text.toString()
            val pass =etPassword!!.text.toString()
            val confirmPass =etConfirmPassword!!.text.toString()
            val jt = etJobTitle!!.text.toString()
            val cn =etCompanyName!!.text.toString()
            val cID = etCountryId!!.text.toString()
            val cIDInt:Int= etCountryId!!.text.toString().toInt()
            val mo =etMobileNo!!.text.toString()


            if(validation()) {

                val pd = ProgressDialog(this)
                pd.setMessage("Signing Up...")
                pd.isIndeterminate = true
                pd.show()


//////////////////// check if user already exist////////////////////////////////////////////////////////////////////////////

                val api = RetrofitClient.getInstance()!!.api as Api
                val call1 =api.isUserExist(email) as Call<UserExistResponse>

                call1.enqueue(object : Callback<UserExistResponse> {

                    override fun onResponse(call: Call<UserExistResponse>, response: Response<UserExistResponse>) {

                        try {

                            if (response.isSuccessful) {
                                //pd.dismiss()

                                if(response.body()!!.data[0]==true){
                                    pd.dismiss()
                                    Toast.makeText(this@Registration,response.body()!!.message, Toast.LENGTH_SHORT).show()}
                                else{
                                    val apiReg = RetrofitClient.getInstance().api as Api
                                    val call2= apiReg.register(
                                        SignUp(fname,
                                            lname,
                                            email,
                                            pass,
                                            confirmPass,
                                            jt,
                                            cn,
                                            cID,
                                            mo,
                                            "4.092356",
                                            "-56.062161",
                                            "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0 Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0",
                                            "+05:30")
                                    ) as Call<SignUpModel>

                                    call2.enqueue(object : Callback<SignUpModel> {

                                        override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {

                                            try {

                                                //Log.e("error",response.body()!!.message.toString())
                                                if (response.isSuccessful) {
                                                    pd.dismiss()
                                                    Toast.makeText(this@Registration, response.body()!!.message, Toast.LENGTH_SHORT).show()

                                                    val call3=api.verify_user(VerifyUser(fname,
                                                        lname,
                                                        email,
                                                        pass,
                                                        confirmPass,
                                                        jt,
                                                        cn,
                                                        cIDInt,
                                                        mo,
                                                        "4.092356",
                                                        "-56.062161",
                                                        "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0 Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0",
                                                        "+05:30"
                                                    )) as Call<ResponseBody>

                                                    call3.enqueue(object:Callback<ResponseBody>{
                                                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                                            Toast.makeText(this@Registration,"Something went Wrong",Toast.LENGTH_SHORT).show()
                                                        }

                                                        override fun onResponse(
                                                            call: Call<ResponseBody>,
                                                            response: Response<ResponseBody>
                                                        ) {
                                                          //  Toast.makeText(this@Registration,"verified user successfully",Toast.LENGTH_SHORT).show()
                                                        }
                                                    })


                                                } else {
                                                    pd.dismiss()
                                                    Toast.makeText(this@Registration,"error", Toast.LENGTH_SHORT).show()
                                                }

                                            }catch (e: IOException){
                                                Toast.makeText(applicationContext,"Exceprtion",Toast.LENGTH_SHORT).show()

                                            }

                                        }
                                        override fun onFailure(call: Call<SignUpModel>, t: Throwable)
                                        {
                                            pd.dismiss()
                                            Toast.makeText(this@Registration,"Something went wrong. Try again", Toast.LENGTH_LONG).show()
                                        }
                                    })

                                }

                            } else {
                                pd.dismiss()
                                Toast.makeText(this@Registration,"error", Toast.LENGTH_SHORT).show()
                            }

                        }catch (e:IOException){
                            Toast.makeText(applicationContext,"Exception",Toast.LENGTH_SHORT).show()
                        }

                    }
                    override fun onFailure(call: Call<UserExistResponse>, t: Throwable)
                    {
                        call.cancel()
                        pd.dismiss()
                        Toast.makeText(this@Registration,"Check your connection", Toast.LENGTH_LONG).show()
                    }
                })
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            }
        }
    }

    private fun validation(): Boolean {

        var valid = false


        val fname =etFirstName!!.text.toString()
        val lname = etLastName!!.text.toString()
        val em = etEmail!!.text.toString()
        val password = etPassword!!.text.toString()
        val confirmPassword = etConfirmPassword!!.text.toString()
        val jt =etJobTitle.text.toString()
        val cn =etCompanyName!!.text.toString()
        val cID = etCountryId!!.text.toString()
        val mo =etMobileNo!!.text.toString()

        var flagFirst = false
        var flagLast = false
        var flagEmail = false
        var flagPassword = false
        var flagConfirm = false
        var flagjt=false
        var flagcn =false
        var flagCID=false
        var flagMobile = false


        if (fname.isEmpty() || !Nameregex.matches(fname)) {
            etFirstName!!.error = "Enter a valid First Name"
        } else {
            flagFirst = true
        }

       if (lname.isEmpty() || !Nameregex.matches(lname)) {
            etLastName!!.error = "Enter a valid Last name"
        } else {
            flagLast = true
        }

       if (!android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
            etEmail!!.error = "Enter a valid Email Address"
            valid = false
        } else {
            flagEmail = true
        }

        if (password.isEmpty() || password.length < 8 || !Passwordregex.matches(password)) {

            etPassword!!.error = "Enter a valid Password"
        } else {
            flagPassword = true
        }

        if (confirmPassword.isEmpty() || confirmPassword.length < 8 || confirmPassword != password) {
            etConfirmPassword!!.error = "Password Do not match"
        } else {
            etConfirmPassword!!.error=null
            flagConfirm = true
        }

       if (jt.isEmpty() ||!Nameregex.matches(jt)) {

           etJobTitle!!.error = "Enter a valid job title"
       } else {
           flagjt = true
       }

       if (cn.isEmpty() ||!Nameregex.matches(cn)) {

           etCompanyName!!.error = "Enter a valid company name"
       } else {
           flagcn= true
       }

       if (cID.isEmpty() ||!Numberregex.matches(cID)||cID.length>3) {

           etCountryId!!.error = "Enter a valid company name"
       } else {
           flagCID= true
       }

       if (mo.isEmpty() || mo.length > 15 || !Numberregex.matches(mo)) {
           etMobileNo!!.error = "Enter a valid MobileNo"
       } else {
           flagMobile = true
       }

        if(flagFirst && flagLast && flagEmail && flagPassword && flagConfirm && flagjt && flagcn && flagCID &&  flagMobile) {
            valid = true
        }
        return valid
    }

}



