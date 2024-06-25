package leonardo.monterrosa.helpdesk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import modelo.ClaseConexion

class registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNombre = findViewById<EditText>(R.id.txtNombreRegistro)
        val txtCorreoRegistro = findViewById<EditText>(R.id.txtCorreoRegistro)
        val txtContrasenaRegistro = findViewById<EditText>(R.id.txtContrasenaRegistro)
        val txtTelefono = findViewById<EditText>(R.id.txtTelefono)
        val txtUbicacion = findViewById<EditText>(R.id.txtUbicacion)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)
        val txtLogin = findViewById<TextView>(R.id.txtLogin)
        var compila: Boolean = false

        btnRegistrarse.setOnClickListener {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val objConexion = ClaseConexion().cadenaConexion()

                    val addRegistro = objConexion?.prepareStatement("Insert into tbRegistrarse (nombre, correo, contrasena, telefono, direccion) values(?, ?, ?, ?, ?)")!!
                    addRegistro.setString(1, txtNombre.text.toString())
                    addRegistro.setString(2, txtCorreoRegistro.text.toString())
                    addRegistro.setString(3, txtContrasenaRegistro.text.toString())
                    addRegistro.setString(4, txtTelefono.text.toString())
                    addRegistro.setString(5, txtUbicacion.text.toString())
                    addRegistro.executeUpdate()

                }
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                val intent2 = Intent(this, MainActivity::class.java)
                startActivity(intent2)
            }catch (e: Exception){
                println("error: $e")
            }

        }



        txtLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}