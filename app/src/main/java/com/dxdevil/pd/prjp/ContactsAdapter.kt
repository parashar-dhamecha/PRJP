package com.dxdevil.pd.prjp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.Model.Response.Data
import com.dxdevil.pd.prjp.Model.Response.DeleteIdResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ContactsAdapter(private var context: Context, var Con: ArrayList<Data>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    var sp = context.getSharedPreferences("userid",0) as SharedPreferences
    var ed = sp.edit()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contactsadapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Con.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.pro.setImageResource(R.drawable.user)
        holder.name.text = Con[position].name
        holder.email.text=Con[position].email
        holder.mobileno.text=Con[position].mobileNumber



        // Log.d("ContactsAdapter", "email" + Con[position].email)
       holder.editbutton.setOnClickListener{
//               var s = Con[position].id
//           ed.putString("userid",s)
//           ed.commit()
//           val intent = Intent(context, UpdateContact::class.java)
//           intent.putExtra("quantity","");
//            context.startActivity(intent)
           Toast.makeText(context, "Check your internet Connection"+Con[position].id, Toast.LENGTH_LONG).show()

//           context.startActivity<UpdateContact>(COUNTRIES to countries)

           val intent = Intent(context, UpdateContact::class.java)
           intent.putExtra("value", Con[position].id)
           context.startActivity(intent)

        }

        holder.delbutton.setOnClickListener{

            var s = Con[position].id
            ed.putString("userid",s)
            ed.commit()




            val builder= AlertDialog.Builder(context)
            builder.setTitle("Are you sure you want to delete the user?")
            builder.setPositiveButton("Yes"){ dialogInterface: DialogInterface?, i: Int ->

                try{

                    var token = context.getSharedPreferences("Token", Context.MODE_PRIVATE).getString("Token", "")
                    val apidel = RetrofitClient.getInstance()!!.api as Api
                    var call1 =
                        apidel.deleteid(token, sp.getString("userid", "")) as Call<DeleteIdResponse>

                    call1.enqueue(object : Callback<DeleteIdResponse>{
                        override fun onFailure(call: Call<DeleteIdResponse>, t: Throwable) {

                            Toast.makeText(context, "Check your internet Connection", Toast.LENGTH_LONG).show()

                        }

                        override fun onResponse(call: Call<DeleteIdResponse>, response: Response<DeleteIdResponse>) {

                            if (response.isSuccessful) {

                                Toast.makeText(context, "Contact deleted successfully", Toast.LENGTH_LONG).show()


                            }

                            else{

                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()



                            }




                        }


                    })







                    // Con.removeAt(position)
                //notifyItemRemoved(position)

            } catch(e:Exception)
                {

                    Toast.makeText(context, "error", Toast.LENGTH_LONG).show()


                }
            }
            builder.setNegativeButton("No") { dialogInterface:DialogInterface?, i:Int->

            }

            //builder.show()
            val dialog: AlertDialog= builder.create()
            dialog.show()
        }




//
//        holder.del.setOnClickListener {
//
//            Con.removeAt(position)
//            notifyItemRemoved(position)
//
//
//        }



    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name) as TextView
        var pro: ImageView = itemView.findViewById(R.id.pro) as ImageView
        var email:TextView=itemView.findViewById(R.id.email) as TextView
        var swipe:SwipeRevealLayout=itemView.findViewById(R.id.swipe) as SwipeRevealLayout
        var mobileno:TextView=itemView.findViewById(R.id.mobileno) as TextView
//        var del: Button = itemView.findViewById(R.id.del) as Button
        // @SuppressLint("WrongViewCast")
        var delbutton: ImageButton = itemView.findViewById(R.id.delbutton) as ImageButton

        var editbutton: ImageButton = itemView.findViewById(R.id.editbutton) as ImageButton


    }
}









