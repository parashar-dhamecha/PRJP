package com.dxdevil.pd.prjp


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.dxdevil.pd.prjp.Model.Request.ResetPasswordRequest
import com.dxdevil.pd.prjp.Model.Response.ResetPassword.ResetPasswordResponse
import kotlinx.android.synthetic.main.activity_forgotpass.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.contactsadapter.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ResetPasswordActivity(): AppCompatActivity(){

    var email:String?=null
    var Pass:String?=null
    var CPass:String?=null
    var securityToken:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)


        btnRESET.setOnClickListener {


                 securityToken= intent?.data?.getQueryParameter("Tkn").toString()

                if(validation()){

                    email=email_reset.text.toString()
                    Pass=password_reset.text.toString()
                    CPass=cPass_reset.text.toString()

                    val api = RetrofitClient.getInstance().api as Api

                    val call= api.resetPassword(

                        ResetPasswordRequest(email,Pass,CPass,securityToken)

                    ) as Call<ResetPasswordResponse>

                    call.enqueue(object:Callback<ResetPasswordResponse>{

                        override fun onFailure(call: Call<ResetPasswordResponse>, t: Throwable) {

                            Toast.makeText(this@ResetPasswordActivity,"Check your connection",Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(
                            call: Call<ResetPasswordResponse>,
                            response: Response<ResetPasswordResponse>
                        ) {
                            if(response.isSuccessful)
                            {
                                Toast.makeText(this@ResetPasswordActivity,"You have successfully changed the password.",Toast.LENGTH_SHORT).show()

                                if(response.body()!!.data[0].redirectToLogin==true)
                                    startActivity(Intent(this@ResetPasswordActivity, LoginActivity::class.java))
                            }
                            else
                            {
                                Toast.makeText(this@ResetPasswordActivity,response.message(),Toast.LENGTH_SHORT).show()
                            }

                        }
                    })
                }
            }
    }

    fun validation():Boolean{
        var valid =false
        var flagEmail=false
        var flagPASS=false
        var flagCPASS=false

        email=email_reset.text.toString()
        Pass=password_reset.text.toString()
        CPass=cPass_reset.text.toString()

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputLayout14!!.error = "Enter a valid Email Address"

        } else {
            flagEmail=true
            textInputLayout14!!.error =null
        }

        if (Pass!!.isEmpty() || Pass!!.length < 8 || !Passwordregex.matches(Pass!!)) {

            textInputLayout15.error = "Enter a valid Password"
        } else {
            textInputLayout15.error = null
            flagPASS = true
        }

        if (CPass!!.isEmpty() || CPass!!.length < 8 || CPass != Pass) {
            textInputLayout12.error = "Password Do not match"
        } else {
            textInputLayout12.error = null
            flagCPASS = true
        }

        if(flagEmail&&flagCPASS&&flagPASS)
        { valid=true}

        return valid
    }
}