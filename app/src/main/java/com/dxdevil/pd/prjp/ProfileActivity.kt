package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.dxdevil.pd.prjp.Model.Request.RefreshToken
import com.dxdevil.pd.prjp.Model.Response.ProfileModel
import com.dxdevil.pd.prjp.Model.Response.RefreshTokenModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.signpopup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        profileapi()


        draw_signature?.setOnClickListener {
            Toast.makeText(applicationContext, "hello", Toast.LENGTH_LONG)
            startActivity(Intent(applicationContext, DrawSignature::class.java))
        }
        photobutton?.setOnClickListener {
            startActivity(Intent(applicationContext, PhotoActivity::class.java))
        }
        typebutton?.setOnClickListener {
            startActivity(Intent(applicationContext, Type::class.java))
        }

        editbuttomid!!.setOnClickListener {
            try {
                var ftm = supportFragmentManager.beginTransaction()
                val cf: ChooseDF = ChooseDF()
                cf.show(ftm, "dialog")
            } catch (e: Exception) {
                Log.d("1", "exception $e")
            }
        }

    }
    fun profileapi(){
        var pd = ProgressDialog(this)
        pd.setMessage("Getting Details..")
        pd.isIndeterminate = true
        pd.show()
        var token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token","")
        var api3 = RetrofitClient.getInstance().api as Api
        var callprofile =api3.getprofiledetails(token) as Call<ProfileModel>

        callprofile?.enqueue(object : Callback<ProfileModel> {

            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                pd.dismiss()
                Toast.makeText(this@ProfileActivity, "check yur connection", Toast.LENGTH_LONG).show()

            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                if (response.isSuccessful) {
                    pd.dismiss()
                    var profileob = response.body()
                    Toast.makeText(this@ProfileActivity, "success", Toast.LENGTH_LONG).show()
                    fullnametextview!!.text =
                        profileob!!.data[0].firstName.toString() + profileob!!.data[0].lastName.toString()
                    emailtv!!.text = profileob!!.data[0].email.toString()
                    jobdescriptiontv!!.text = profileob!!.data[0].jobTitle as String?
                    companeynametv!!.text = profileob!!.data[0].organization as String?
                    phonenotv!!.text = profileob!!.data[0].phoneNumber.toString()
                    //displaying profile pic
                    if(profileob!!.data[0].isProfileImage) {
                        var profilepic = profileob!!.data[0].profileByte.toString()
                        var byap = Base64.decode(profilepic, Base64.DEFAULT)
                        var bitmapprofile = BitmapFactory.decodeByteArray(byap, 0, byap.size) as Bitmap?
                        profilepicv!!.setImageBitmap(bitmapprofile)
                    }
                    //displaying sign
                    if(profileob.data[0].impressions!=null) {
                        var string = profileob!!.data[0].impressions[0].imageBytes
                        var by = Base64.decode(string, Base64.DEFAULT)
                        var bitmap1 = BitmapFactory.decodeByteArray(by, 0, by.size) as Bitmap?
                        signiv.setImageBitmap(bitmap1)
                    }
                    if (profileob!!.data[0].gender == 1) {
                        gendertv!!.text = "Male"
                    } else {
                        gendertv!!.text = "FeMale"
                    }
                } else {
                    pd.dismiss()
                    Toast.makeText(this@ProfileActivity, "error", Toast.LENGTH_LONG).show()
                    refreshtoken()
                }
            }
        })
    }
    fun refreshtoken(){
        var prefer = getSharedPreferences("Token", Context.MODE_PRIVATE)
        var ed = prefer.edit()
        var refreshapi = RetrofitClient.getInstance().api as Api
        var call = refreshapi.refreshtoken(
            RefreshToken(
                getSharedPreferences(
                    "Token",
                    Context.MODE_PRIVATE
                ).getString("Token", "")?.toString(),
                getSharedPreferences("Token", Context.MODE_PRIVATE).getString("RefreshToken", "")?.toString()
            )
        ) as Call<RefreshTokenModel>
        call?.enqueue(object : Callback<RefreshTokenModel> {
            override fun onFailure(call: Call<RefreshTokenModel>, t: Throwable) {
                Toast.makeText(this@ProfileActivity, "check your connection", Toast.LENGTH_LONG).show()
            }


            override fun onResponse(call: Call<RefreshTokenModel>, response: Response<RefreshTokenModel>) {
                if(response.isSuccessful){
                    ed.putString("Token",response.body()!!.data[0].token.toString())
                    ed.putString("RefreshToken",response.body()!!.data[0].refreshToken.toString())
                    ed.commit()
                    Toast.makeText(this@ProfileActivity, "token updated..", Toast.LENGTH_LONG).show()
                    profileapi()
                }else{
                    Toast.makeText(this@ProfileActivity, "error while updating token", Toast.LENGTH_LONG).show()
                }
            }

        })

        }





    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        menuInflater.inflate(R.menu.edit_icon, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.edit_icon ->
                startActivity(Intent(applicationContext, EditProfile::class.java))
        }
        return super.onOptionsItemSelected(item)
    }


}

