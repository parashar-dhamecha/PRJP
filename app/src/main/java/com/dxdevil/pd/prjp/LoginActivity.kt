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
import android.widget.EditText
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.Login
import com.dxdevil.pd.prjp.Model.Response.LoginModel

import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


@SuppressLint("Registered")
public class LoginActivity : AppCompatActivity() {

    var passpattern: Regex =
        """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@${'$'}!%*?&])[A-Za-z\d@${'$'}!%*?&]{8,}${'$'}""".toRegex()
    var pref: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pref = getSharedPreferences("Token", Context.MODE_PRIVATE)
        var edpref: SharedPreferences.Editor = pref!!.edit()

        var UsernameEmail = findViewById<EditText>(R.id.edEmail)
        var edPassword = findViewById<EditText>(R.id.edPassword)

        var uname = UsernameEmail.text.toString()
        var pass: String = edPassword.text.toString()

        textViewforgotpass!!.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
        Login_Button!!.setOnClickListener {
            var email = edEmail!!.text?.toString()
            var password = edPassword!!.text?.toString()

            if (validateemail() && validatepass()) {

                var pd = ProgressDialog(this)
                pd.setMessage("Loading...")
                pd.setTitle("Logging in..")
                pd.isIndeterminate = true
                pd.show()


                var api1 = RetrofitClient.getInstance().api as Api
                var  call = api1.login(Login(email, password)) as Call<LoginModel>

                call!!.enqueue(object : Callback<LoginModel> {

                    override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                        call.cancel()
                        pd.dismiss()
                        Toast.makeText(this@LoginActivity, "faliure", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {


                        if (response.isSuccessful) {

                            try {

                                var lm = response.body()
                                var t = lm!!.data[0]?.token?.toString()
                                var rt = lm!!.data[0]?.refreshToken?.toString()

                                edpref.putString("Token", t)
                                edpref.putString("RefreshToken", rt)

                                pd.dismiss()

                                edpref?.commit()

                                startActivity(Intent(this@LoginActivity, Otpactivity::class.java))
                                Toast.makeText(this@LoginActivity, lm.message.toString(), Toast.LENGTH_LONG).show()

                            } catch (e: Exception) {
                                Toast.makeText(this@LoginActivity, "exception thrown :$e", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            pd.dismiss()
                            Toast.makeText(
                                this@LoginActivity,
                                "Something went wrong please try again",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                })
            }

        }
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

  public  fun getsp(): SharedPreferences? {
        return pref
    }
}
