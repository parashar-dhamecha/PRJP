package com.dxdevil.pd.prjp

import `in`.gauriinfotech.commons.Commons
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Response.UploadfileModel
import kotlinx.android.synthetic.main.activity_uploadfile.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import android.provider.MediaStore
import android.provider.DocumentsContract
import android.content.Context
import android.app.Activity
import android.database.Cursor
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.loader.content.CursorLoader
import java.io.FileOutputStream
import java.util.regex.Pattern
import android.net.Uri as Uri1


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class uploadfile : AppCompatActivity() {
    val READ_REQUEST_CODE =42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploadfile)

        uploadfileid!!.setOnClickListener {
            val mimeTypes = arrayOf(
                "application/pdf",
                "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/zip",
                "application/vnd.ms-powerpoint",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/x-excel"
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
            val selectedFile = data?.data
            if (selectedFile != null) {
                callapi(uri = selectedFile)
            }
        }
    }


    private fun callapi(uri : Uri1) {
        val file = File(Environment.getExternalStorageDirectory(),uri.path)
     Toast.makeText(this@uploadfile,file.path,Toast.LENGTH_LONG).show()

        var rb =RequestBody.create(MediaType.parse("multipart/form-data"),uri.path)
        var mpb :MultipartBody.Part = MultipartBody.Part.createFormData("file",file.name,rb)
        var token = getSharedPreferences("Token",0).getString("Token","").toString()

        var uapi = RetrofitClient.getInstance().api as Api
        var ucall = uapi.upload(token,mpb) as Call<UploadfileModel>
        ucall!!.enqueue(object : Callback<UploadfileModel>{

            override fun onFailure(call: Call<UploadfileModel>, t: Throwable) {
//                Toast.makeText(this@uploadfile,"Something went wrong please try again later" ,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UploadfileModel>, response: Response<UploadfileModel>) {
            if(response.isSuccessful){
                Toast.makeText(this@uploadfile,"success",Toast.LENGTH_LONG).show()

            }
                else{
//                Toast.makeText(this@uploadfile,response.message().toString(),Toast.LENGTH_LONG).show()

            }
            }
        })
//        var file = File(uri!!.path)
//        var rb = RequestBody.create(MediaType.parse("multipart/form-data"),uri.path)
//        var mpb = MultipartBody.Part.createFormData("file",file.name,rb)
//        var token = getSharedPreferences("Token",0).getString("Token","").toString()
//
//        var uploadapi =  RetrofitClient.getInstance().api as Api
//        var upload = uploadapi.upload(token,mpb) as Call<UploadfileModel>
//        upload.enqueue(object : Callback<UploadfileModel>{
//            override fun onFailure(call: Call<UploadfileModel>, t: Throwable) {
//                Toast.makeText(this@uploadfile,"Something went wrong please try again later" ,Toast.LENGTH_LONG).show()
//
//            }
//
//            override fun onResponse(call: Call<UploadfileModel>, response: Response<UploadfileModel>) {
//                if(response.isSuccessful){
//                    Toast.makeText(this@uploadfile,"success",Toast.LENGTH_LONG).show()
//
//                }
//                else{
//                    Toast.makeText(this@uploadfile,response.message().toString(),Toast.LENGTH_LONG).show()
//
//                }
//            }
//        })

    }
    private fun getRealPathFromURI(context: Context, uri: android.net.Uri): String {
        var ret = ""

        // Query the uri with condition.
        val cursor: Cursor = contentResolver.query(uri, null,null, null, null)

        if (cursor != null) {
            val moveToFirst = cursor.moveToFirst()
            if (moveToFirst) {

                // Get columns name by uri type.
                var columnName = MediaStore.Images.Media.DATA

                if (uri === MediaStore.Images.Media.EXTERNAL_CONTENT_URI) {
                    columnName = MediaStore.Images.Media.DATA
                } else if (uri === MediaStore.Audio.Media.EXTERNAL_CONTENT_URI) {
                    columnName = MediaStore.Audio.Media.DATA
                } else if (uri === MediaStore.Video.Media.EXTERNAL_CONTENT_URI) {
                    columnName = MediaStore.Video.Media.DATA
                }

                // Get column index.
                val columnIndex = cursor.getColumnIndex(columnName)

                // Get column value which is the uri related file local path.
                ret = cursor.getString(columnIndex)
            }
        }

        return ret
    }


}


