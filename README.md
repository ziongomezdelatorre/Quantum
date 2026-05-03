# Quantum 

## Descripcion
Quantum es una e-commerce de una corporación de tecnologia de alta gama. Este proyecto nace para resolver la necesidad de una plataforma minimalista que gestione inventario y ventas y que a su vez represente la idea y el concepto de la empresa. Nuestro producto va dirigido a los que son vanguardistas de la tecnologia, los que buscan una experiencia de ultimo nivel con nuestros chips neuronales o nuestra tecnología experimental

## Tecnologías utilizadas
*  **Frontend:** HTML5 y CSS3 puro (sin frameworks).
* **Backend:** Java SE (Programación Orientada a Objetos).
* **Base de Datos:** MySQL con conexión mediante JDBC.
* **Gestión:** Git y GitHub para el control de versiones.

##  Estructura del Proyecto
* `/web`: Interfaz de usuario y diseño visual (HTML/CSS).
* `/src`: Lógica de negocio y motor funcional en Java.
* `/sql`: Scripts de creación, inserción y consultas de la base de datos.
*  `/docs/sistemas`: Documentación de sistemas.
* `/docs/entornos/`: Informe técnico de entorno de ejecución.
* `/docs/empleabilidad`: Perfil profesional.


## Instalación y ejecución


### Requisitos previos

- Java JDK 23 o superior
- MySQL Server 8 (conectado en el puerto `3306`).
- Maven 3.x
- IntelliJ IDEA (recomendado) o cualquier IDE compatible con Maven

### Pasos

**1. Clonar el repositorio**
Bash

git clone https://github.com/ziongomezdelatorre/Quantum.git

cd Quantum


**2. Crear la base de datos**
Bash

source sql/crear_tablas.sql
source sql/insertar_datos.sql

**3. Ejecutar la aplicación Java**
Bash

cd src/QuantumAPP
mvn clean javafx:run


**4. Ver el portal web**

Abrir `web/index.html` directamente en cualquier navegador moderno.


## Funcionalidades de la aplicación (QuantumAPP)


### Flujo de navegación


Pantalla de bienvenida → Login (correo + contraseña)
     |---Rol "admin"    → Panel de administración
     |--- Rol "empleado" → Panel de empleado


### Panel de administrador

- **Pedidos:** Mostrar Pedidos, buscar pedido por id, insertar pedido, actualizar pedidos en tabla, eliminar pedido
- **Productos:** Mostrar Productos, buscar producto por nombre, insertar producto, actualizar productos en tabla, eliminar producto
- **Empleados:** Mostrar Empleados, buscar empleado por id, insertar empleado, actualizar empleado en tabla ,eliminar empleado

### Panel de empleado

- **Pedidos:** Mostrar Pedidos, buscar pedido por id, insertar pedido, actualizar pedidos en tabla, eliminar pedido
- **Productos:** Mostrar Productos, buscar producto por nombre, insertar producto, actualizar productos en tabla, eliminar producto

### Características 

- Autenticación por correo y contraseña con redirección de ventanas por rol
- Búsqueda en tiempo real con `FilteredList`
- Edición inline directamente en las tablas (`TextFieldTableCell`)
- DialogPane de inserción de datos con validación
- Animaciones de bienvenida con texto progresivo

---

## Entidades gestionadas

| **Entidad** | **Tabla BD** | **Descripción** |
|---------|----------|-------------|
| Empleado | `empleado` | Usuarios del sistema con rol `admin` o `empleado` |
| Cliente | `cliente` | Clientes de la empresa |
| Producto | `producto` | Catálogo: chips, RA, RV |
| Pedido | `pedido` | Pedidos vinculados a clientes y empleados |
| DetallePedido | `detalle_pedido` | Líneas de cada pedido (producto + cantidad + precio) |

---

## Arquitectura del proyecto (MPO)

El proyecto sigue el patrón `MVC + DAO`:


Vista (FXML)
    |
Controlador (Controller)
    |
DAO (Data Access Object)
    |
Base de Datos (MySQL vía JDBC)


- **`model/`** — Clases de datos puras (`Persona` abstracta → `Empleado`, `Cliente`; `Producto`, `Pedido`, `DetallePedido`). Encapsulación con atributos privados y getters/setters.
- **`dao/`** — Una clase DAO por entidad. Toda la lógica SQL está aquí, aislada de los controladores.
- **`controller/`** — Solo lógica de UI: binding de datos, eventos de botones, navegación entre vistas.
- **`database/`** — `DBConnection` (singleton de conexión) y `SchemDB` (interfaz de constantes con nombres de tablas y columnas).

**Mejora MPO :** separación estricta de responsabilidades en capas (modelo / acceso a datos / controlador), uso de herencia (`Persona` → `Empleado`, `Cliente`), patrón singleton para la conexión, y uso de `ObservableList` + `FilteredList` para reactividad en la UI sin lógica duplicada en la vista.

---

## Credenciales de prueba (Administrador y Empleado común)

| Correo | Contraseña | Rol |
|--------|-----------|-----|
| eric.admin@quantum.es | eric_2204 | admin |
| iker.js01@quantum.es  |ikz_2001_secure |  empleado |

---

## Autor

**Zion Gomez de la Torre Jácome**
1º DAW — Proyecto Intermodular Quantum
[GitHub](https://github.com/ziongomezdelatorre)