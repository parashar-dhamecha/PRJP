package com.dxdevil.pd.prjp


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_forgotpass.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity(): AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        if(validation()){
            btnRESET.setOnClickListener {
                Toast.makeText(this@ResetPasswordActivity,"buttton clicked", Toast.LENGTH_SHORT).show()
            }
        }




    }

    fun validation():Boolean{
        var valid =false
        var flagEmail=false
        var flagPASS=false
        var flagCPASS=false

        val em=email_reset.text.toString()
        val password=password_reset.text.toString()
        val confirmPassword=cPass_reset.text.toString()

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
            textInputLayout14!!.error = "Enter a valid Email Address"
            valid = false
        } else {
            flagEmail=true
            textInputLayout14!!.error =null
        }

        if (password.isEmpty() || password.length < 8 || !Passwordregex.matches(password)) {

            textInputLayout15.error = "Enter a valid Password"
        } else {
            textInputLayout15.error = null
            flagPASS = true
        }

        if (confirmPassword.isEmpty() || confirmPassword.length < 8 || confirmPassword != password) {
            textInputLayout12.error = "Password Do not match"
        } else {
            textInputLayout12.error = null
            flagCPASS = true
        }

        if(flagEmail&&flagCPASS&&flagPASS)
            valid=true
        return valid
    }
}