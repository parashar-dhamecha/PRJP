package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_login.*

@SuppressLint("Registered")
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var UsernameEmail = findViewById<EditText>(R.id.UsernameEmail)
        var edPassword = findViewById<EditText>(R.id.edPassword)

        var uname = UsernameEmail.text.toString()
        var pass:String = edPassword.text.toString()


        Login_Button.setOnClickListener {
            startActivity(Intent(applicationContext, Dashboard::class.java))
        }

    }
}
