package com.dxdevil.pd.prjp

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_set_annotation.*
import java.lang.Exception

var aCanvas : Canvas? =null
class SetAnnotation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_annotation)

        buttonannotation!!.setOnClickListener {
            try {
                var m: Bitmap = BitmapFactory.decodeResource(resources,R.drawable.pic1)
                var o: Bitmap= BitmapFactory.decodeResource(resources,R.drawable.user)

                annotationiv!!.setImageBitmap(overlayBitmapToCenter(o,m))
            }catch (e:Exception){
                Log.d("1","exception:$e")
            }
        }


    }

    private fun overlayBitmapToCenter(bitmap1: Bitmap, bitmap2: Bitmap): Bitmap {
        val bitmap1Width = bitmap1.width
        val bitmap1Height = bitmap1.height
        val bitmap2Width = bitmap2.width
        val bitmap2Height = bitmap2.height

        val marginLeft = (bitmap1Width * 0.5 - bitmap2Width * 0.5).toFloat()
        val marginTop = (bitmap1Height * 0.5 - bitmap2Height * 0.5).toFloat()

        val overlayBitmap = Bitmap.createBitmap(bitmap1Width, bitmap1Height, bitmap1.config)
        val canvas = Canvas(overlayBitmap)
        canvas.drawBitmap(bitmap2, marginLeft, marginTop, null)
        return overlayBitmap
    }
}
