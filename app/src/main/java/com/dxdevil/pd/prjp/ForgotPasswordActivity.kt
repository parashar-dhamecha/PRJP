package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_forgotpass.*

@SuppressLint("Registered")
class ForgotPasswordActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpass)

        reset.setOnClickListener{
            startActivity(Intent(applicationContext,ResetPasswordActivity::class.java))
        }
    }

}