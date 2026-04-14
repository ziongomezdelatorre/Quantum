CREATE TABLE cliente(
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(60) NOT NULL, 
    dni VARCHAR(9) NOT NULL UNIQUE,
    fecha_nacimiento DATE NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(100) NOT NULL,
    direccion VARCHAR(100) NOT NULL  );
    
    CREATE TABLE empleado (
        id_empleado INT AUTO_INCREMENT PRIMARY KEY,
         nombre VARCHAR(50) NOT NULL,
      apellido VARCHAR(60) NOT NULL, 
      dni VARCHAR(9) NOT NULL UNIQUE,
    fecha_nacimiento DATE NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(100) NOT NULL ,
    direccion VARCHAR(100) NOT NULL  );
    
    
    CREATE TABLE categoria (
        id_categoria INT AUTO_INCREMENT PRIMARY KEY,
        tipo ENUM('Chip neuronal','Realidad Aumentada','Realidad Virtual') NOT NULL);
        CREATE TABLE producto (
            id_producto INT AUTO_INCREMENT PRIMARY KEY,
            nombre_producto VARCHAR(50) NOT NULL,
            descripcion TEXT NOT NULL,
            stock INT NOT NULL,
            disponibilidad TINYINT(1) NOT NULL DEFAULT 1,
            precio DECIMAL(10,2) NOT NULL,
           id_categoria INT NOT NULL,
            FOREIGN KEY (id_categoria)
            REFERENCES categoria (id_categoria)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
            );
            
            CREATE TABLE pedido (
                id_pedido INT AUTO_INCREMENT PRIMARY KEY,
                fecha DATETIME NOT NULL,
                metodo_pago ENUM( 'tarjeta', 'transferencia', 'cripto') NOT NULL,
                estado ENUM( 'pendiente', 'enviado', 'entregado') NOT NULL DEFAULT 'pendiente',
                id_cliente INT NOT NULL,
                FOREIGN KEY(id_cliente)
                REFERENCES cliente (id_cliente)
                ON UPDATE CASCADE
                ON DELETE RESTRICT
                );
                
                CREATE TABLE detalle_pedido (
    id_producto INT NOT NULL, 
    id_pedido INT NOT NULL, 
    precio_unitario DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (id_producto, id_pedido),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE
);