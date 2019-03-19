package com.dxdevil.pd.prjp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View



 var mBitmap : Bitmap?=null
var mCanvas : Canvas? =null
var bitmappaint :Paint? = Paint(Paint.DITHER_FLAG)
class PaintView(context:Context,att: AttributeSet): View(context,att) {


    var path: Path = Path()
    var brush: Paint = Paint()
    var cirpath: Path = Path()
    var circlebrush: Paint = Paint()

    init {
        brush.isAntiAlias = true
        brush.color = Color.BLACK
        brush.setDither(true)
        brush.style = Paint.Style.STROKE
        brush.strokeJoin = Paint.Join.ROUND
        brush.strokeWidth = 6f

    }

    fun getBitmap(): Bitmap?{
        return mBitmap
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
         mBitmap  = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        mCanvas= Canvas(mBitmap)
    }
    override fun onDraw(canvas: Canvas?) {
        canvas!!.drawPath(path,brush)


    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var pointx:Float=event!!.x
         var pointy:Float=event!!.y
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointx, pointy)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointx, pointy)
                circlebrush.reset()
            }
            MotionEvent.ACTION_UP ->{
                circlebrush.reset()
            }
            else -> {
                return false
            }
        }

            postInvalidate()
            return true
        }


    }


