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
import android.widget.Toast
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

            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)

    }
//method 2
    /* fun showPictureDialog() {
        var ad: AlertDialog.Builder = AlertDialog.Builder(this)
        ad.setTitle("choose from")
        var list = arrayOf("Galary","Camera")
        ad.setItems(list) { dialog, which ->
            when(which){
                0->{
                    chooseFromGalary()
                }
                1->{
                    captureFromcamera()
                }
            }

        }


        ad.setNegativeButton("close") { dialog, which ->
            dialog.dismiss()
        }
        ad.show()

    }

    private fun captureFromcamera() {

        try{
            val cani:Intent =Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cani, 1)

        }
    catch (e:Exception){
        Toast.makeText(this,"exception thrown $e",Toast.LENGTH_LONG).show()
        Log.d("5","exception $e")

    }
    }
        fun chooseFromGalary() {
            var galaryintent: Intent =
                Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(galaryintent,"select item from galery"), 0)
        }
*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /*   if (requestCode == 1) {
            try {
                picUri = data!!.data
                CropImage.activity(picUri)
                    .start(this)

            } catch (e: Exception) {
                Log.d("1", "error $e")

            }
        }
        else if(requestCode==0){

            try{
                picUri = data!!.getData()
                performcrop()
        }catch (e:Exception){
                Log.d("2","Exception thrown $e")
            }
        }*/
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
                            if (ob.impressions[0].imageBytes.toString() == null){

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
                                            Toast.makeText(this@PhotoActivity,"Saved successfully..", Toast.LENGTH_LONG).show()
                                        }else{
                                            pd.dismiss()
                                            Snackbar.make(it,response.errorBody().toString(),Snackbar.LENGTH_LONG).show()
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
                                            Toast.makeText(this@PhotoActivity,"Saved successfully..", Toast.LENGTH_LONG).show()
                                        }else{
                                            pd.dismiss()
                                            Snackbar.make(it,response.errorBody().toString(),Snackbar.LENGTH_LONG).show()
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
}
   /* private fun performcrop() {
        try {
            val cropIntent:Intent= Intent("com.android.camera.action.CROP")
            cropIntent.setDataAndType(picUri, "image/*")
            //set ntentcrop properties
            cropI.putExtra("crop", "true")
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 3)
            cropIntent.putExtra("aspectY", 4)
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256)
            cropIntent.putExtra("outputY", 256)
            cropIntent.putExtra("scaleUpIfNeeded", true)
            //retrieve data on return
            cropIntent.putExtra("return-data", true)

            startActivityForResult(cropIntent,2)

        }catch (e:Exception){
            Log.d("3","exception thrown $e")
        }
    }*/