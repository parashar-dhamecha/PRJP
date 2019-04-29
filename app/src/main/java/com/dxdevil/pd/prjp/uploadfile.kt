package com.dxdevil.pd.prjp

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
import android.content.Context
import android.os.Environment
import android.webkit.MimeTypeMap
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
                "application/vnd.ms-powerpoint",
                "application/x-excel"
                )

            val intent = Intent()
               .setAction(Intent.ACTION_GET_CONTENT).addCategory(Intent.CATEGORY_OPENABLE)
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


    private fun callapi(uri : android.net.Uri) {
        val file = File(getRealPathFromURI(this,uri))
     Toast.makeText(this@uploadfile,MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString())),Toast.LENGTH_LONG).show()

        var rb =RequestBody.create(MediaType.parse(MimeTypeMap.getFileExtensionFromUrl(uri.toString()))
            ,file)
        var rb2 =RequestBody.create(MediaType.parse("multipart/form-data"),file.path)
        var mpb :MultipartBody.Part = MultipartBody.Part.createFormData("file",file.name,rb)
        var token = getSharedPreferences("Token",0).getString("Token","").toString()

        var uapi = RetrofitClient.getInstance().api as Api
        var ucall = uapi.upload(token,mpb) as Call<UploadfileModel>
        ucall!!.enqueue(object : Callback<UploadfileModel>{

            override fun onFailure(call: Call<UploadfileModel>, t: Throwable) {
                Toast.makeText(this@uploadfile,"Something went wrong please try again later" ,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UploadfileModel>, response: Response<UploadfileModel>) {
            if(response.isSuccessful){
                var pagebytes :ArrayList<String>

                Toast.makeText(this@uploadfile, response.body()!!.data[0].name.toString(),Toast.LENGTH_LONG).show()

            }
                else{
                Toast.makeText(this@uploadfile,response.message().toString(),Toast.LENGTH_LONG).show()

            }
            }
        })

    }
    private fun getRealPathFromURI(context: Context, uri: android.net.Uri): String {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = this.getContentResolver().query(uri, filePathColumn, null, null, null)
        cursor.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val filePath = cursor.getString(columnIndex) as String
        cursor.close()
        return filePath
    }


}


