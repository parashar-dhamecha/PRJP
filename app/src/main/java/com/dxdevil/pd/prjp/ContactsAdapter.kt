package com.dxdevil.pd.prjp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


public class ContactsAdapter<Context>(private val list: ArrayList<ModelClass>,
                               private val context :Context) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.contactsadapter,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bindItem(list[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(modelClass: ModelClass)
            {
                var name:TextView=itemView.findViewById(R.id.name) as TextView
                name.text=modelClass.name
    }
}









