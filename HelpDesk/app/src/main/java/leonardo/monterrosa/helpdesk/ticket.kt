package leonardo.monterrosa.helpdesk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ticket : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnCrearTicket = findViewById<Button>(R.id.btnIrAgregarTicket)
        val btnIrTickets = findViewById<Button>(R.id.btnIrTickets)

        btnCrearTicket.setOnClickListener {
            val intent1 = Intent(this, nuevoticket::class.java)
            startActivity(intent1)
        }
        btnIrTickets.setOnClickListener {
            val intent = Intent(this, todoslostickets::class.java)
            startActivity(intent)


        }
    }
}