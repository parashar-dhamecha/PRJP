package com.dxdevil.pd.prjp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.Otp
import com.dxdevil.pd.prjp.Model.Request.Verify
import com.dxdevil.pd.prjp.Model.Response.OtpModel
import com.dxdevil.pd.prjp.Model.Response.VerifyModel
import kotlinx.android.synthetic.main.activity_otpactivity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.alimuzaffar.lib.pin.PinEntryEditText
import kotlinx.android.synthetic.main.activity_otpactivity.view.*


class Otpactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)
        var prefe = getSharedPreferences("Token", Context.MODE_PRIVATE) as SharedPreferences
        var token =prefe.getString("Token","")!!.toString()
        var dpref = getSharedPreferences("Login Details",0)
        otpemail!!.text = dpref.getString("email","")

        loginanother!!.setOnClickListener {
            startActivity(Intent(this@Otpactivity,LoginActivity::class.java))
        }


       txt_pin_entry.setOnPinEnteredListener(PinEntryEditText.OnPinEnteredListener { str ->
           var pd = ProgressDialog(this)
           pd.setMessage("Verifying Otp..")
           pd.isIndeterminate = true
           pd.show()

           var vapi = RetrofitClient.getInstance().api as Api
           var call3 = vapi?.verifyotp(token, Verify(str.toString())) as Call<VerifyModel>

           call3!!.enqueue(object : Callback<VerifyModel>{
               override fun onFailure(call: Call<VerifyModel>, t: Throwable) {
                   pd.dismiss()
                   Toast.makeText(this@Otpactivity, "check your network connection", Toast.LENGTH_LONG).show()
               }

               override fun onResponse(call: Call<VerifyModel>, response: Response<VerifyModel>) {
                   Log.e("response","${response.errorBody()} ${response.body()?.message.toString()}" )

                   if (response.isSuccessful){
                       if(response.body()?.message.toString()=="Invalid OTP"){
                           pd.dismiss()
                           Toast.makeText(this@Otpactivity, response.body()?.message.toString(), Toast.LENGTH_LONG).show()
                       }
                       else{
                           pd.dismiss()
                           startActivity(Intent(applicationContext,Dashboard::class.java))
                           Toast.makeText(this@Otpactivity, "Successfully logged in ", Toast.LENGTH_LONG).show()
                       }

                   }else{
                       pd.dismiss()
                       txt_pin_entry.clearComposingText()
                       Toast.makeText(this@Otpactivity, response.body()!!.message.toString(), Toast.LENGTH_LONG).show()
                   }
               }
           })
        })


        resend_otp_button!!.setOnClickListener{
            txt_pin_entry.text = null
            var pd = ProgressDialog(this)
            pd.setMessage("Resending otp...")
            pd.isIndeterminate = true
            pd.show()

            var api2 = RetrofitClient.getInstance().api as Api
            var call2 = api2.sendotp(token, Otp("2")) as Call<OtpModel>
            call2.enqueue(object : Callback<OtpModel> {
                override fun onFailure(call: Call<OtpModel>, t: Throwable) {
                    call2.cancel()
                    pd.dismiss()
                    Toast.makeText(
                        this@Otpactivity,
                        "check your network connection",
                        Toast.LENGTH_LONG
                    ).show()

                }

                override fun onResponse(call: Call<OtpModel>, response: Response<OtpModel>) {
                    if (response.isSuccessful) {
                        pd.dismiss()
                        Toast.makeText(this@Otpactivity, "otp sent..", Toast.LENGTH_LONG).show()
                    } else {
                        pd.dismiss()
                        Toast.makeText(this@Otpactivity, response.body()!!.message.toString(), Toast.LENGTH_LONG).show()

                    }


                }

            })

        }


    }
    override fun onBackPressed() {
        finish()
    }
}
