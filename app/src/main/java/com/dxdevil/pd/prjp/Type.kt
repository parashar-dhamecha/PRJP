package com.dxdevil.pd.prjp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_type.*
import java.lang.Exception

class Type : AppCompatActivity(), View.OnClickListener {

    var tf:Typeface? = null
    var atv :Array<TextView>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type)
        var ed: TextInputEditText = findViewById(R.id.edfonts)
        ed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //tv1
                scrolltv1.text = s

                //tv2
                scrolltv2.text = s
                tf = Typeface.createFromAsset(assets, "fonts/2.ttf")
                scrolltv2.setTypeface(tf)
                //tv3
                scrolltv3.text = s
                tf = Typeface.createFromAsset(assets, "fonts/3.ttf")
                scrolltv3.setTypeface(tf)
                //tv4
                scrolltv4.text = s
                tf = Typeface.createFromAsset(assets, "fonts/4.ttf")
                scrolltv4.setTypeface(tf)
                //tv5
                scrolltv5.text = s
                tf = Typeface.createFromAsset(assets, "fonts/5.ttf")
                scrolltv5.setTypeface(tf)
                //tv6
                scrolltv6.text = s
                tf = Typeface.createFromAsset(assets, "fonts/6.ttf")
                scrolltv6.setTypeface(tf)
                //tv7
                scrolltv7.text = s
                tf = Typeface.createFromAsset(assets, "fonts/7.ttf")
                scrolltv7.setTypeface(tf)
            }
        })
        try {
            scrolltv1.setOnClickListener(this)
            scrolltv2.setOnClickListener(this)
            scrolltv3.setOnClickListener(this)
            scrolltv4.setOnClickListener(this)
            scrolltv5.setOnClickListener(this)
            scrolltv6.setOnClickListener(this)
            scrolltv7.setOnClickListener(this)
        }catch (e:Exception){
            Log.d("1","exception $e")
        }

    }
    override fun onClick(v: View?) {
        try {
        when(v!!.id) {

            R.id.scrolltv1 -> {
                finalsigntv.text = edfonts.text.toString()
                tf = Typeface.createFromAsset(assets, "fonts/1.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                tvimageview.setImageBitmap(finalsigntv.getDrawingCache())

            }
            R.id.scrolltv2 -> {
                finalsigntv.text = edfonts.text.toString()
                tf = Typeface.createFromAsset(assets, "fonts/2.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                tvimageview.setImageBitmap(finalsigntv.getDrawingCache())
            }
            R.id.scrolltv3 -> {
                finalsigntv.setText(edfonts.text.toString())
                tf = Typeface.createFromAsset(assets, "fonts/3.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                tvimageview.setImageBitmap(finalsigntv.getDrawingCache())
            }
            R.id.scrolltv4 -> {
                finalsigntv.setText(edfonts.text.toString())
                tf = Typeface.createFromAsset(assets, "fonts/4.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                tvimageview.setImageBitmap(finalsigntv.getDrawingCache())
            }
            R.id.scrolltv5 -> {
                finalsigntv.setText(edfonts.text.toString())
                tf = Typeface.createFromAsset(assets, "fonts/5.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                tvimageview.setImageBitmap(finalsigntv.getDrawingCache())
            }
            R.id.scrolltv6 -> {
                finalsigntv.setText(edfonts.text.toString())
                tf = Typeface.createFromAsset(assets, "fonts/6.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                tvimageview.setImageBitmap(finalsigntv.getDrawingCache())
            }
            R.id.scrolltv7 -> {
                finalsigntv.setText(edfonts.text.toString())
                tf = Typeface.createFromAsset(assets, "fonts/7.ttf")
                finalsigntv.typeface = tf
                finalsigntv.buildDrawingCache()
                tvimageview.setImageBitmap(finalsigntv.getDrawingCache())

            }
        }
    }catch (e:Exception) {
        Log.d("2", "exception $e")
    }
}


}


