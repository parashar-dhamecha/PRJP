package com.dxdevil.pd.prjp

import android.app.ActionBar
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View

class PaintView(context:Context): View(context) {
    var layoutparams:ActionBar.LayoutParams?=null
    var path : Path =Path()
    var brush : Paint = Paint()
    init{
        brush.isAntiAlias=true
        brush.color= Color.BLACK
        brush.style=Paint.Style.STROKE
        brush.strokeJoin=Paint.Join.ROUND
        brush.strokeWidth=6f

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var pointx:Float=event!!.getX()
         var pointy:Float=event!!.getY()
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointx, pointy)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointx, pointy)
            }
            else -> {
                return false
            }
        }

            postInvalidate()
            return false
        }

    override fun onDraw(canvas: Canvas?) {
canvas!!.drawPath(path,brush)
    }
    }


