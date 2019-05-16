package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dxdevil.pd.prjp.Model.Request.ForgotRequest
import com.dxdevil.pd.prjp.Model.Response.ForgotResponse
import com.dxdevil.pd.prjp.Model.Response.LoginModel
import kotlinx.android.synthetic.main.activity_forgotpass.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

@SuppressLint("Registered")
class ForgotPasswordActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpass)

        reset.setOnClickListener{
            if(validateemail()){
                var email = edforgotemail.text.toString()
            var api = RetrofitClient.getInstance().api as Api
            var call1 = api.forgotpass(ForgotRequest(email)) as Call<ForgotResponse>
            call1.enqueue(object : retrofit2.Callback<ForgotResponse>{
                override fun onFailure(call: Call<ForgotResponse>, t: Throwable) {
                    Toast.makeText(this@ForgotPasswordActivity,"check your connection",Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<ForgotResponse>, response: Response<ForgotResponse>) {
                   if (response.isSuccessful){
                       startActivity(Intent(applicationContext,LoginActivity::class.java))
                       Toast.makeText(this@ForgotPasswordActivity,response!!.body()!!.message!!.toString(),Toast.LENGTH_LONG).show()
                   }else{
                       if (response.message().toString() == "Unauthorized") {
                           startActivity(Intent(this@ForgotPasswordActivity, LoginActivity::class.java))
                       } else {
                           Toast.makeText(this@ForgotPasswordActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                       }
                   }
                }
            })
        }
    }
    }
    fun validateemail(): Boolean {
        var email = edforgotemail.text.toString()
        if (email.isEmpty()) {
            textInputLayout11.error = "Email address cant be empty"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputLayout11.error = "Enter a valid email address"
            return false
        } else {
            textInputLayout11.error=null
            return true
        }

    }

}