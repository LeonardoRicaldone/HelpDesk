package RecycleViewHelpers

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import leonardo.monterrosa.helpdesk.R
import modelo.ClaseConexion
import modelo.tbTickets

class Adaptador(var Datos: List<tbTickets>): RecyclerView.Adapter<ViewHolder>() {

    fun eliminarRegistro(titulo: String, posicion: Int){
        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(posicion)

        GlobalScope.launch(Dispatchers.IO){
            val objConexion = ClaseConexion().cadenaConexion()

            val deleteProducto = objConexion?.prepareStatement("delete tbTickets where titulo = ?")!!
            deleteProducto.setString(1,titulo)
            deleteProducto.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()
        }

        Datos = listaDatos.toList()
        notifyItemRemoved(posicion)
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Datos[position]
        holder.txtEstadoCard.text = item.estado
        holder.txtTituloCard.text = item.titulo
        holder.imgBorrarCard.setOnClickListener {


            val context = holder.itemView.context

            val builder = AlertDialog.Builder(context)

            builder.setTitle("Alerta")
            builder.setMessage("Estas seguro que deseas eliminar?")

            builder.setPositiveButton("si"){
                    dialog, wich ->
                eliminarRegistro(item.titulo, position)
            }

            builder.setNegativeButton("no"){
                    dialog, wich ->
                dialog.dismiss()
            }
            //muestra la alerta
            val dialog = builder.create()
            dialog.show()


        }
    }


}