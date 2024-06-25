package leonardo.monterrosa.helpdesk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import modelo.ClaseConexion

class nuevoticket : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nuevoticket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtTitulo = findViewById<EditText>(R.id.txtTitulo)
        val txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
        val txtResponsable = findViewById<EditText>(R.id.txtResponsable)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtTelefonoTicket = findViewById<EditText>(R.id.txtTelefonoTicket)
        val txtUbicacionTicket = findViewById<EditText>(R.id.txtUbicacionTicket)
        val btnIngresarTicket = findViewById<Button>(R.id.btnAgregarTicket)
val imgVolverMenu1 = findViewById<ImageView>(R.id.imgVolverMenu1)

        imgVolverMenu1.setOnClickListener {
            val intent = Intent(this, ticket::class.java)
            startActivity(intent)
        }


        btnIngresarTicket.setOnClickListener {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val objConexion = ClaseConexion().cadenaConexion()

                    val addTicket = objConexion?.prepareStatement("Insert into tbTickets (titulo, descripcion, responsable, emailAutor, telefonoAutor, ubicacion) values (?, ?, ?, ?, ?, ?)")!!
                    addTicket.setString(1, txtTitulo.text.toString())
                    addTicket.setString(2, txtDescripcion.text.toString())
                    addTicket.setString(3, txtResponsable.text.toString())
                    addTicket.setString(4, txtEmail.text.toString())
                    addTicket.setString(5, txtTelefonoTicket.text.toString())
                    addTicket.setString(6, txtUbicacionTicket.text.toString())
                    addTicket.executeUpdate()

                }
                Toast.makeText(this, "Creacion de ticket exitoso", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                println("error: $e")
            }
        }
    }
}