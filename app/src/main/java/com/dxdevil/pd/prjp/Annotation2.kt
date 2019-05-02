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


@Suppress("ImplicitThis")
class Annotation2 : AppCompatActivity(),View.OnTouchListener {
    lateinit var view: ImageView
    lateinit var view1: ImageView
    lateinit var root: ViewGroup
    private var _xDelta: Int = 0
    private var _yDelta: Int = 0
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation2)
        root = findViewById<ViewGroup>(R.id.rootview)
        view = ImageView(this)
        view.setImageDrawable(getDrawable(R.drawable.logo))
        view1 = ImageView(this)
        view1.setImageDrawable(getDrawable(R.drawable.logo))

        val layoutParams = RelativeLayout.LayoutParams(500,200)
        layoutParams.leftMargin = 50
        layoutParams.topMargin = 50
        layoutParams.bottomMargin = -250
        layoutParams.rightMargin = -250
        view.setLayoutParams(layoutParams)
        view1.setLayoutParams(layoutParams)


        view.setOnTouchListener(this)
        view1.setOnTouchListener(this)
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
                view.setLayoutParams(layoutParams)
            }
        }
        root.invalidate()
        return true
    }}
