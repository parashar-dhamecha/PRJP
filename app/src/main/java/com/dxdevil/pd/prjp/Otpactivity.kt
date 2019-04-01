package com.dxdevil.pd.prjp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alimuzaffar.lib.pin.PinEntryEditText
import kotlinx.android.synthetic.main.activity_otpactivity.*
import kotlinx.android.synthetic.main.activity_profile.*

class Otpactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)
       var pin = txt_pin_entry!!.text?.toString()
        txt_pin_entry.setOnPinEnteredListener {
            if(txt_pin_entry.text.toString() == "123456")
                startActivity(Intent(this@Otpactivity,Dashboard::class.java))
//            var api = RetrofitClient.getInstance().api
//            var call =  api
        }


    }
}
