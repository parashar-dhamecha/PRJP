package com.dxdevil.pd.prjp

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_bulk_import.*
import kotlinx.android.synthetic.main.activity_bulk_import.view.*

class BulkImport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulk_import)

        val T1=findViewById<TextView>(R.id.sample)
        T1.paintFlags=T1.paintFlags or Paint.UNDERLINE_TEXT_FLAG


        val mDialogView= LayoutInflater.from(this).inflate(R.layout.activity_bulk_import,null)
        val mBuilder= AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("BulkContactsImport")

        val mAlertDialog=mBuilder.show()
        sample.setOnClickListener{


        }
        mDialogView.upload.setOnClickListener{



            mAlertDialog.dismiss()
        }







    }
}
