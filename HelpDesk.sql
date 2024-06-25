
CREATE TABLE tbRegistrarse(
    idRegistro INT PRIMARY KEY,
    nombre VARCHAR2(50) NOT NULL,
    correo VARCHAR2(50) NOT NULL UNIQUE,
    contrasena VARCHAR2(50) NOT NULL,
    telefono VARCHAR2(50) NOT NULL,
    direccion VARCHAR2(50)
);

create sequence identity_tbRegistrarse
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE OR REPLACE TRIGGER trg_before_insert_tbRegistrarse
BEFORE INSERT ON tbRegistrarse
FOR EACH ROW
BEGIN
    SELECT identity_tbRegistrarse.NEXTVAL INTO :new.idRegistro FROM dual;
END;

select * from tbRegistrarse;

CREATE TABLE tbTickets(
    numero INT PRIMARY KEY,
    titulo VARCHAR2(100) NOT NULL,
    descripcion VARCHAR2(255) NOT NULL,
    responsable VARCHAR2(50) NOT NULL,
    emailAutor VARCHAR2(50) NOT NULL,
    telefonoAutor VARCHAR2(50) NOT NULL,
    ubicacion VARCHAR2(100),
    estado VARCHAR2(20) DEFAULT 'activo' NOT NULL
);

create sequence identity_tbTickets
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE OR REPLACE TRIGGER trg_before_insert_tbTickets
BEFORE INSERT ON tbTickets
FOR EACH ROW
BEGIN
    SELECT identity_tbTickets.NEXTVAL INTO :new.numero FROM dual;
END;

select * from tbTickets;