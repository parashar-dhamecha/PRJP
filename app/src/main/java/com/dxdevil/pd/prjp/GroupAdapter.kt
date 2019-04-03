package com.dxdevil.pd.prjp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroupAdapter() : RecyclerView.Adapter<GroupAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacts = Con[position]
        holder.name.text = contacts.name
        holder.pro.setImageResource(contacts.photo)}

    private lateinit var context: Context
    private lateinit var Con: ArrayList<ContactModel>

    constructor(context: Context, Con: ArrayList<ContactModel>) : this() {
        this.Con = Con
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_group_member, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Con.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.name)
        var pro: ImageView = itemView.findViewById(R.id.pro)

        init {

        }

    }
}
