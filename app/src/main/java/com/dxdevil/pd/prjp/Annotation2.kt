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
import kotlinx.android.synthetic.main.activity_annotation2.*


@Suppress("ImplicitThis")
class Annotation2 : AppCompatActivity(),View.OnTouchListener {
    lateinit var view: ImageView
    lateinit var view1: ImageView
    lateinit var root: ViewGroup
    lateinit var root2: ViewGroup
    private var _xDelta: Int = 0
    private var _yDelta: Int = 0
    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation2)
        root = findViewById(R.id.Relativelid)

        view = ImageView(this)
        view.setImageDrawable(getDrawable(R.drawable.logo))
        view1 = ImageView(this)
        view1.setImageDrawable(getDrawable(R.drawable.logo))

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
        view.setOnClickListener {
    view.setOnTouchListener(this)
       }

        root.addView(view)
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
