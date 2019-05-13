package com.dxdevil.pd.prjp


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_draw_signature.*
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.util.Base64
import com.dxdevil.pd.prjp.Model.Request.EnrollSignRequest
import com.dxdevil.pd.prjp.Model.Request.UpdateSignature
import com.dxdevil.pd.prjp.Model.Response.EnrollSignModel
import com.dxdevil.pd.prjp.Model.Response.ProfileModel
import com.dxdevil.pd.prjp.Model.Response.UpdateSignatureModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DrawSignature : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_signature)
        var pv: PaintView = findViewById(R.id.paintviewid)
        clearButtonid.setOnClickListener {
            pv.path.reset()
            pv.postInvalidate()
        }
        saveButtonid.setOnClickListener {
            try {

                var bitmap :Bitmap = Bitmap.createBitmap(pv.width,pv.height,Bitmap.Config.ARGB_8888)
                 val canvas = Canvas(bitmap)
                   pv.draw(canvas)
//                      for storing image in internal storage
//                        var filePath: String = Environment.getExternalStorageDirectory().toString()
//
//                filePath += "/sign.png"
//                Log.d("d", filePath)
//                var file: File = File(filePath)
//
//                file.createNewFile()
//                Log.d("2","2")
//                var fileOutputStream: FileOutputStream = FileOutputStream(file)
//                Log.d("1","1")

                var baos :ByteArrayOutputStream = ByteArrayOutputStream()
                bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                var b :ByteArray = baos.toByteArray()
                var signstring = Base64.encodeToString(b,Base64.DEFAULT)

                var pd = ProgressDialog(this)
                pd.setMessage("Saving..")
                pd.isIndeterminate = true
                pd.show()
                var token1 = getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token","").toString()
                var api3 = RetrofitClient.getInstance().api as Api
                var callprofile =api3.getprofiledetails(token1) as Call<ProfileModel>

                callprofile?.enqueue(object : Callback<ProfileModel>{
                    override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                        Toast.makeText(this@DrawSignature,"something went wrong please try again later",Toast.LENGTH_LONG).show()
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
                                        Toast.makeText(this@DrawSignature, "check your network connection", Toast.LENGTH_LONG).show()
                                    }

                                    override fun onResponse(call: Call<EnrollSignModel>, response: Response<EnrollSignModel>) {
                                        if (response.isSuccessful){
                                            var st =  response.body()!!.data.toString()
                                            var by = Base64.decode(st,Base64.DEFAULT)
                                            var bitmap1 =BitmapFactory.decodeByteArray(by,0,by.size) as Bitmap?
                                            pd.dismiss()

                                            startActivity(Intent(this@DrawSignature, Dashboarrd::class.java))

                                            Toast.makeText(this@DrawSignature,"Saved successfully..",Toast.LENGTH_LONG).show()
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
                                    Toast.makeText(this@DrawSignature, "check your network connection", Toast.LENGTH_LONG).show()
                                }

                                override fun onResponse(call: Call<UpdateSignatureModel>, response: Response<UpdateSignatureModel>) {
                                    if (response.isSuccessful){
                                        var st =  response.body()!!.data.toString()
                                        var by = Base64.decode(st,Base64.DEFAULT)
                                        var bitmap1 =BitmapFactory.decodeByteArray(by,0,by.size) as Bitmap?
                                        pd.dismiss()
                                        startActivity(Intent(this@DrawSignature, Dashboarrd::class.java))

                                        Toast.makeText(this@DrawSignature,"Saved successfully..",Toast.LENGTH_LONG).show()
                                    }else{
                                        pd.dismiss()
                                        Snackbar.make(it,response.errorBody().toString(),Snackbar.LENGTH_LONG).show()
                                    }

                                }
                            })
                        }
                    }
                })





//                fileOutputStream.flush()
//
//                // Close the output stream.
//                fileOutputStream.close()
            } catch (e:Exception) {
                Log.d("saving","exception $e")
            }


        }

    }
}