package com.dxdevil.pd.prjp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
            var CurrentPass = etCurrent!!.text.toString()
            var NewPass = etNew!!.text.toString()
            var ConfirmPass = etConfirm.text.toString()

            if (validation()) {
                val pd = ProgressDialog(this)
                pd.setMessage("Signing Up...")
                pd.isIndeterminate = true
                pd.show()

                var preference = getSharedPreferences("Token", Context.MODE_PRIVATE) as SharedPreferences
                var token: String =
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.N9w0jV/v+9PKjqTQVRprezt4+qqzzgZZjY68hHbb/VdLYi/P2x192in+N/puRDAp8zEKMMqdnOPVvgPE6C6bukPznNX+g+jkb5sv8FfeS7Q7UXY/QIHjeYLQJ4Tk0Aziq/VdzyrDQLOq20vUdEYJjwIBiq1pk+R/7xBYPpEEPI6lQtTxggOw72uJnYt/kwviC0i6ppoGmWZHU5sX4ZyNBnhSp5RPMYvQKENl5w3MdkGet6cb9MaMD55LM0Ytqy2DMHMqW4H1t4ql13hFw3c/1MhX/sNci8G6WNp1Bo6qnFwi+eu6ABRNaQ0C4lXKjy4K/9Mj9ZN6dqQLCWdpsbW6Bx1K46PitJsHNo6bqFVlUWyxRHNeIJKu655Ema646p9o62EeO4LDIrUhOJLPMpXXDwbdcgHAsLBpM/TMfOdQlaLsB2d6rOlkqlh0n8ForetQ+M19xiDH0C+YxrDeDlnY6v62VfXDwwayEVEgmjZv2SM/CKPoyQgHsYEh3tGXECqP9UVxmqL+jPbLhIrTlku4Z1LsZXmRVc/gEq1/NJPIU7DtF1PEkfIs4VUHuwGClKyyQ2GfWmecbn5RZ4WF4HZpOr8KUDo5TsLVee+4w0W75MRPSaQZVRBz/AaW5owhvQ1DsBzHmO01XfKxj5oXASQUaSOA6OoAyFgrA+JHMi3g10sjOVFPhiHSDyOPenpYcw7rrxB7pXpVEdnW1gen3oT5BSJ+PzMfFkt2sg0Mg4KRFMMG0/cG6wP5C1W2gKQT2hVENRxfTYeifEjLE0oTVot9g+xlczLrrvAjqJS9SuHk+nEGX+WyninWtSctvVhYteGNFEt1+248GW3OEl0mPVHeob9BERN2k8uCJaIPJVBV9/J5CYSKf8KviTl6tCArkm6O.u7xWXY-DHq4VwYYjKMoiN16OkkEAKgQ3VN1sLMSWDNg"
                var tok = preference.getString("Token", "")!!.toString() as String?

                var api = RetrofitClient.getInstance().api as Api
                var call = api.changepassword(
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
                            Toast.makeText(this@ChangePassword, "Invalid Current Password", Toast.LENGTH_LONG).show()
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