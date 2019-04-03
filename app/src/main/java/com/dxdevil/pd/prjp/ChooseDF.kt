package com.dxdevil.pd.prjp


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import android.view.Gravity
import android.R.attr.x
import android.app.Dialog
import android.content.Intent
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.signpopup.*


class ChooseDF : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.setGravity(Gravity.CENTER_HORIZONTAL)

        val p = dialog!!.window!!.attributes
        p.width = ViewGroup.LayoutParams.MATCH_PARENT
        p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        dialog!!.window!!.setAttributes(p)
        return inflater.inflate(R.layout.signpopup, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val draw:Button? = view?.findViewById<View>(R.id.draw_signature) as Button?
        val photo:Button? = view?.findViewById<View>(R.id.photobutton) as Button?
        val type:Button?= view?.findViewById<View>(R.id.typebutton) as Button?
        draw?.setOnClickListener {
            var intent= Intent(context,DrawSignature::class.java)
            startActivity(intent)
        }
        photo?.setOnClickListener {
            var intent= Intent(context,PhotoActivity::class.java)
            startActivity(intent)
        }
        type?.setOnClickListener {
            var intent= Intent(context,Type::class.java)
            startActivity(intent)
        }

        popupcancel.setOnClickListener {
            dialog!!.dismiss()
        }
    }
}




