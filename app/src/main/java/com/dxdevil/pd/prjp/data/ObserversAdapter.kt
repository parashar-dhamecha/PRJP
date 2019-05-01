package com.dxdevil.pd.prjp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.Model.Response.Document.DocDetails.Observer
import com.dxdevil.pd.prjp.R


class ObserversAdapter(
    var list: List<Observer>, var context:Context): RecyclerView.Adapter<ObserversAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.observers, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.shortname.text=list[position].profileShortName
        holder.signername.text=list[position].firstName

    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var shortname: TextView = itemView.findViewById(R.id.tvshort_name_observe) as TextView
        var signername: TextView = itemView.findViewById(R.id.tvObserver_name) as TextView
    }

}
