package modelo

data class tbTickets(
    val numero: String,
    val titulo: String,
    val descripcion: String,
    val responsable: String,
    val emailAutor: String,
    val telefonoAutor: String,
    val ubicacion: String,
    val estado: String
)
