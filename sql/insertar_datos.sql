INSERT INTO categoria (tipo) VALUES
('Chip neuronal'),
('Realidad Aumentada'),
('Realidad Virtual');

INSERT INTO cliente (nombre, apellido, dni, fecha_nacimiento, correo, contraseña, direccion) VALUES
('Rodrigo', 'García Pérez', '12345678Z', '1992-05-15', 'rodrigo.gp@example.com', 'rodrigoCule92', 'Calle Mayor 12, Madrid'),
('Lucía', 'Fernández Sanz', '87654321X', '1995-10-22', 'lucia.fer@example.com', '1995ferlucia', 'Avenida de la Constitución 5, Valencia'),
('Marcos', 'López González', '45678912M', '1988-01-30', 'm.lopez@example.com', 'mlopez88', 'Plaza España , Sevilla');

INSERT INTO empleado (nombre, apellido, dni, fecha_nacimiento, correo, contraseña, direccion, rol) VALUES
('Iker', 'Jiménez Soler', '47332109A', '2001-04-12', 'iker.js01@quantum.es', 'ikz_2001_secure', 'Calle de la Luna 14, Madrid', 'empleado'),
('Aitana', 'Vega Ortiz', '53884210B', '2003-08-25', 'aitana.vega@quantum.es', 'aitana_v03_pass', 'Avenida del Puerto 88, Valencia', 'empleado'),
('Mateo', 'Rivas Cano', '20993344C', '2000-01-15', 'mateo.rivas@quanmtum.es', 'm_rivas_genz', 'Calle Mayor 5, Barcelona', 'empleado'),
('Carla', 'Navarro Gil', '48221155D', '2002-11-30', 'c.navarro@quantum.es', 'carla_ng_2026', 'Calle Nueva 22, Sevilla', 'empleado'),
('Hugo', 'Pascual Sanz', '55110099E', '2005-05-20', 'hugo.p@quantum.es', 'hugo_p_work20', 'Plaza del Sol 3, Zaragoza', 'empleado');
('Eric', 'Gómez Ríos', '87654321A', '2004-02-12', 'eric.admin@quantum.es', 'eric_2204', 'Avenida Central 45, Madrid', 'admin');


INSERT INTO producto (nombre_producto, descripcion, stock, disponibilidad, precio, id_categoria) VALUES
('QuantumLink v1', 'Interfaz cerebro-computadora de baja latencia.', 50, 1, 2499.99, 1),
('QuantVision Pro', 'Gafas de realidad mixta con resolución 10K.', 30, 1, 3500.00, 2),
('QuantumBio', 'Chip subcutáneo para pagos, envios de mensaje, integracion con la tecnologia de tu casa.', 100, 1, 120.00, 1),
('Lentes AR Nano', 'Lentes de contacto con pantalla de realidad aumentada.', 0, 0, 1200.00, 2);

INSERT INTO pedido (fecha, metodo_pago, estado, id_cliente, id_empleado) VALUES
('2026-04-10 10:30:00', 'tarjeta', 'entregado', 1, 1), 
('2026-04-12 15:45:00', 'cripto', 'enviado', 2, 1),    
('2026-04-14 09:00:00', 'transferencia', 'pendiente', 3, 1),
('2026-04-15 12:20:00', 'tarjeta', 'pendiente', 2, 1), 
('2026-04-15 17:05:00', 'cripto', 'pendiente', 1, 1);  

INSERT INTO detalle_pedido (id_producto, id_pedido, precio_unitario, cantidad) VALUES 
(1, 1, 2499.99, 1),
(2, 2, 3500.00, 1),
(3, 2, 120.00, 2);