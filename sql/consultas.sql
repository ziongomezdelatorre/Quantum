-- ============================================
-- CONSULTAS SQL - PROYECTO QUANTUM
-- ============================================

-- 1. Empleados nacidos a partir del año 2001
SELECT nombre, apellido, correo
FROM empleado
WHERE fecha_nacimiento >= '2001-01-01';

-- 2. Productos sin stock o no disponibles
SELECT stock, disponibilidad
FROM producto
WHERE stock = 0 OR disponibilidad = 0;

-- ============================================
-- INNER JOINS
-- ============================================

-- 3. Pedidos realizados en una fecha concreta con método de pago
SELECT c.nombre, p.fecha, p.metodo_pago
FROM cliente c
INNER JOIN pedido p
ON c.id_cliente = p.id_cliente
WHERE p.fecha = '2026-04-15';

-- 4. Productos de alta gama con su categoría tecnológica
SELECT p.nombre_producto, p.precio, c.tipo
FROM categoria c
INNER JOIN producto p
ON c.id_categoria = p.id_categoria
WHERE p.precio > 1000;

-- 5. Historial completo de pedidos de un cliente por DNI
SELECT c.nombre, pd.estado, p.nombre_producto, dp.cantidad
FROM cliente c
INNER JOIN pedido pd ON c.id_cliente = pd.id_cliente
INNER JOIN detalle_pedido dp ON pd.id_pedido = dp.id_pedido
INNER JOIN producto p ON dp.id_producto = p.id_producto
WHERE c.dni = '87654321X';

-- ============================================
-- AGREGACIONES
-- ============================================

-- 6. Número de pedidos realizados por cada cliente
SELECT c.nombre, COUNT(*) AS pedido
FROM cliente c
INNER JOIN pedido p ON p.id_cliente = c.id_cliente
GROUP BY nombre;

-- 7. Catálogo de productos ordenado de mayor a menor precio
SELECT precio, nombre_producto
FROM producto
ORDER BY precio DESC;

-- 8. Total facturado y número de líneas por cada pedido
SELECT SUM(cantidad * precio_unitario) AS total_pedido,
       COUNT(*) AS pedido
FROM detalle_pedido dp
INNER JOIN pedido p ON p.id_pedido = dp.id_pedido
GROUP BY p.id_pedido;

-- 9. Precio medio de productos por categoría tecnológica
SELECT AVG(precio), c.tipo
FROM producto p
INNER JOIN categoria c ON c.id_categoria = p.id_categoria
GROUP BY c.tipo;

-- ============================================
-- LEFT JOIN
-- ============================================

-- 10. Todos los clientes y cuántos pedidos han realizado (incluyendo 0)
SELECT c.nombre, COUNT(p.id_pedido) AS numero_pedidos
FROM cliente c
LEFT JOIN pedido p ON p.id_cliente = c.id_cliente
GROUP BY c.id_cliente;

-- ============================================
-- HAVING
-- ============================================

-- 11. Clientes con más de un pedido realizado
SELECT c.nombre, COUNT(p.id_pedido) AS numero_pedidos
FROM cliente c
LEFT JOIN pedido p ON p.id_cliente = c.id_cliente
GROUP BY c.id_cliente
HAVING numero_pedidos > 1;

-- ============================================
-- LEFT JOIN + RIGHT JOIN + UNION 
-- ============================================

-- 12. Detección de huérfanos: categorías sin productos Y productos sin categoría
SELECT c.id_categoria, c.tipo, p.id_producto, p.nombre_producto
FROM categoria c
LEFT JOIN producto p ON p.id_categoria = c.id_categoria

UNION

SELECT c.id_categoria, c.tipo, p.id_producto, p.nombre_producto
FROM categoria c
RIGHT JOIN producto p ON p.id_categoria = c.id_categoria;
