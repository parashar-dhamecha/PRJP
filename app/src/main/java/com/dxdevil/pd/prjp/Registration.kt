package com.dxdevil.pd.prjp
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registration.*



val Passwordregex = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@${'$'}!%*?&])[A-Za-z\d@${'$'}!%*?&]{8,}${'$'}""".toRegex()
val Nameregex="""^[A-Za-z]+${'$'}""".toRegex()
val Numberregex="""^[0-9]+$""".toRegex()



class Registration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

//        val etFirstName= findViewById<TextInputEditText>(R.id.etFirstName)
//        val etLastName= findViewById<TextInputEditText>(R.id.etLastName)
//        val etEmail= findViewById<TextInputEditText>(R.id.etEmail)
//        val etMobileNo= findViewById<TextInputEditText>(R.id.etMobileNo)
//        val etPassword= findViewById<TextInputEditText>(R.id.etPassword)
//        val etConfirmPassword= findViewById<TextInputEditText>(R.id.etConfirmPassword)
//        val btregister=findViewById<Button>(R.id.btregister)
//        var loginlink=findViewById<TextView>(R.id.loginlink)

//        var Fname = etFirstName!!.text.toString()
//        var Lname = etLastName!!.text.toString()
//        var email = etEmail!!.text.toString()
//        var MobileNo = etMobileNo!!.text.toString()
//        var Password = etPassword!!.text.toString()
//        var ConfirmPassword = etConfirmPassword!!.text.toString()




        btregister!!.setOnClickListener {

            if(validation())
            {
                startActivity(Intent(applicationContext,LoginActivity::class.java))
            }
        }


    }

    private fun validation():Boolean{

            var valid=false


            val fname= etFirstName!!.text.toString()
            val lname= etLastName!!.text.toString()
            val email= etEmail!!.text.toString()
            val mobileNo= etMobileNo!!.text.toString()
            val password= etPassword!!.text.toString()
            val confirmPassword= etConfirmPassword!!.text.toString()

        var flagFirst =false
        var flagLast =false
        var flagEmail =false
        var flagMobile =false
        var flagPassword =false
        var flagConfirm =false


            if(fname.isEmpty()|| !Nameregex.matches(fname))
            {
                etFirstName!!.error="Enter a valid First Name"
            }
            else {flagFirst =true}


            if(lname.isEmpty()||!Nameregex.matches(lname))
            {
                etLastName!!.error="Enter a valid Last name"
            }
             else{ flagLast=true }

            if(mobileNo.isEmpty()||mobileNo.length>15||!Numberregex.matches(mobileNo))
            {
                etMobileNo!!.error="Enter a valid MobileNo"
            }
            else{flagMobile=true }

          if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
               etEmail!!.error="Enter a valid Email Address"
               valid= false
          }
            else{flagEmail=true}

          if(password.isEmpty() || password.length<8 || !Passwordregex.matches(password)) {

               etPassword!!.error = "Enter a valid Password"
            }

            else
            { flagPassword=true}

            if(confirmPassword.isEmpty() || confirmPassword.length<8|| confirmPassword!=password) {
                etConfirmPassword!!.error = "Password Do not match"
            }
            else
            { flagConfirm=true }

        if(flagFirst&&flagLast&&flagEmail&&flagPassword&&flagConfirm&&flagMobile){valid =true}
        return valid

   }

}
