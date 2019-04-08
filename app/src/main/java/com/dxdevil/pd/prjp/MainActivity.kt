
package com.dxdevil.pd.prjp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.Login
import com.dxdevil.pd.prjp.Model.Request.Otp
import com.dxdevil.pd.prjp.Model.Response.LoginModel
import com.dxdevil.pd.prjp.Model.Response.OtpModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({

            var detpreference = getSharedPreferences("Login Details",0)
            var flag = detpreference.getString("rememberflag","0")


            if(flag=="0"){
                var email = detpreference.getString("email","")
                var pass = detpreference.getString("password","")

                var pd = ProgressDialog(this)
                pd.setMessage("Sending Otp..")
                pd.isIndeterminate = true
                pd.show()


                var api4 = RetrofitClient.getInstance().api as Api
                var call2 = api4.login(Login(email, pass)) as Call<LoginModel>
                call2!!.enqueue(object : Callback<LoginModel> {

                    override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                        pd.dismiss()
                        call.cancel()
                        Toast.makeText(this@MainActivity, "check your network connection", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {


                        if (response.isSuccessful) {

                            try {
                                var pref: SharedPreferences = getSharedPreferences("Token", Context.MODE_PRIVATE)
                                var edpref: SharedPreferences.Editor = pref!!.edit()

                                var lm = response.body()
                                var t = "Bearer "+ lm!!.data[0]?.token?.toString()
                                var rt = lm!!.data[0]?.refreshToken?.toString()

                                edpref.putString("Token", t)
                                edpref.putString("RefreshToken", rt)
                                edpref.commit()

                                // Sending otp
                                var api2 = RetrofitClient.getInstance().api as Api
                                var call2 = api2.sendotp(t, Otp("1")) as Call<OtpModel>
                                call2.enqueue(object : Callback<OtpModel> {
                                    override fun onFailure(call: Call<OtpModel>, t: Throwable) {
                                        call2.cancel()
                                        pd.dismiss()
                                        Toast.makeText(
                                            this@MainActivity,
                                            "check your network connection",
                                            Toast.LENGTH_LONG
                                        ).show()

                                    }

                                    override fun onResponse(call: Call<OtpModel>, response: Response<OtpModel>) {
                                        if (response.isSuccessful) {
                                            pd.dismiss()
                                            startActivity(Intent(this@MainActivity, Otpactivity::class.java))
                                            Toast.makeText(this@MainActivity, response.body()!!.message.toString(), Toast.LENGTH_LONG).show()
                                        } else {
                                            pd.dismiss()
                                            Toast.makeText(this@MainActivity, response.body()!!.message.toString(), Toast.LENGTH_LONG).show()

                                        }


                                    }

                                })

                            } catch (e: Exception) {
                                Toast.makeText(this@MainActivity, "exception thrown :$e", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            pd.dismiss()
                            startActivity(Intent(applicationContext,LoginActivity::class.java))
                            Toast.makeText(
                                this@MainActivity,
                                "Something went wrong please try again",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                })

            }
            else{
                startActivity(Intent(applicationContext,LoginActivity::class.java))
            }
        }, 1000)


    }
}





