package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager
import android.util.Log
import android.util.Patterns
import android.view.KeyEvent
import android.widget.EditText
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.Login
import com.dxdevil.pd.prjp.Model.Request.Otp
import com.dxdevil.pd.prjp.Model.Response.LoginModel
import com.dxdevil.pd.prjp.Model.Response.OtpModel

import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*


@SuppressLint("Registered")
public class LoginActivity : AppCompatActivity() {

    var passpattern: Regex = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@${'$'}!%*?&])[A-Za-z\d@${'$'}!%*?&]{8,}${'$'}""".toRegex()
    var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pref = getSharedPreferences("Token", Context.MODE_PRIVATE)
        var edpref1: SharedPreferences.Editor = pref!!.edit()

        var UsernameEmail = findViewById<EditText>(R.id.edEmail)
        var edPassword = findViewById<EditText>(R.id.edPassword)


        textViewforgotpass!!.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
        Login_Button!!.setOnClickListener {
            var email = edEmail!!.text?.toString()
            var password = edPassword!!.text?.toString()

            if (validateemail() && validatepass()) {

                var pd = ProgressDialog(this)
                pd.setCancelable(false)
                pd.setMessage("Sending Otp..")
                pd.isIndeterminate = true
                pd.show()


                var api1 = RetrofitClient.getInstance().api as Api
                var  call = api1.login(Login(email, password)) as Call<LoginModel>

                call!!.enqueue(object : Callback<LoginModel> {

                    override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                        pd.dismiss()
                        call.cancel()
                        Toast.makeText(this@LoginActivity, "check your network connection", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {


                        if (response.isSuccessful) {

                            try {

                                var lm = response.body()
                                lm!!.data[0].isProfileImage
                               var t = "Bearer "+ lm!!.data[0]?.token?.toString()
                                var rt = lm!!.data[0]?.refreshToken?.toString()
                                var userid = lm!!.data[0]?.userId?.toString()
                                edpref1.putString("userid",userid)
                                edpref1.putString("Token", t)
                                edpref1.putString("RefreshToken", rt)
                                edpref1.putBoolean("isprofile",lm!!.data[0].isProfileImage)
                                if(lm!!.data[0].isProfileImage)
                                edpref1.putString("profieimage",lm!!.data[0]!!.profileByte.toString())
                                else
                                    edpref1.putString("profieimage",null)
                                edpref1.putString("fname",lm.data[0].firstName.toString())
                                edpref1.putString("lname",lm.data[0].lastName.toString())
                                edpref1.putString("email",lm.data[0].email.toString())
                                edpref1.apply()

                                //storing details to preference
                                if(remembercb.isChecked){
                                    var detpref = getSharedPreferences("Login Details",0) as SharedPreferences
                                    var ed = detpref.edit()
                                    ed.putString("email",edEmail!!.text.toString())
                                    ed.putString("password",edPassword!!.text.toString())
                                    ed.putString("rememberflag","1")
                                    ed?.apply()
                                }
                                else{
                                    var detpref = getSharedPreferences("Login Details",0) as SharedPreferences
                                    var ed = detpref.edit()
                                    ed.putString("rememberflag","0")
                                    ed.apply()
                                }

                                // Sending otp
                                var api2 = RetrofitClient.getInstance().api as Api
                                var call2 = api2.sendotp(t, Otp("1")) as Call<OtpModel>
                                call2.enqueue(object : Callback<OtpModel> {
                                    override fun onFailure(call: Call<OtpModel>, t: Throwable) {
                                        call2.cancel()
                                        pd.dismiss()
                                        Toast.makeText(
                                            this@LoginActivity,
                                            "check your network connection",
                                            Toast.LENGTH_LONG
                                        ).show()

                                    }

                                    override fun onResponse(call: Call<OtpModel>, response: Response<OtpModel>) {
                                        if (response.isSuccessful) {
                                            pd.dismiss()
                                            var i=Intent(this@LoginActivity, Otpactivity::class.java)
                                            i.putExtra("loginemail",edEmail.text.toString())
                                            startActivity(i)
                                            this@LoginActivity.finish()
                                            Toast.makeText(this@LoginActivity, response.body()!!.message.toString(), Toast.LENGTH_LONG).show()
                                        } else {
                                            pd.dismiss()
                                            Toast.makeText(this@LoginActivity, response.body()!!.message.toString(), Toast.LENGTH_LONG).show()
                                        }
                                    }
                                })

                            } catch (e: Exception) {
                                Toast.makeText(this@LoginActivity, "exception thrown :$e", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            pd.dismiss()
                            Toast.makeText(
                                this@LoginActivity,
                                response.message().toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                })
            }

        }

        registeruser!!.setOnClickListener {
            startActivity(Intent(this@LoginActivity,Registration::class.java))
        }

    }

    override fun onBackPressed() {
        this@LoginActivity.finish()

    }

    fun validateemail(): Boolean {
        var email = edEmail.text.toString()
        if (email == "") {
            edEmail.setError("Email address cant be empty")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Enter a valid email address")
            return false
        } else {
            return true
        }

    }


    fun validatepass(): Boolean {
        val pass = edPassword.text.toString()
        if (pass == "") {
            edPassword.setError("Password cant be empty")
            return false
        } else if (!passpattern.matches(pass)) {
            edPassword.setError("Enter a valid Password")
            return false
        } else {
            return true
        }

    }
    override fun onKeyDown(keycode: Int, event: KeyEvent): Boolean {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
        }
        return super.onKeyDown(keycode, event)
    }
}