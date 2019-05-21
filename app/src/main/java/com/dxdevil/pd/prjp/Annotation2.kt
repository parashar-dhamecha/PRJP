package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.*
import android.widget.*
import com.dxdevil.pd.prjp.Model.Request.CreateDocRequest
import com.dxdevil.pd.prjp.Model.Request.CreateRequest2
import com.dxdevil.pd.prjp.Model.Request.Document.NextPage
import com.dxdevil.pd.prjp.Model.Request.DocumentShapeModel
import com.dxdevil.pd.prjp.Model.Request.DocumentShapeModelj
import com.dxdevil.pd.prjp.Model.Response.CreateResponse
import com.dxdevil.pd.prjp.Model.Response.Document.NextPage.NextPageResponse
import com.dxdevil.pd.prjp.data.CreateDoc
import kotlinx.android.synthetic.main.activity_annotation2.*
import kotlinx.android.synthetic.main.activity_preview.*
import kotlinx.android.synthetic.main.content_dashboarrd.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("ImplicitThis", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "UNCHECKED_CAST")
class Annotation2 : AppCompatActivity(),View.OnTouchListener{

    var viewarr: ArrayList<TextView> = arrayListOf()
     lateinit var viewpagearr : HashMap<Int,ArrayList<TextView>>
    lateinit var view :TextView
     lateinit var viewcount:MutableList<Int>
    lateinit var root: ViewGroup
    private var _xDelta: Int = 0
    private var _yDelta: Int = 0
     var selsigners : List<String> = mutableListOf(String())
    var signersid : MutableList<String> = mutableListOf()
    var documentshapemodel : List<com.dxdevil.pd.prjp.data.DocumentShapeModel> = mutableListOf(com.dxdevil.pd.prjp.data.DocumentShapeModel(242,31.842105263157894,663,61.50278293135436,
        369,48.55263157894737,104,9.647495361781075,1,"0.612903225806452","ed26c904-dca3-433d-87ab-a5ac2381de27",true,"ESignature"))
    var title:String?=null
    var pageCount:Int=0
    var token:String? =null
    var docId:String?=null
    var fromP:Int?=1
    var toP:Int?=6
    var cpage:Int=1
    var currentpage:Int=0
    var currentpage1:Int=0
    var pageNo:Int=0
    var length2:Int=0
     var ispageannot:Array<Boolean> = arrayOf()
    var page : ArrayList<String> = ArrayList()
    lateinit var by:ByteArray
    lateinit var bitmap: Bitmap

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation2)
        root = findViewById(R.id.Relativelid)
        selsigners=this.intent.getStringArrayListExtra("ssname")
        signersid.add(0,"ed26c904-dca3-433d-87ab-a5ac2381de27")



        var sp = this.getSharedPreferences("CreateDocDetails",0)
        docId= sp.getString("docid","")as String
        token = this.getSharedPreferences("Token",0).getString("Token","").toString()

        prev_button.isEnabled=false


        apiNextPage(token,fromP.toString(),toP.toString())


        next_button!!.setOnClickListener {
            synchronized(this) {
                prev_button.isEnabled = true
                if(ispageannot[currentpage]) {
                    viewpagearr[currentpage] = arrayListOf()
                    viewpagearr[currentpage] = viewarr
                    viewarr = arrayListOf()
                    var fv =  viewcount[currentpage]
                    while (fv > 0) {
                        root.removeView(findViewById((currentpage*100)+fv-1))
                        fv -= 1
                    }
                }

                currentpage += 1
                currentpage1 += 1

                if(ispageannot[currentpage]){
                    var vc=0

                    viewarr = ArrayList()
                   viewarr = viewpagearr[currentpage]!!
                    Toast.makeText(this@Annotation2,viewarr.size.toString(),Toast.LENGTH_LONG).show()
                    var sp = viewcount[currentpage]
                    while(sp>0){
                        root.addView(viewpagearr[currentpage]!!?.get(sp-1))
                        sp-=1
                    }
                }

                if (pageNo % 6 == 0 && pageNo
                    == page.size) {
                    fromP = cpage + 1
                    toP = cpage + 6
                    apiNextPage(token, fromP.toString(), toP.toString())


                } else {
                    by = Base64.decode(page[currentpage], Base64.DEFAULT)
                    bitmap = BitmapFactory.decodeByteArray(by, 0, by.size)
                    previewdocid.setImageBitmap(bitmap)
                    pageNo = pageNo.inc()

                }
                cpage = cpage.inc()
                if (pageNo == pageCount)
                    next_button.isEnabled = false
            }
        }

        prev_button.setOnClickListener {
            synchronized(this) {
                next_button.isEnabled = true
                if(ispageannot[currentpage]) {
                    viewpagearr[currentpage] = arrayListOf()
                    viewpagearr[currentpage] = viewarr
                    viewarr = arrayListOf()

                    var fv =  viewcount[currentpage]

                    while (fv > 0) {
                        root.removeView(findViewById((currentpage*100)+fv-1))
                        fv -= 1
                    }
                }

                currentpage -= 1
                currentpage1 -= 1

                if(ispageannot[currentpage]){

                    viewarr = viewpagearr[currentpage]!!
                    Toast.makeText(this@Annotation2,viewarr.size.toString(),Toast.LENGTH_LONG).show()
                    var sp = viewcount[currentpage]
                    while(sp > 0){
                        root.addView(viewarr[sp-1])
                        sp-=1
                    }
                }

                by = Base64.decode(page[currentpage], Base64.DEFAULT)
                bitmap = BitmapFactory.decodeByteArray(by, 0, by.size)
                previewdocid.setImageBitmap(bitmap)
                pageNo = pageNo.dec()
                cpage = cpage.dec()
                if (pageNo == 1)
                    prev_button.isEnabled = false


            }
        }


        if(selsigners!=null) {
            var adapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, selsigners)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            signerspinner.adapter = adapter!!
        }

         addantbutton.setOnClickListener {
             viewarr.add(viewcount[currentpage], addannotatio())
             viewarr[viewcount[currentpage]]!!.id = (currentpage * 100) + viewcount[currentpage]
             root.addView(viewarr[viewcount[currentpage]])
             viewarr[viewcount[currentpage]]!!.setOnTouchListener(this)
             viewcount.set(currentpage,(viewcount.elementAt(currentpage) + 1))
             if (!ispageannot[currentpage]) {
                 ispageannot[currentpage] = true
             }
         }
        clearantbutton.setOnClickListener {
            synchronized(this) {
                if (viewcount[currentpage] > 0) {
                    root.removeView(findViewById((currentpage*100)+viewcount[currentpage] - 1))
                    viewcount[currentpage] -= 1
                }
            }
        }
      clearallantbutton.setOnClickListener {
         synchronized(this) {
             while (viewcount[currentpage] > 0) {
                 root.removeView(findViewById((currentpage*100)+viewcount[currentpage]-1))
                 viewcount[currentpage] -= 1
             }
         }
      }
    }


    fun apiNextPage(toke:String?,pageFrom:String,pageTo:String)
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
                pageTo)
        ) as Call<NextPageResponse>
        try{
            call.enqueue(object :Callback<NextPageResponse>{
                override fun onFailure(call: Call<NextPageResponse>, t: Throwable) {
                    dialog.dismiss()
                    tvSomethingWrong?.visibility=View.VISIBLE
                    tvRefresh!!.visibility=View.VISIBLE
                }

                override fun onResponse(call: Call<NextPageResponse>, response: Response<NextPageResponse>) {

                    dialog.dismiss()
                    if(response.isSuccessful)
                    {
                        pageCount=response.body()!!.data[0].pageCount

                        length2=response.body()!!.data[0].pages.size
                        length2=length2.dec()
                        if(length2==0)button_next.isEnabled=false

                        for(i in 0..length2) {
                            page.add(response.body()!!.data[0].pages[i])
                        }
                        by = Base64.decode(page[pageNo], Base64.DEFAULT)
                        bitmap= BitmapFactory.decodeByteArray(by,0,by.size)
                        previewdocid.setImageBitmap(bitmap)
                        pageNo=pageNo.inc()

                        viewcount= MutableList(pageCount){0}
                        ispageannot = Array(pageCount){false}
                        viewpagearr = HashMap()
                        dialog.dismiss()
                    }
                    else
                    {
                        dialog.dismiss()
                        if (response.message().toString() == "Unauthorized") {
                            startActivity(Intent(this@Annotation2, LoginActivity::class.java))
                        } else {
                            Toast.makeText(this@Annotation2, response.message().toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })}catch (e:Exception){
            dialog.dismiss()
            Toast.makeText(this@Annotation2, e.message, Toast.LENGTH_SHORT).show()
        }
    }




    @SuppressLint("ResourceType")
   @Synchronized private fun addannotatio():TextView {
        view = TextView(this)
        view.text = signerspinner.selectedItem.toString()
        view.setTextColor(Color.parseColor("#F5E1C1"))
        setLayoutsize(220,100)
        return view
    }

    @SuppressLint("ResourceAsColor")
    private fun setLayoutsize(width:Int,height:Int){
        val layoutParams = RelativeLayout.LayoutParams(width, height)
        layoutParams.leftMargin = 0
        layoutParams.topMargin = 0
        layoutParams.bottomMargin = -250
        layoutParams.rightMargin = -250
        view.setLayoutParams(layoutParams)
        view.setBackgroundColor(R.color.DueSoon)
        view.x= 100F
        view.y=100F
    }



    override fun onTouch(view: View, event: MotionEvent): Boolean {
        val X = event.rawX.toInt()
        val Y = event.rawY.toInt()
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val lParams = view.getLayoutParams() as RelativeLayout.LayoutParams
                _xDelta = X - lParams.leftMargin
                _yDelta = Y - lParams.topMargin

            }


            MotionEvent.ACTION_UP -> {
//                xa.add(view.id, view.x.toInt())
//                ya.add(view.id, view.y.toInt())
//                var rw= view.width/1.4
//                var rh:Double = (view.height/2).toDouble()
//                xp.add(view.id,(((view.x*100)/rw)*root.width)/100)
//                yp.add(view.id,(((view.y*100)/rh)*root.width)/100)
//                wa.add(view.id,view.width)
//               ha.add(view.id,view.height)
//                wp.add(view.id,(100/rw)*100)
//                hp.add(view.id,(100/rh)*100)
//                documentshapemodel[0].x=242
//                documentshapemodel[0].xPercentage=31.002
//                documentshapemodel[0].y=663
//                documentshapemodel[0].yPercentage=38.33
//                documentshapemodel[0].w=369
//                documentshapemodel[0].wPercentage=65.21
//                documentshapemodel[0].h=104
//                documentshapemodel[0].hPercentage=15.22
//                documentshapemodel[0].p=1
//                documentshapemodel[0].ratio="0.612"
//                documentshapemodel[0].userId= signersid?.get(selsigners!!.indexOf(signerspinner!!.selectedItem)).toString()
//                documentshapemodel[0].isAnnotation=true
//                documentshapemodel[0].SignatureType="ESignature"


//                       documentshapemodel.add(view.id, DocumentShapeModel(242,31.002,663,38.33,369,65.21,104
//                    ,15.22,1,"0.612",
//                    signersid?.get(selsigners!!.indexOf(signerspinner!!.selectedItem)),true,"ESignature"))


//            view.setOnTouchListener(null)
//                view.setOnClickListener {
//                }
//                view.setOnTouchListener(this)

                if (view.x<0){
                    view.x = 1f
                }
                if (view.y<0){
                    view.y = 1f
                }
                if (view.y>970){
                    view.y = 970f
                }
                if (view.x>535){
                    view.x = 535f
                }
            }
            MotionEvent.ACTION_POINTER_DOWN -> {

            }
            MotionEvent.ACTION_POINTER_UP -> {

            }
            MotionEvent.ACTION_MOVE -> {
                val layoutParams = view.getLayoutParams() as RelativeLayout.LayoutParams
                layoutParams.leftMargin = X - _xDelta
                layoutParams.topMargin = Y - _yDelta
                layoutParams.rightMargin = -250
                layoutParams.bottomMargin = -250
                xycoordinates.text= "X:${view.x},Y:${view.y}"
                view.setLayoutParams(layoutParams)


            }
        }
        root.invalidate()
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        menuInflater.inflate(R.menu.create, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.create ->{
                var sp = this.getSharedPreferences("CreateDocDetails",0)
               var filename=  sp.getString("filename","")as String
                var des =sp.getString("description","")as String
                var endstartdate =sp.getString("endstartdate","")as String
             var endexpdate  = sp.getString("endexpdate","")as String
            var  sidningduedate=    sp.getString("signingduedate","")as String
            var seqpar=    sp.getString("seqpara","")as String
//            var reminddays =   sp.getString("reminddays","")?.toInt()
              var docid =  sp.getString("docid","")as String
                var totsign= mutableListOf<Int>()
                totsign.add(3)
                var authtype= mutableListOf<Int>()
                authtype.add(1)
                var token = this.getSharedPreferences("Token",0).getString("Token","").toString()


                Toast.makeText(this,docid.toString(),Toast.LENGTH_LONG).show()


           val createapi: Api = RetrofitClient.getInstance().api as Api
                val createcall = createapi.create(
                     CreateDoc(docid, filename,filename,".pdf",
                        "sample doc","2019-05-11 11:42",
                        "2019-05-11 11:42","2019-05-11 11:42",3,
                        documentshapemodel ,2, this!!.signersid as List<String>,
                        null,totsign,
                        4.092356,
                        -56.062161,
                        "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36","61.12.66.6"
                        ,authtype),token)
                 as Call<CreateResponse>

                createcall!!.enqueue(object : Callback<CreateResponse>{
                    override fun onFailure(call: Call<CreateResponse>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<CreateResponse>, response: Response<CreateResponse>) {
                        if(response.isSuccessful){
                            Toast.makeText(this@Annotation2,"successfully created",Toast.LENGTH_LONG).show()
                        }else{
                            if (response.message().toString() == "Unauthorized") {
                                startActivity(Intent(this@Annotation2, LoginActivity::class.java))
                            } else {
                                Toast.makeText(this@Annotation2, response.message().toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })
                }
            }
        return super.onOptionsItemSelected(item)
    }

}

