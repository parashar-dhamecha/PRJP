
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.dxdevil.pd.prjp.R
private var content: String? = null


class ChooseDF : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments!!.getString("content")
        val style = DialogFragment.STYLE_NO_FRAME
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.signpopup, container, false)

        val btnCancel = view.findViewById<View>(R.id.popupcancel) as Button
        val draw = view.findViewById<View>(R.id.draw_signature) as Button
        val photo = view.findViewById<View>(R.id.photobutton) as Button
        val type = view.findViewById<View>(R.id.typebutton) as Button
        btnCancel.setOnClickListener {
            Toast.makeText(activity, "action cancelled", Toast.LENGTH_SHORT).show()
            dismiss()
        }


        return view
    }



}


