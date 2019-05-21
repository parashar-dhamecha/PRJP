package com.dxdevil.pd.prjp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.UpdateProfile
import com.dxdevil.pd.prjp.Model.Response.ProfileModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_registration.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class EditProfile : AppCompatActivity() {
lateinit var userid:String
    lateinit var fname:String
    lateinit var lname:String
    lateinit var emailid:String
    lateinit var mobno:String
    lateinit var discription:String
    var gender:Int? = 1
   var countryid:Int= 0
    lateinit var jobtitle:String
    lateinit var organization:String
    var birthday:String? ="1998-05-11"
    var isprofile:Boolean = false
  var profilebytes:String?= null
    lateinit var description:String
    lateinit var token :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        getprofileapi()
        changeimagefb.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)
        }
        removeimage.setOnClickListener {
            isprofile=false
            profilebytes=null
            editprofileiv.setImageDrawable(null)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
        try {

            var result = CropImage.getActivityResult(data)
            editprofileiv.setImageURI(result.uri)

                var bitmap2 = MediaStore.Images.Media.getBitmap(this.contentResolver,result.originalUri)
                var baos2: ByteArrayOutputStream = ByteArrayOutputStream()
                bitmap2?.compress(Bitmap.CompressFormat.PNG, 100, baos2)
                var ba = baos2.toByteArray()
                profilebytes = Base64.encodeToString(ba, Base64.DEFAULT)
            isprofile=true

        } catch (e: Exception) {
            Log.d("2", "Exception thrown $e")
        }
    }
}

    fun getprofileapi(){
        var pd = ProgressDialog(this)
        pd.setMessage("Getting Details..")
        pd.isIndeterminate = true
        pd.show()
        token = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token","")
        var api3 = RetrofitClient.getInstance().api as Api
        var callprofile =api3.getprofiledetails(token) as Call<ProfileModel>

        Toast.makeText(this@EditProfile,"error1", Toast.LENGTH_LONG)

        callprofile?.enqueue(object : Callback<ProfileModel> {

            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                pd.dismiss()
                Toast.makeText(this@EditProfile, "check yur connection", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                if (response.isSuccessful) {
                    pd.dismiss()
                    var profileob = response.body()

                    if(profileob!!.data[0].isProfileImage) {
                        var string1 = profileob!!.data[0].profileByte.toString()
                        var by1 = Base64.decode(string1, Base64.DEFAULT)
                        var bitmap12 = BitmapFactory.decodeByteArray(by1, 0, by1.size)
                        editprofileiv.setImageBitmap(bitmap12)
                    }

                    first_nameedt!!.setText(profileob!!.data[0].firstName.toString())
                    last_nameedt!!.setText(profileob!!.data[0].lastName.toString())
                    emailedt!!.setText(profileob!!.data[0].email.toString())
                    jobdiscedt!!.setText(profileob!!.data[0].jobTitle)
                    orgdetedt!!.setText(profileob!!.data[0].organization )
                    mob_noedt!!.setText(profileob!!.data[0].phoneNumber.toString() )

                    if (profileob!!.data[0].gender == 1) {
                        rbmale.isChecked = true
                    } else {
                        rbfemale.isChecked = true
                    }
                } else {
                    pd.dismiss()
                    Toast.makeText(this@EditProfile, "error", Toast.LENGTH_LONG).show()

                }
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        menuInflater.inflate(R.menu.done, menu)
        return true
    }
    override fun onBackPressed() {
        val intent = Intent(this@EditProfile, ProfileActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.done_icon -> {

                if (validation()){

                val builder = android.app.AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
                val message = dialogView.findViewById<TextView>(R.id.progress_message)

                message.text = getString(R.string.update_profile)
                builder.setView(dialogView)
                builder.setCancelable(true)
                val dialog = builder.create()
                dialog.show()

                userid = getSharedPreferences("Token", 0).getString("userid", "").toString()
                token = getSharedPreferences("Token", 0).getString("Token", "").toString()
                emailid = emailedt!!.text.toString()
                fname = first_nameedt!!.text.toString()
                lname = last_nameedt!!.text.toString()
                description = descriptionedt!!.text.toString()
                if (rbmale.isChecked) gender = 1
                else gender = 0
                countryid = 91
                jobtitle = jobdiscedt!!.text.toString()
                organization = orgdetedt!!.text.toString()
                mobno = mob_noedt!!.text.toString()
                birthday = "1995-10-16T00:00:00"
                if (!isprofile) {
                    profilebytes = null
                }


                var apiupdate = RetrofitClient.getInstance().api as Api
                var callupdate = apiupdate.updateprofile(
                    token,
                    userid,
                    UpdateProfile(
                        userid,
                        emailid,
                        fname,
                        lname,
                        description,
                        gender,
                        countryid,
                        jobtitle,
                        organization,
                        mobno,
                        birthday,
                        isprofile,
                        "India Standard Time",
                        profilebytes
                    )
                )
                        as Call<ResponseBody>
                callupdate!!.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        dialog.dismiss()
                        Toast.makeText(this@EditProfile, "check your connection", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            dialog.dismiss()
                            Toast.makeText(this@EditProfile, "Profile updated successfully", Toast.LENGTH_LONG).show()

                            val intent = Intent(this@EditProfile, ProfileActivity::class.java)
                            startActivity(intent)
                        } else {
                            dialog.dismiss()
                            Toast.makeText(this@EditProfile, "somethiing went wrong", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }

        }

        }
        return super.onOptionsItemSelected(item)
    }
    private fun validation(): Boolean {

        var valid = false


        val fname =first_nameedt.text.toString()
        val lname = last_nameedt.text.toString()
        val email=emailedt.text.toString()
        val mobile=mob_noedt.text.toString()
        val jt =jobdiscedt.text.toString()
        val organi=orgdetedt.text.toString()
        val desc=descriptionedt.text.toString()



        var flagFirst = false
        var flagLast = false
        var flagEmail = false
        var flagMobile = false
        var flagjt=false
        var flagORG =false
        var flagDES=false


        if (fname.isEmpty() || !Nameregex.matches(fname)) {
            tilFirstName.error="Enter a valid First Name"
        } else {
            tilFirstName.error= null
            flagFirst = true
        }

        if (lname.isEmpty() || !Nameregex.matches(lname)) {
            tilLastName!!.error = "Enter a valid Last name"
        } else {
            tilLastName!!.error = null
            flagLast = true
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail!!.error = "Enter a valid Email Address"

        } else {
            tilEmail!!.error =null
            flagEmail=true
        }


        if (jt.isEmpty() ||!Nameregex.matches(jt)) {

            tilJobTitle.error = "Enter a valid job title"
        } else {
            tilJobTitle.error = null
            flagjt = true
        }

        if (organi.isEmpty() ||!Nameregex.matches(organi)) {

            tilOrganization.error = "Enter a valid company name"
        } else {
            flagORG= true
            tilOrganization.error=null
        }

        if (mobile.isEmpty() || mobile.length > 15 || !Numberregex.matches(mobile)) {

            tilMobile.error = "Enter a valid MobileNo"
        } else {
            tilMobile.error = null
            flagMobile = true
        }

        if (desc.isEmpty() ||!Nameregex.matches(desc)) {

            tilDescription.error = "Enter a valid Description"
        } else {
            flagDES= true
            tilDescription.error=null
        }



        if(flagFirst && flagLast && flagEmail && flagMobile && flagjt && flagORG&& flagDES ) {
            valid = true
        }

        return valid
    }

}
