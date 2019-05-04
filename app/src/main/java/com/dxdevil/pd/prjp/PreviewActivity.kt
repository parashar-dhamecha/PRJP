package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.TextView
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.Document.NextPage
import com.dxdevil.pd.prjp.Model.Response.Document.NextPage.NextPageResponse
import com.dxdevil.pd.prjp.Model.Response.Document.Preview.PreviewDocResponse
import kotlinx.android.synthetic.main.activity_preview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PreviewActivity : AppCompatActivity() {

    var token:String? =null
    var docId:String?=null
    var fromP:Int?=null
    var toP:Int?=null
    var cpage:Int=1
    var currentpage:Int = 0
    var pageNo:Int=1
    var length:Int=1
    var pageList = arrayOfNulls<String>(10)
    lateinit var by:ByteArray
    lateinit var bitmap:Bitmap

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        token=getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")
        docId = intent.getStringExtra("doc")

        apiPreview(token,docId)

        button_previous.isEnabled=false

//        if(length==1)
//            button_next.isEnabled=false

        cpage_number.text=pageNo.toString()

        try {
            button_next.setOnClickListener {

                button_previous.isEnabled = true

                if(cpage%6==0||currentpage==6){
                    fromP=cpage
                    toP=cpage+6
                    apiNextPage(token,fromP.toString(),toP.toString())
                    // pageNo = pageNo.inc()
                }
                cpage=cpage.inc()
                pageNo= pageNo.inc()
                cpage_number.text = pageNo.toString()


                currentpage+=1
                if(currentpage==6)currentpage=0

                if(currentpage<6){
                    by = Base64.decode(pageList[currentpage], Base64.DEFAULT)
                    bitmap = BitmapFactory.decodeByteArray(by, 0, by.size)
                    img_fileBITMAP.setImageBitmap(bitmap)
                }

            }
        }catch (e:Exception)
        {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }

        button_previous.setOnClickListener {


            if(pageNo==1)
                button_previous.isEnabled=false

            if(cpage%6==1&&cpage!=1){

                fromP=cpage-6
                toP=cpage-1

                apiNextPage(token,fromP.toString(),toP.toString())

            }
                cpage=cpage.dec()
                pageNo=pageNo.dec()
                cpage_number.text=pageNo.toString()


                if(currentpage==-1)currentpage=5
                currentpage-=1

            if(currentpage>=0){
                by = Base64.decode(pageList[currentpage], Base64.DEFAULT)
                bitmap= BitmapFactory.decodeByteArray(by,0,by.size)
                img_fileBITMAP.setImageBitmap(bitmap)
            }


        }

    }

    fun apiPreview(token:String?,docId:String?){

        val api = RetrofitClient.getInstance()!!.api as Api
        val call = api.getdocpreview(token, docId) as Call<PreviewDocResponse>

        val builder = android.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.progress_message)
        message.text = getString(R.string.loading_preview)
        builder.setView(dialogView)
        builder.setCancelable(true)
        val dialog = builder.create()
        dialog.show()

        call.enqueue(object : Callback<PreviewDocResponse> {
            override fun onFailure(call: Call<PreviewDocResponse>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(applicationContext, "Failure", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PreviewDocResponse>, response: Response<PreviewDocResponse>) {
                try{
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()

                        length=response.body()!!.data[0].documentData.pages.size


                        for(i in 0..5)
                        {
                            pageList[i]=response.body()!!.data[0].documentData.pages[i]
                        }
                        by = Base64.decode(pageList[0], Base64.DEFAULT)
                        bitmap= BitmapFactory.decodeByteArray(by,0,by.size)
                        img_fileBITMAP.setImageBitmap(bitmap)

                    }
                }catch (e:Exception)
                {
                    dialog.dismiss()
                    Toast.makeText(applicationContext,e.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun apiNextPage(toke:String?,pageFrom:String,pageTo:String)
    {
        val builder = android.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.progress_message)
        message.text = getString(R.string.Getting)
        builder.setView(dialogView)
        builder.setCancelable(true)
        val dialog = builder.create()
        dialog.show()

        val api = RetrofitClient.getInstance()!!.api as Api
        val call = api.nextPages(token,
            NextPage(
                docId,
                pageFrom,
                pageTo)) as Call<NextPageResponse>
        try{
        call.enqueue(object :Callback<NextPageResponse>{
            override fun onFailure(call: Call<NextPageResponse>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(applicationContext, "Check your Connection", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<NextPageResponse>, response: Response<NextPageResponse>) {

                dialog.dismiss()
               if(response.isSuccessful)
               {
                   length=response.body()!!.data[0].pages.size


                   for(i in 0..5)
                       {
                           pageList[i]=response.body()!!.data[0].pages[i]
                       }
                       pageList[0]=response.body()!!.data[0].pages[0]
                       by = Base64.decode(pageList[0], Base64.DEFAULT)
                       bitmap= BitmapFactory.decodeByteArray(by,0,by.size)
                       img_fileBITMAP.setImageBitmap(bitmap)


                   dialog.dismiss()
                   Toast.makeText(this@PreviewActivity, "Next page successfull", Toast.LENGTH_SHORT).show()
               }
                else
               {
                   dialog.dismiss()
                   Toast.makeText(this@PreviewActivity, "else", Toast.LENGTH_SHORT).show()
               }


            }
        })}catch (e:Exception){
            dialog.dismiss()
            Toast.makeText(this@PreviewActivity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
