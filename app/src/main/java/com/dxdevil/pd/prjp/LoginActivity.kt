package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.Login
import com.dxdevil.pd.prjp.Model.Response.LoginModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("Registered")
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var UsernameEmail = findViewById<EditText>(R.id.UsernameEmail)
        var edPassword = findViewById<EditText>(R.id.edPassword)

        var uname = UsernameEmail.text.toString()
        var pass: String = edPassword.text.toString()


        Login_Button.setOnClickListener {
            startActivity(Intent(applicationContext, Dashboard::class.java))
            Login_Button?.setOnClickListener {



                var call: Call<LoginModel> = RetrofitClient.getInstance().api.login(
                    Login(
                        UsernameEmail.text.toString(),
                        edPassword.text.toString()
                    )
                )

                call.enqueue(object : Callback<LoginModel> {
                    override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "faliure", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {

                        Toast.makeText(this@LoginActivity, "success", Toast.LENGTH_LONG).show()
                    }

                })
            }

        }
    }
}
