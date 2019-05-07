package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_annotation2.*


@Suppress("ImplicitThis", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class Annotation2 : AppCompatActivity(),View.OnTouchListener {
    lateinit var viewarr: ArrayList<ImageView>
    lateinit var view :ImageView
     var viewcount: Int = 0
    lateinit var root: ViewGroup
    private var _xDelta: Int = 0
    private var _yDelta: Int = 0
     var selsigners : ArrayList<String>? = ArrayList()
    var signersid : ArrayList<String>? = ArrayList()



    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation2)
        root = findViewById(R.id.Relativelid)
       selsigners=this.intent.getStringArrayListExtra("ssname")
        signersid=this.intent.getStringArrayListExtra("ssid")
        viewarr=ArrayList<ImageView>()

        if(selsigners!=null) {
            var adapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, selsigners)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            signerspinner.adapter = adapter!!
            val contains = selsigners!!.indexOf(signerspinner.selectedItem)
        }
//        view = ImageView(this)

//        val layoutParams = RelativeLayout.LayoutParams(500, 200)
//        layoutParams.leftMargin = 0
//        layoutParams.topMargin = 0
//        layoutParams.bottomMargin = -250
//        layoutParams.rightMargin = -250
//        view.setLayoutParams(layoutParams)
//        view.setBackgroundColor(R.color.digitbg)
//        view.x= 100F
//        view.y=100F
        previewdocid.setImageDrawable(getDrawable(R.drawable.sampledoc))

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
                root.removeView(findViewById(viewcount-1))
                viewcount-=1
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

    @SuppressLint("ResourceType")
   @Synchronized private fun addannotatio():View {
        view = ImageView(this)
        view.setImageDrawable(getDrawable(R.drawable.logo))
        setLayoutsize(400,170)
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
    }}
