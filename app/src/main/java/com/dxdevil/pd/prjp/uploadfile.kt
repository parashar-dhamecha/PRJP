package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.withStyledAttributes
import com.dxdevil.pd.prjp.Model.Response.UploadfileModel
import kotlinx.android.synthetic.main.activity_uploadfile.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class uploadfile : AppCompatActivity() {
    val READ_REQUEST_CODE =42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploadfile)

        uploadfileid.setOnClickListener {
            val mimeTypes = arrayOf(
                "application/pdf",
                "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                )
            val intent = Intent()
               .setAction(Intent.ACTION_GET_CONTENT)
            intent.type = if (mimeTypes.size === 1) mimeTypes[0] else "*/*"
            if (mimeTypes.size > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            }
            startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file
            val file =  File(selectedFile.toString())
            var req :RequestBody=  RequestBody.create(MediaType.parse("*/*"),file)
var mpb:MultipartBody.Part =MultipartBody.Part.createFormData("file", file.getName(), req)
            Toast.makeText(this,selectedFile!!.path.toString(),Toast.LENGTH_LONG).show()
            callapi(uri = selectedFile)
        }
    }

    private fun callapi(uri : Uri) {
        val file = File(uri.toString())
        Toast.makeText(this@uploadfile,file.name.toString(),Toast.LENGTH_LONG).show()

        var rb =RequestBody.create(MediaType.parse(contentResolver.getType(uri).toString()),file)
        var mpb :MultipartBody.Part = MultipartBody.Part.createFormData("file",file.name.toString(),rb)
        var token = getSharedPreferences("Token",0).getString("Token","").toString()

        var uapi = RetrofitClient.getInstance().api as Api
        var ucall = uapi.uploadfile(token,mpb) as Call<UploadfileModel>
        ucall!!.enqueue(object : Callback<UploadfileModel>{
            override fun onFailure(call: Call<UploadfileModel>, t: Throwable) {
//                Toast.makeText(this@uploadfile,"network error",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UploadfileModel>, response: Response<UploadfileModel>) {
            if(response.isSuccessful){
                Toast.makeText(this@uploadfile,"success",Toast.LENGTH_LONG).show()

            }
                else{
                Toast.makeText(this@uploadfile,"error",Toast.LENGTH_LONG).show()

            }
            }
        })
    }
}


