package com.dxdevil.pd.prjp


import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.view.drawToBitmap
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_draw_signature.*
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Path
import android.R.attr.bitmap
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class DrawSignature : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_signature)
        var pv: PaintView = findViewById(R.id.paintviewid)
        clearButtonid.setOnClickListener {
            pv.path.reset()
            pv.postInvalidate()
        }
        saveButtonid.setOnClickListener {


            try {
                var bitmap :Bitmap = Bitmap.createBitmap(pv.width,pv.height,Bitmap.Config.ARGB_8888)
                 val canvas = Canvas(bitmap)
                   pv.draw(canvas)



                var filePath: String = Environment.getExternalStorageDirectory().toString()

                filePath += "/sign.png"
                Log.d("d", filePath)
                var file: File = File(filePath)

                file.createNewFile()
                Log.d("2","2")
                var fileOutputStream: FileOutputStream = FileOutputStream(file)
                Log.d("1","1")
                bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)


                fileOutputStream.flush()

                // Close the output stream.
                fileOutputStream.close()

                Toast.makeText(getApplicationContext(), "Signature file is saved to " + filePath, Toast.LENGTH_LONG).show()
            } catch (e:Exception) {
                Log.d("saving","exception $e")
            }


        }

    }
}