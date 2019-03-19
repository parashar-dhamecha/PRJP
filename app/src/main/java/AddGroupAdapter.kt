import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dxdevil.pd.prjp.ContactModel
import com.dxdevil.pd.prjp.R

class AddGroupAdapter(private val context: Context, private val Con: ArrayList<ContactModel>) :
    RecyclerView.Adapter<AddGroupAdapter.ViewHolder>() {


    var checkedids = SparseBooleanArray()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddGroupAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.grp, parent, false)
        return AddGroupAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Con.size
    }

    override fun onBindViewHolder(holder: AddGroupAdapter.ViewHolder, position: Int) {
        holder.pro.setImageResource(Con[position].photo)
        holder.name.text = Con[position].name
        holder.checkbox.isChecked = checkedids.get(position)
        // holder.checkbox.setOnClickListener{

        //   checkCheckBox(position,!checkedids.get(position))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name) as TextView
        var pro: ImageView = itemView.findViewById(R.id.pro) as ImageView
        var checkbox: CheckBox = itemView.findViewById(R.id.checkbox) as CheckBox


    }


}