package com.dxdevil.pd.prjp
import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dxdevil.pd.prjp.Model.Request.EnrollSignRequest
import com.dxdevil.pd.prjp.Model.Request.UpdateSignature
import com.dxdevil.pd.prjp.Model.Response.EnrollSignModel
import com.dxdevil.pd.prjp.Model.Response.ProfileModel
import com.dxdevil.pd.prjp.Model.Response.UpdateSignatureModel
import com.google.android.material.snackbar.Snackbar
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_photo.*
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)


        if (ContextCompat.checkSelfPermission(this@PhotoActivity, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                100)
        }
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)

        retakephotoid.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            try {

                var result = CropImage.getActivityResult(data)
                iv2.setImageURI(result.uri)

                savephotobutton!!.setOnClickListener {
                    var bitmap2 = MediaStore.Images.Media.getBitmap(this.contentResolver,result.uri)
                    var baos2: ByteArrayOutputStream = ByteArrayOutputStream()
                    bitmap2?.compress(Bitmap.CompressFormat.JPEG, 100, baos2)
                    var ba = baos2.toByteArray()
                    var signstring = Base64.encodeToString(ba, Base64.DEFAULT)
                    //Api caling
                    var pd = ProgressDialog(this)
                    pd.setMessage("Saving..")
                    pd.setCancelable(false)
                    pd.isIndeterminate = true
                    pd.show()
                    var token1 = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token","").toString()
                    var api3 = RetrofitClient.getInstance().api as Api
                    var callprofile =api3.getprofiledetails(token1) as Call<ProfileModel>

                    callprofile?.enqueue(object : Callback<ProfileModel>{
                        override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                            Toast.makeText(this@PhotoActivity,"something went wrong please try again later", Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                            var ob = response.body()!!.data[0]
                            if (ob.impressions== null){

                                var ppref = getSharedPreferences("Token", Context.MODE_PRIVATE)
                                var token = ppref.getString("Token","") as String
                                var updateapi = RetrofitClient.getInstance()!!.api as Api
                                var updatecall1 = updateapi?.enrollsignature(token, EnrollSignRequest(2,signstring )) as Call<EnrollSignModel>

                                updatecall1.enqueue(object : Callback<EnrollSignModel>{
                                    override fun onFailure(call: Call<EnrollSignModel>, t: Throwable) {
                                        pd.dismiss()
                                        Toast.makeText(this@PhotoActivity, "check your network connection", Toast.LENGTH_LONG).show()
                                    }

                                    override fun onResponse(call: Call<EnrollSignModel>, response: Response<EnrollSignModel>) {
                                        if (response.isSuccessful){
                                            var st =  response.body()!!.data.toString()
                                            var by = Base64.decode(st,Base64.DEFAULT)
                                            var bitmap1 =BitmapFactory.decodeByteArray(by,0,by.size) as Bitmap?
                                            pd.dismiss()
                                            startActivity(Intent(this@PhotoActivity, Dashboarrd::class.java))
                                            Toast.makeText(this@PhotoActivity,"Saved successfully..", Toast.LENGTH_LONG).show()
                                        }else{
                                            pd.dismiss()
                                            if (response.message().toString() == "Unauthorized") {
                                                startActivity(Intent(this@PhotoActivity, LoginActivity::class.java))
                                            } else {
                                                Toast.makeText(this@PhotoActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                                            }
                                        }

                                    }
                                })


                            }else{
                                var ppref = getSharedPreferences("Token", Context.MODE_PRIVATE)
                                var token = ppref.getString("Token","") as String
                                var updateapi = RetrofitClient.getInstance()!!.api as Api
                                var updatecall = updateapi?.updatesignature(token, UpdateSignature(2,signstring )) as Call<UpdateSignatureModel>


                                updatecall.enqueue(object : Callback<UpdateSignatureModel>{
                                    override fun onFailure(call: Call<UpdateSignatureModel>, t: Throwable) {
                                        pd.dismiss()
                                        Toast.makeText(this@PhotoActivity, "check your network connection", Toast.LENGTH_LONG).show()
                                    }

                                    override fun onResponse(call: Call<UpdateSignatureModel>, response: Response<UpdateSignatureModel>) {
                                        if (response.isSuccessful){
                                            var st =  response.body()!!.data.toString()
                                            var by = Base64.decode(st,Base64.DEFAULT)
                                            var bitmap1 =BitmapFactory.decodeByteArray(by,0,by.size) as Bitmap?
                                            pd.dismiss()
                                            startActivity(Intent(this@PhotoActivity, Dashboarrd::class.java))
                                            Toast.makeText(this@PhotoActivity,"Saved successfully..", Toast.LENGTH_LONG).show()
                                        }else{
                                            pd.dismiss()
                                            if (response.message().toString() == "Unauthorized") {
                                                startActivity(Intent(this@PhotoActivity, LoginActivity::class.java))
                                            } else {
                                                Toast.makeText(this@PhotoActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                                            }
                                        }

                                    }
                                })
                            }
                        }
                    })




                }

            } catch (e: Exception) {
                Log.d("2", "Exception thrown $e")
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            100 -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Snackbar.make(this.view,"Camera Permission Denied",Snackbar.LENGTH_LONG).show()
                } else {
                    Snackbar.make(this.view,"Camera Permission Granted",Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}
