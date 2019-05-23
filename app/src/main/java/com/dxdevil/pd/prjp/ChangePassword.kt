package com.dxdevil.pd.prjp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.ChangePasswordRequest
import com.dxdevil.pd.prjp.Model.Response.ChangePassword.ChangePasswordModel
import kotlinx.android.synthetic.main.activity_change_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//val Passregex = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@${'$'}!%*?&])[A-Za-z\d@${'$'}!%*?&]{8,}${'$'}""".toRegex()

class ChangePassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        btChangePassword.setOnClickListener {
            val CurrentPass = etCurrent!!.text.toString()
            val NewPass = etNew!!.text.toString()
            val ConfirmPass = etConfirm.text.toString()

            if (validation()) {
                val pd = ProgressDialog(this)
                pd.setMessage("Signing Up...")
                pd.isIndeterminate = true
                pd.setCancelable(false)
                pd.show()


                val token: String? =getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")

                val api = RetrofitClient.getInstance().api as Api
                val call = api.changepassword(
                    token,
                    ChangePasswordRequest(CurrentPass, NewPass, ConfirmPass)
                ) as Call<ChangePasswordModel>

                try{
                call.enqueue(object : Callback<ChangePasswordModel> {
                    override fun onFailure(call: Call<ChangePasswordModel>, t: Throwable) {

                        Toast.makeText(this@ChangePassword, "Check your connection", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<ChangePasswordModel>, response: Response<ChangePasswordModel>) {
                        if (response.isSuccessful) {
                            pd.dismiss()
                            Toast.makeText(this@ChangePassword, response.body()!!.message, Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@ChangePassword,LoginActivity::class.java))
                        } else {
                            pd.dismiss()
                            if (response.message().toString() == "Unauthorized") {
                                startActivity(Intent(this@ChangePassword, LoginActivity::class.java))
                            } else {
                                Toast.makeText(this@ChangePassword, "Invalid Current Password", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                })
            }catch (e:Exception)
                {
                    Toast.makeText(this@ChangePassword,e.message,Toast.LENGTH_SHORT).show()
                }
            }


        }

    }

   private fun validation(): Boolean {
        var valid= false
        var CurrentPass = etCurrent!!.text.toString()
        var NewPass = etNew!!.text.toString()
        var ConfirmPass = etConfirm.text.toString()

        var flagCurrent= false
        var flagNew= false
        var flagConfirm= false

        if (CurrentPass.isEmpty() || CurrentPass.length < 8 || !Passwordregex.matches(CurrentPass)) {

            etCurrent!!.error = "Enter a valid Password"
        } else {
            flagCurrent = true
        }

        if (NewPass.isEmpty() || NewPass.length < 8 || !Passwordregex.matches(NewPass)) {

            etNew!!.error = "Enter a valid Password"
        } else {
            flagNew = true
        }

        if (ConfirmPass.isEmpty() || ConfirmPass.length < 8 || ConfirmPass != NewPass) {
            etConfirm!!.error = "Password Do not match"
        } else {
            flagConfirm = true
        }

        if (flagCurrent && flagNew && flagConfirm) valid = true
        return valid

    }


}