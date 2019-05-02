package com.dxdevil.pd.prjp

import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import kotlinx.android.synthetic.main.activity_annotation.*
import android.R.attr.rotation
import androidx.core.view.MotionEventCompat.getPointerCount
import android.R.attr.y
import android.R.attr.x
import android.R.attr.spacing
import android.R.attr.mode
import android.R.attr.start
import kotlinx.android.synthetic.main.activity_profile.view.*


class Annotation : AppCompatActivity() {
     var cx :Float? =null
    var cy:Float?=  null
    private val matrix = Matrix()
    private val savedMatrix = Matrix()
    // we can be in one of these 3 states
    private val NONE = 0
    private val DRAG = 1
    private val ZOOM = 2
    private var mode = NONE
    // remember some things for zooming
    private val start = PointF()
    private val mid = PointF()
    private var oldDist = 1f
    private var d = 0f
    private var newRot = 0f
    private var lastEvent: FloatArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation)

        imageView.setOnTouchListener { v, event -> onTouch(v,event) }
       xycoordinates.setText(imageView.verticalScrollbarPosition.toString())
    }
     fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val view = v as ImageView
        when (event!!.getAction() and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                savedMatrix.set(matrix)
                start.set(event.getX(), event.getY())
                mode = DRAG
                lastEvent = null
                xycoordinates.setText("x:${event.getX(0)} y:${event.getY(0)}")

            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                oldDist = spacing(event)
                if (oldDist > 10f) {
                    savedMatrix.set(matrix)
                    midPoint(mid, event)
                    mode = ZOOM
                }
                lastEvent = FloatArray(4)
                lastEvent!![0] = event.getX(0)
                lastEvent!![1] = event.getX(1)
                lastEvent!![2] = event.getY(0)
                lastEvent!![3] = event.getY(1)
                d = rotation(event)
                xycoordinates.setText("x:${event.getX(0)} y:${event.getY(0)}")
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                mode = NONE
                lastEvent = null
            }
            MotionEvent.ACTION_MOVE -> if (mode == DRAG) {
                matrix.set(savedMatrix)
                val dx = event.getX() - start.x
                val dy = event.getY() - start.y
                matrix.postTranslate(dx, dy)
                cx=dx
                cy=dy
                xycoordinates.setText("x:${event.getX(0)} y:${event.getY(0)}")
            } else if (mode == ZOOM) {
                val newDist = spacing(event)
                if (newDist > 10f) {
                    matrix.set(savedMatrix)
                    val scale = newDist / oldDist
                    matrix.postScale(scale, scale, mid.x, mid.y)
                }
                if (lastEvent != null && event.getPointerCount() === 3) {
                    newRot = rotation(event)
                    val r = newRot - d
                    val values = FloatArray(9)
                    matrix.getValues(values)
                    val tx = values[2]
                    val ty = values[5]
                    val sx = values[0]
                    val xc = view.width / 2 * sx
                    val yc = view.height / 2 * sx
                    matrix.postRotate(r, tx + xc, ty + yc)
                }

            }
        }

        view.imageMatrix = matrix
        return true
    }

     fun spacing(event:MotionEvent):Float{
         val x = event.getX(0) - event.getX(1)
         val y = event.getY(0) - event.getY(1)
         return Math.sqrt((x * x+ y * y).toDouble()).toFloat()
    }
    private fun midPoint(point: PointF, event: MotionEvent) {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        point.set(x / 2, y / 2)
    }
    private fun rotation(event: MotionEvent): Float {
        val delta_x = (event.getX(0) - event.getX(1)).toDouble()
        val delta_y = (event.getY(0) - event.getY(1)).toDouble()
        val radians = Math.atan2(delta_y, delta_x)
        return Math.toDegrees(radians).toFloat()
    }
}

