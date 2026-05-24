USE universidad;

CREATE TABLE cliente_tarjeta (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    numero_identificacion VARCHAR(20) NOT NULL UNIQUE,
    correo_electronico VARCHAR(100) NOT NULL,
    nombre_completo VARCHAR(100) NOT NULL
);

CREATE TABLE tarjeta_credito (
    id_tarjeta INT AUTO_INCREMENT PRIMARY KEY,
    numero_tarjeta VARCHAR(16) NOT NULL UNIQUE,
    fecha_vencimiento VARCHAR(7) NOT NULL,
    franquicia VARCHAR(20) NOT NULL,
    estado VARCHAR(10) NOT NULL DEFAULT 'ACTIVO',
    cupo_total DECIMAL(15,2) NOT NULL,
    cupo_disponible DECIMAL(15,2) NOT NULL,
    cupo_utilizado DECIMAL(15,2) NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente_tarjeta(id_cliente)
);
