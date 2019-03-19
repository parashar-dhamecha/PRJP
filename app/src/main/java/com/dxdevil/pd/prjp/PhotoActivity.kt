package com.dxdevil.pd.prjp
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_photo.*


//var picUri: Uri? =null

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        choosebutton.setOnClickListener {
            //  showPictureDialog()
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)
            //showPictureDialog()
        }
    }

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
                iv.setImageURI(result.uri)
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