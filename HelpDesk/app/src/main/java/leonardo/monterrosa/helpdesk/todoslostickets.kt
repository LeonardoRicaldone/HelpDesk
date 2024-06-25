package leonardo.monterrosa.helpdesk

import RecycleViewHelpers.Adaptador
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbTickets

class todoslostickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_todoslostickets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rcvTickets = findViewById<RecyclerView>(R.id.rcvTickets)


        rcvTickets.layoutManager = LinearLayoutManager(this)

        fun obtenerTickets(): List<tbTickets>{
            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("Select * from tbTickets")!!

            val listaTickets = mutableListOf<tbTickets>()


                while (resultSet.next()){
                    val numero = resultSet.getString("numero")
                    val titulo = resultSet.getString("titulo")
                    val descripcion = resultSet.getString("descripcion")
                    val responsable = resultSet.getString("responsable")
                    val emailAutor = resultSet.getString("emailAutor")
                    val telefonoAutor = resultSet.getString("telefonoAutor")
                    val ubicacion = resultSet.getString("ubicacion")
                    val estado = resultSet.getString("estado")

                    val valoresJuntos = tbTickets(numero, titulo, descripcion, responsable, emailAutor, telefonoAutor, ubicacion, estado)
                    listaTickets.add(valoresJuntos)
                }
return listaTickets
        }
        CoroutineScope(Dispatchers.IO).launch{
            val ticketsDB = obtenerTickets()
            withContext(Dispatchers.Main){
                val adapter = Adaptador(ticketsDB)
                rcvTickets.adapter = adapter
            }
        }
    }
}