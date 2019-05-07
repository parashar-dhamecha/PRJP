package com.dxdevil.pd.prjp.com.dxdevil.pd.prjp

import com.dxdevil.pd.prjp.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.Model.Response.Data


class ObserverAdapter(private var context: Context, var Con: ArrayList<Data>) :
    RecyclerView.Adapter<ObserverAdapter.ViewHolder>() {

    lateinit var selobservers:ArrayList<Data>
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.signername) as TextView
        var email:TextView=itemView.findViewById(R.id.signeremail) as TextView
        var cb = itemView.findViewById<CheckBox>(R.id.selectcb)
    }

    override fun onBindViewHolder(h: ViewHolder, position: Int) {
        h.name.text= Con[position].name.toString()
        h.email.text= Con[position].email.toString()
        if(h.cb.isSelected){
            selobservers.add(Con[position])
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.signercv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Con.size
    }


}











