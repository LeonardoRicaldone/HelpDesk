package RecycleViewHelpers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import leonardo.monterrosa.helpdesk.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val txtEstadoCard = view.findViewById<TextView>(R.id.txtEstadocard)
    val txtTituloCard = view.findViewById<TextView>(R.id.txtTituloCard)
    val imgBorrarCard = view.findViewById<ImageView>(R.id.imgBorrarCard)
}