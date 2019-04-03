package com.dxdevil.pd.prjp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.R
import android.widget.CheckBox


 class AddGroupAdapter() : RecyclerView.Adapter<AddGroupAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var onItemClickListener: ItemClickListener
    private lateinit var Con: ArrayList<ContactModel>

    constructor(context: Context, Con: ArrayList<ContactModel>, onItemClickListener: ItemClickListener) : this() {
        this.Con = Con
        this.context = context
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddGroupAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.grp, parent, false)
        return AddGroupAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Con.size
    }

    override fun onBindViewHolder(holder: AddGroupAdapter.ViewHolder, position: Int) {


        val contacts = Con[position]
        holder.name.text = contacts.name
        holder.checkbox.isChecked = contacts.isSelected
        holder.pro.setImageResource(contacts.photo)
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onItemClickListener.onItemClick(position, true)

            } else {

                onItemClickListener.onItemClick(position, false)

            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.name)
        var pro: ImageView = itemView.findViewById(R.id.pro)
        var checkbox: CheckBox = itemView.findViewById(R.id.checkbox)

        init {

        }

    }
}
