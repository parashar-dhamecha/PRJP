package com.dxdevil.pd.prjp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.EnrollSignRequest
import com.dxdevil.pd.prjp.Model.Request.UpdateSignature
import com.dxdevil.pd.prjp.Model.Response.EnrollSignModel
import com.dxdevil.pd.prjp.Model.Response.ProfileModel
import com.dxdevil.pd.prjp.Model.Response.UpdateSignatureModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_type.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.lang.Exception

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Type : AppCompatActivity(), View.OnClickListener {

    var tf:Typeface? = null
    var  bitmap : Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type)
        var ed: TextInputEditText = findViewById(R.id.edfonts)

        var fname =  getSharedPreferences("Token", 0).getString("fname", "").toString()
        edfonts.setText( fname)
        scrolltv1.text = fname

        //tv2
        scrolltv2.text = fname
        tf = Typeface.createFromAsset(assets, "fonts/2.ttf")
        scrolltv2.setTypeface(tf)
        //tv3
        scrolltv3.text = fname
        tf = Typeface.createFromAsset(assets, "fonts/3.ttf")
        scrolltv3.setTypeface(tf)
        //tv4
        scrolltv4.text = fname
        tf = Typeface.createFromAsset(assets, "fonts/4.ttf")
        scrolltv4.setTypeface(tf)
        //tv5
        scrolltv5.text = fname
        tf = Typeface.createFromAsset(assets, "fonts/5.ttf")
        scrolltv5.setTypeface(tf)
        //tv6
        scrolltv6.text = fname
        tf = Typeface.createFromAsset(assets, "fonts/6.ttf")
        scrolltv6.setTypeface(tf)
        //tv7
        scrolltv7.text = fname
        tf = Typeface.createFromAsset(assets, "fonts/7.ttf")
        scrolltv7.setTypeface(tf)


        ed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //tv1
                scrolltv1.text = s

                //tv2
                scrolltv2.text = s
                tf = Typeface.createFromAsset(assets, "fonts/2.ttf")
                scrolltv2.setTypeface(tf)
                //tv3
                scrolltv3.text = s
                tf = Typeface.createFromAsset(assets, "fonts/3.ttf")
                scrolltv3.setTypeface(tf)
                //tv4
                scrolltv4.text = s
                tf = Typeface.createFromAsset(assets, "fonts/4.ttf")
                scrolltv4.setTypeface(tf)
                //tv5
                scrolltv5.text = s
                tf = Typeface.createFromAsset(assets, "fonts/5.ttf")
                scrolltv5.setTypeface(tf)
                //tv6
                scrolltv6.text = s
                tf = Typeface.createFromAsset(assets, "fonts/6.ttf")
                scrolltv6.setTypeface(tf)
                //tv7
                scrolltv7.text = s
                tf = Typeface.createFromAsset(assets, "fonts/7.ttf")
                scrolltv7.setTypeface(tf)
            }
        })
        try {
            scrolltv1.setOnClickListener(this)
            scrolltv2.setOnClickListener(this)
            scrolltv3.setOnClickListener(this)
            scrolltv4.setOnClickListener(this)
            scrolltv5.setOnClickListener(this)
            scrolltv6.setOnClickListener(this)
            scrolltv7.setOnClickListener(this)
        }catch (e:Exception){
            Log.d("1","exception $e")
        }

    }
    override fun onClick(v: View?) {
        try {
        when(v!!.id) {

            R.id.scrolltv1 -> {
                finalsigntv.text = edfonts.text.toString()
                tf = Typeface.createFromAsset(assets, "fonts/1.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                bitmap = finalsigntv.getDrawingCache()

            }
            R.id.scrolltv2 -> {
                finalsigntv.text = edfonts.text.toString()
                tf = Typeface.createFromAsset(assets, "fonts/2.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                bitmap = finalsigntv.getDrawingCache()
            }
            R.id.scrolltv3 -> {
                finalsigntv.setText(edfonts.text.toString())
                tf = Typeface.createFromAsset(assets, "fonts/3.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                bitmap = finalsigntv.getDrawingCache()
            }
            R.id.scrolltv4 -> {
                finalsigntv.setText(edfonts.text.toString())
                tf = Typeface.createFromAsset(assets, "fonts/4.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                bitmap = finalsigntv.getDrawingCache()
            }
            R.id.scrolltv5 -> {
                finalsigntv.setText(edfonts.text.toString())
                tf = Typeface.createFromAsset(assets, "fonts/5.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                bitmap = finalsigntv.getDrawingCache()
            }
            R.id.scrolltv6 -> {
                finalsigntv.setText(edfonts.text.toString())
                tf = Typeface.createFromAsset(assets, "fonts/6.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                bitmap = finalsigntv.getDrawingCache()
            }
            R.id.scrolltv7 -> {
                finalsigntv.setText(edfonts.text.toString())
                tf = Typeface.createFromAsset(assets, "fonts/7.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                bitmap = finalsigntv.getDrawingCache()

            }
        }
    }catch (e:Exception) {
        Log.d("2", "exception $e")
    }
        typesavebutton.setOnClickListener{


            var baos2: ByteArrayOutputStream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, baos2)
            var ba = baos2.toByteArray()
            var signstring = Base64.encodeToString(ba, Base64.DEFAULT)
            //token
            var pd = ProgressDialog(this)
            pd.setMessage("Saving..")
            pd.setCancelable(false)
            pd.isIndeterminate = true
            pd.show()
            var token1 = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token","").toString()
            var api3 = RetrofitClient.getInstance().api as Api
            var callprofile =api3.getprofiledetails(token1) as Call<ProfileModel>

            callprofile?.enqueue(object : Callback<ProfileModel> {
                override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                    Toast.makeText(this@Type,"something went wrong please try again later",Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                    var ob = response.body()!!.data[0]
                    if (ob.impressions== null){

                        var ppref = getSharedPreferences("Token", Context.MODE_PRIVATE)
                        var token = ppref.getString("Token","") as String
                        var updateapi = RetrofitClient.getInstance()!!.api as Api
                        var updatecall1 = updateapi?.enrollsignature(token, EnrollSignRequest(2,signstring )) as Call<EnrollSignModel>

                        updatecall1.enqueue(object : Callback<EnrollSignModel> {
                            override fun onFailure(call: Call<EnrollSignModel>, t: Throwable) {
                                pd.dismiss()
                                Toast.makeText(this@Type, "check your network connection", Toast.LENGTH_LONG).show()
                            }

                            override fun onResponse(call: Call<EnrollSignModel>, response: Response<EnrollSignModel>) {
                                if (response.isSuccessful){
                                    var st =  response.body()!!.data.toString()
                                    var by = Base64.decode(st,Base64.DEFAULT)
                                    var bitmap1 =BitmapFactory.decodeByteArray(by,0,by.size) as Bitmap?
                                    startActivity(Intent(this@Type,Dashboarrd::class.java))
                                    pd.dismiss()
                                    startActivity(Intent(this@Type, Dashboarrd::class.java))
                                    Toast.makeText(this@Type,"Saved successfully..",Toast.LENGTH_LONG).show()
                                }else{
                                    pd.dismiss()
                                    if (response.message().toString() == "Unauthorized") {
                                        startActivity(Intent(this@Type, LoginActivity::class.java))
                                    } else {
                                        Toast.makeText(this@Type, response.message().toString(), Toast.LENGTH_SHORT).show()
                                    }
                                }

                            }
                        })


                    }else{
                        var ppref = getSharedPreferences("Token", Context.MODE_PRIVATE)
                        var token = ppref.getString("Token","") as String
                        var updateapi = RetrofitClient.getInstance()!!.api as Api
                        var updatecall = updateapi?.updatesignature(token, UpdateSignature(2,signstring )) as Call<UpdateSignatureModel>


                        updatecall.enqueue(object : Callback<UpdateSignatureModel> {
                            override fun onFailure(call: Call<UpdateSignatureModel>, t: Throwable) {
                                pd.dismiss()
                                Toast.makeText(this@Type, "check your network connection", Toast.LENGTH_LONG).show()
                            }

                            override fun onResponse(call: Call<UpdateSignatureModel>, response: Response<UpdateSignatureModel>) {
                                if (response.isSuccessful){
                                    var st =  response.body()!!.data.toString()
                                    var by = Base64.decode(st,Base64.DEFAULT)
                                    var bitmap1 =BitmapFactory.decodeByteArray(by,0,by.size) as Bitmap?
                                    startActivity(Intent(this@Type,Dashboarrd::class.java))
                                    pd.dismiss()
                                    startActivity(Intent(this@Type, Dashboarrd::class.java))
                                    Toast.makeText(this@Type,"Saved successfully..",Toast.LENGTH_LONG).show()
                                }else{
                                    pd.dismiss()
                                    if (response.message().toString() == "Unauthorized") {
                                        startActivity(Intent(this@Type, LoginActivity::class.java))
                                    } else {
                                        Toast.makeText(this@Type, response.message().toString(), Toast.LENGTH_SHORT).show()
                                    }                                         }

                            }
                        })
                    }
                }
            })



        }
}

}


