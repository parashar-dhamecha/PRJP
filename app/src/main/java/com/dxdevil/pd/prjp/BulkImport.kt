package com.dxdevil.pd.prjp

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class BulkImport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulk_import)

        val T1=findViewById<TextView>(R.id.sample)
        T1.paintFlags=T1.paintFlags or Paint.UNDERLINE_TEXT_FLAG





    }
}
