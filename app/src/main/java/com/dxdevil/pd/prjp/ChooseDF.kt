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
import android.view.WindowManager
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


}




