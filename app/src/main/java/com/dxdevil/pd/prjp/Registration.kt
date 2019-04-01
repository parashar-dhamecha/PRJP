package com.dxdevil.pd.prjp
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dxdevil.pd.prjp.Model.Request.SignUp
import com.dxdevil.pd.prjp.Model.Response.SignUpModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_registration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


val Passwordregex = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@${'$'}!%*?&])[A-Za-z\d@${'$'}!%*?&]{8,}${'$'}""".toRegex()
val Nameregex="""^[A-Za-z]+${'$'}""".toRegex()
val Numberregex="""^[0-9]+$""".toRegex()



class Registration : AppCompatActivity() {

    @SuppressLint("MissingPermission")

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener { location : Location? ->
//                // Got last known location. In some rare situations this can be null.
//               latitude=location?.latitude.toString()
//                longitude=location?.longitude.toString()

//        Toast.makeText(this,fusedLocationClient.lastLocation.l,Toast.LENGTH_SHORT).show()


        var etFirstName = findViewById<TextInputEditText>(R.id.etFirstName)
        var etLastName = findViewById<TextInputEditText>(R.id.etLastName)
        var etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        var etMobileNo = findViewById<TextInputEditText>(R.id.etMobileNo)
        var etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        var etConfirmPassword = findViewById<TextInputEditText>(R.id.etConfirmPassword)
        var btregister = findViewById<Button>(R.id.btregister)
        var loginlink = findViewById<TextView>(R.id.loginlink)

        var Fname = etFirstName!!.text.toString()
        var Lname = etLastName!!.text.toString()
        var email = etEmail!!.text.toString()
        var MobileNo = etMobileNo!!.text.toString()
        var Pass = etPassword!!.text.toString()
        var ConfirmPass = etConfirmPassword!!.text.toString()



        fun validation(): Boolean {

            var valid = false


            val fname =etFirstName.text.toString()
            val lname = etLastName.text.toString()
            val em = etEmail.text.toString()
            val mobileNo = etMobileNo.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            var flagFirst = false
            var flagLast = false
            var flagEmail = false
            var flagMobile = false
            var flagPassword = false
            var flagConfirm = false


            if (fname.isEmpty() || !Nameregex.matches(fname)) {
                etFirstName.error = "Enter a valid First Name"
            } else {
                flagFirst = true
            }


            if (lname.isEmpty() || !Nameregex.matches(lname)) {
                etLastName.error = "Enter a valid Last name"
            } else {
                flagLast = true
            }

            if (mobileNo.isEmpty() || mobileNo.length > 15 || !Numberregex.matches(mobileNo)) {
                etMobileNo.error = "Enter a valid MobileNo"
            } else {
                flagMobile = true
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
                etEmail.error = "Enter a valid Email Address"
                valid = false
            } else {
                flagEmail = true
            }

            if (password.isEmpty() || password.length < 8 || !Passwordregex.matches(password)) {

                etPassword.error = "Enter a valid Password"
            } else {
                flagPassword = true
            }

            if (confirmPassword.isEmpty() || confirmPassword.length < 8 || confirmPassword != password) {
                etConfirmPassword.error = "Password Do not match"
            } else {
                etConfirmPassword.error=null
                flagConfirm = true
            }

            if (flagFirst && flagLast && flagEmail && flagPassword && flagConfirm && flagMobile) {
                valid = true
            }
            return valid

        }
        btregister.setOnClickListener {

            if(validation()) {
                var api = RetrofitClient.getInstance()!!.api as Api
               val call =api!!.registerethod(
                   SignUp( Fname,
                        Lname,
                        email,
                        Pass,
                        ConfirmPass,
                        "Android",
                        "Cygnet",
                        91,
                       MobileNo,
                      null,
                       null,
                        null,
                        null,
                        null)
                   ) as Call<SignUpModel>

                call!!.enqueue(object : Callback<SignUpModel> {
                    override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {

                        try {
                            if (response.isSuccessful) {
                                Log.d("1","error")
                               var  s = response?.body()
                                Log.d("2","error")
                                Toast.makeText(this@Registration, s!!.message, Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@Registration,response!!.body()!!.message, Toast.LENGTH_SHORT).show()
                            }

                        }catch (e:IOException){
                            e.printStackTrace()
                        }

                    }

                    override fun onFailure(call: Call<SignUpModel>, t: Throwable)
                    {

                        Toast.makeText(this@Registration,t.message, Toast.LENGTH_LONG).show()
                    }
                })

            }
        }
    }

}

