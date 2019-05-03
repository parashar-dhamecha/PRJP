package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.RelativeLayout
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_annotation2.*


@Suppress("ImplicitThis")
class Annotation2 : AppCompatActivity(),View.OnTouchListener {
    lateinit var view: ImageView
    lateinit var root: ViewGroup
    private var _xDelta: Int = 0
    private var _yDelta: Int = 0
     var arr : ArrayList<String> = ArrayList()

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation2)
        root = findViewById(R.id.Relativelid)

        arr.add("Rishabh")
        arr.add("Ashish")
        arr.add("kalpesh")

        var adapter:ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arr)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        signerspinner.adapter=adapter
        view = ImageView(this)
        view.setImageDrawable(getDrawable(R.drawable.logo))

        val layoutParams = RelativeLayout.LayoutParams(500, 200)
        layoutParams.leftMargin = 0
        layoutParams.topMargin = 0
        layoutParams.bottomMargin = -250
        layoutParams.rightMargin = -250
        view.setLayoutParams(layoutParams)
        view.setBackgroundColor(R.color.digitbg)
        view.x= 100F
        view.y=100F
        previewdocid.setImageDrawable(getDrawable(R.drawable.sampledoc))

        addantbutton.setOnClickListener {
            view = addannotatio() as ImageView
            root.addView(view)
            view.setOnClickListener {
                view.setOnTouchListener(this)
            }
        }
        clearantbutton.setOnClickListener {
            root.removeView(view)
        }
        clearallantbutton.setOnClickListener {
            root.removeAllViews()
        }



    }

    private fun addannotatio():View {
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
