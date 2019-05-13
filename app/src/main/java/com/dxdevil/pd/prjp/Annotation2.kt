package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.*
import android.widget.*
import com.dxdevil.pd.prjp.Model.Request.CreateRequest
import com.dxdevil.pd.prjp.Model.Request.Document.NextPage
import com.dxdevil.pd.prjp.Model.Request.DocumentShapeModel
import com.dxdevil.pd.prjp.Model.Response.CreateResponse
import com.dxdevil.pd.prjp.Model.Response.Document.NextPage.NextPageResponse
import kotlinx.android.synthetic.main.activity_annotation2.*
import kotlinx.android.synthetic.main.activity_preview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("ImplicitThis", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "UNCHECKED_CAST"
)
class Annotation2 : AppCompatActivity(),View.OnTouchListener{


    lateinit var viewarr: ArrayList<ImageView>
    lateinit var view :ImageView
     var viewcount: Int = 0
    lateinit var root: ViewGroup
    private var _xDelta: Int = 0
    private var _yDelta: Int = 0
     var selsigners : List<String>? = mutableListOf<String>(String())
    var signersid : List<String>? = mutableListOf(String())
    var documentshapemodel : List<DocumentShapeModel> = mutableListOf(DocumentShapeModel("ESignature",104,9.647495361781075,true
    ,2,"0.612","9333F719-6C51-4067-B9AC-C9733A79A392",369,48.55263157894737,242,31.84210526357894,
        663,61.50278293135436))
    var title:String?=null
    var pageCount:Int=0
    var token:String? =null
    var docId:String?=null
    var fromP:Int?=1
    var toP:Int?=6
    var cpage:Int=1
    var currentpage:Int=0
    var pageNo:Int=0
    var length2:Int=0
    var page : ArrayList<String> = ArrayList()
    lateinit var by:ByteArray
    lateinit var bitmap: Bitmap

//    var xa :ArrayList<Int> = ArrayList()
//    var ya :ArrayList<Int> = ArrayList()
//    var wa :ArrayList<Int> = ArrayList()
//    var ha :ArrayList<Int> = ArrayList()
//    var xp :ArrayList<Double> = ArrayList()
//    var yp :ArrayList<Double> = ArrayList()
//    var wp :ArrayList<Double> = ArrayList()
//    var hp :ArrayList<Double> = ArrayList()



    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation2)
        root = findViewById(R.id.Relativelid)
       selsigners=this.intent.getStringArrayListExtra("ssname")
        signersid=this.intent.getStringArrayListExtra("ssid")
        viewarr=ArrayList<ImageView>()

        var sp = this.getSharedPreferences("CreateDocDetails",0)
       docId= sp.getString("docid","")as String
        token = this.getSharedPreferences("Token",0).getString("Token","").toString()
        prev_button.isEnabled=false

        apiNextPage(token,fromP.toString(),toP.toString())


        next_button!!.setOnClickListener {

            prev_button.isEnabled = true

            currentpage+=1
            if(pageNo%6==0&&pageNo==page.size){

                fromP=cpage+1
                toP=cpage+6
                apiNextPage(token,fromP.toString(),toP.toString())

            } else{
                by = Base64.decode(page[currentpage], Base64.DEFAULT)
                bitmap = BitmapFactory.decodeByteArray(by, 0, by.size)
                previewdocid.setImageBitmap(bitmap)
                pageNo = pageNo.inc()

            }
            cpage=cpage.inc()
//            cpage_number.text = pageNo.toString()
            if(pageNo==pageCount)
                next_button.isEnabled=false
        }

        prev_button.setOnClickListener {

            next_button.isEnabled=true
            currentpage-=1

            by = Base64.decode(page[currentpage], Base64.DEFAULT)
            bitmap= BitmapFactory.decodeByteArray(by,0,by.size)
            previewdocid.setImageBitmap(bitmap)
            pageNo=pageNo.dec()
            cpage=cpage.dec()

            if(pageNo==1)
                prev_button.isEnabled=false
        }



        if(selsigners!=null) {
            var adapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, selsigners)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            signerspinner.adapter = adapter!!
        }


         addantbutton.setOnClickListener {
             synchronized(this) {
                 viewarr.add( addannotatio() as ImageView)
                 viewarr[viewcount].id= viewcount
                 root.addView(viewarr[viewcount])
                 viewarr[viewcount].setOnTouchListener(this)
                 viewcount+=1
             }
         }
        clearantbutton.setOnClickListener {
            synchronized(this) {
                if (viewcount > 0) {
                    root.removeView(findViewById(viewcount - 1))
                    viewcount -= 1
                }
            }
        }
      clearallantbutton.setOnClickListener {
         synchronized(this) {
             while (viewcount > 0) {
                 root.removeView(findViewById(viewcount-1))
                 viewarr.removeAt(viewcount-1)
                 viewcount -= 1
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

                        for(i in 0..length2) {
                            page.add(response.body()!!.data[0].pages[i])
                        }
                        by = Base64.decode(page[pageNo], Base64.DEFAULT)
                        bitmap= BitmapFactory.decodeByteArray(by,0,by.size)
                        previewdocid.setImageBitmap(bitmap)
                        pageNo=pageNo.inc()

                        dialog.dismiss()
                    }
                    else
                    {
                        dialog.dismiss()
                        Toast.makeText(this@Annotation2, response.message().toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })}catch (e:Exception){
            dialog.dismiss()
            Toast.makeText(this@Annotation2, e.message, Toast.LENGTH_SHORT).show()
        }
    }




    @SuppressLint("ResourceType")
   @Synchronized private fun addannotatio():View {
        view = ImageView(this)
        view.setImageDrawable(getDrawable(R.drawable.logo))
        setLayoutsize(250,110)
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
                if(view.x<=1){
                    view.x= 1F
                }
                if(view.y<=1){
                    view.y= 1F
                }
                if(view.x>=505) {
                    view.x = 505F

                }
                if(view.y>=965){
                    view.y=965F
                }
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

                 Toast.makeText(this@Annotation2,view.x.toString(),Toast.LENGTH_LONG).show()
                if(view.x<=1){
                    view.x= 1F
                }
                if(view.y<=1){
                    view.y= 1F
                }
                if(view.x>=505){
                    view.x= 505F
                }
                if(view.y>=965){
                    view.y=965F
                }

//            view.setOnTouchListener(null)
//                view.setOnClickListener {
//                }
//                view.setOnTouchListener(this)
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
                if(view.x<=1){
                    view.x= 1F
                }
                if(view.y<=1){
                    view.y= 1F
                }
                if(view.x>=505){
                    view.x= 505F
                }
                if(view.y>=965){
                    view.y=965F
                }
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
                var totsign= mutableListOf<Int>(3)
                var authtype= mutableListOf<Int>(1)
                var token = this.getSharedPreferences("Token",0).getString("Token","").toString()


                Toast.makeText(this,docid.toString(),Toast.LENGTH_LONG).show()


           val createapi: Api = RetrofitClient.getInstance().api as Api
                val createcall = createapi.create(
                    token, CreateRequest(authtype,
                        des,
                        4.092356,
                        -56.062161,documentshapemodel ,
                        "2019-05-11 11:42",
                        "2019-05-11 11:42",
                        ".pdf",
                        filename,filename,null,docid,3,totsign, this!!.signersid as List<String>,"2019-05-11 11:42"
                    ,2,"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36","61.12.66.6"))as Call<CreateResponse>


                createcall!!.enqueue(object : Callback<CreateResponse>{
                    override fun onFailure(call: Call<CreateResponse>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<CreateResponse>, response: Response<CreateResponse>) {
                        if(response.isSuccessful){
                            Toast.makeText(this@Annotation2,"successfully created",Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(this@Annotation2,response.errorBody().toString()+""+response.message().toString(),Toast.LENGTH_LONG).show()

                        }
                    }
                })


                }


            }

        return super.onOptionsItemSelected(item)
    }

}

