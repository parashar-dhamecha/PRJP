package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dxdevil.pd.prjp.Model.Request.Document.NextPage
import com.dxdevil.pd.prjp.Model.Response.Document.NextPage.NextPageResponse
import com.dxdevil.pd.prjp.Model.Response.Document.Preview.PreviewDocResponse
import kotlinx.android.synthetic.main.activity_preview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PreviewActivity : AppCompatActivity() {

    var title:String?=null
    var pageCount:Int=0
    var token:String? =null
    var docId:String?=null
    var fromP:Int?=null
    var toP:Int?=null
    var cpage:Int=1
    var currentpage:Int=0
    var pageNo:Int=1
    var length:Int=5
    var length2:Int=0
    var page : ArrayList<String> = ArrayList()
    lateinit var by:ByteArray
    lateinit var bitmap:Bitmap

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)


        token=getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")
        docId = intent.getStringExtra("doc")

        setTitle(R.string.preview)
        tvSomethingWrong.visibility=View.GONE
        tvRefresh.visibility=View.GONE
        swipeRefreshImage.isEnabled=false
        button_previous.isEnabled=false

        apiPreview(token,docId)

        cpage_number.text=pageNo.toString()

        button_next.setOnClickListener {

                button_previous.isEnabled = true

            currentpage+=1
            if(pageNo%6==0&&pageNo==page.size){

                    fromP=cpage+1
                    toP=cpage+6
                    apiNextPage(token,fromP.toString(),toP.toString(),true)

                } else{
                        by = Base64.decode(page[currentpage], Base64.DEFAULT)
                        bitmap = BitmapFactory.decodeByteArray(by, 0, by.size)
                        img_fileBITMAP.setImageBitmap(bitmap)
                        pageNo = pageNo.inc()

            }
            cpage=cpage.inc()
            cpage_number.text = pageNo.toString()
            if(pageNo==pageCount)
                button_next.isEnabled=false
            }

        button_previous.setOnClickListener {

            button_next.isEnabled=true
              currentpage-=1

            by = Base64.decode(page[currentpage], Base64.DEFAULT)
                    bitmap= BitmapFactory.decodeByteArray(by,0,by.size)
                    img_fileBITMAP.setImageBitmap(bitmap)
                    pageNo=pageNo.dec()
            cpage=cpage.dec()
            cpage_number.text=pageNo.toString()

            if(pageNo==1)
                button_previous.isEnabled=false
        }

        swipeRefreshImage!!.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refreshItem()
            }
            private fun refreshItem() {
                    apiPreview(token,docId)
                itemLoadComplete()
            }
            private fun itemLoadComplete() {
                swipeRefreshImage!!.isRefreshing = false
            }
        })
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
                tvSomethingWrong.visibility=View.VISIBLE
                tvRefresh.visibility=View.VISIBLE
                swipeRefreshImage.isEnabled=true
                button_next.isEnabled=false

            }

            override fun onResponse(call: Call<PreviewDocResponse>, response: Response<PreviewDocResponse>) {
                try{
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                        swipeRefreshImage.isEnabled=false

                        button_next.isEnabled=true
                        length=response.body()!!.data[0].documentData.pages.size
                        length=length.dec()

                        if(length==0)
                            button_next.isEnabled=false

                        title=response.body()!!.data[0].documentData.name
                        setTitle(title)
                        pageCount=response.body()!!.data[0].documentData.pageCount

                        for(i in 0..length)
                        {
                            page.add(i,response.body()!!.data[0].documentData.pages[i])

                        }
                        by = Base64.decode(page[0], Base64.DEFAULT)
                        bitmap= BitmapFactory.decodeByteArray(by,0,by.size)
                        img_fileBITMAP.setImageBitmap(bitmap)

                    }else{
                        if (response.message().toString() == "Unauthorized") {
                            startActivity(Intent(this@PreviewActivity, LoginActivity::class.java))
                        } else {
                            Toast.makeText(this@PreviewActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }catch (e:Exception)
                {
                    dialog.dismiss()
                    Toast.makeText(applicationContext,e.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun apiNextPage(toke:String?,pageFrom:String,pageTo:String,nextORPrevious:Boolean)
    {
        val builder = android.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.progress_message)
        message.text = getString(R.string.Getting)
        builder.setView(dialogView)
        builder.setCancelable(false)
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
                tvSomethingWrong.visibility=View.VISIBLE
                tvRefresh.visibility=View.VISIBLE
            }

            override fun onResponse(call: Call<NextPageResponse>, response: Response<NextPageResponse>) {

                dialog.dismiss()
               if(response.isSuccessful)
               {
                   pageCount=response.body()!!.data[0].pageCount

                   length2=response.body()!!.data[0].pages.size
                   length2=length2.dec()
                   if(length2==0)button_next.isEnabled=false

                   for(i in 0..length2)
                       {
                           page.add(response.body()!!.data[0].pages[i])

                       }
                   by = Base64.decode(page[pageNo], Base64.DEFAULT)
                   bitmap= BitmapFactory.decodeByteArray(by,0,by.size)
                   img_fileBITMAP.setImageBitmap(bitmap)
                    pageNo=pageNo.inc()
                   cpage_number.text=pageNo.toString()

                   dialog.dismiss()
               }
                else
               {
                   dialog.dismiss()
                   if (response.message().toString() == "Unauthorized") {
                       startActivity(Intent(this@PreviewActivity, LoginActivity::class.java))
                   } else {
                       Toast.makeText(this@PreviewActivity, response.message().toString(), Toast.LENGTH_SHORT).show()
                   }
               }
            }
        })}catch (e:Exception){
            dialog.dismiss()
            Toast.makeText(this@PreviewActivity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
