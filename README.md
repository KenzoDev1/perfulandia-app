# Perfulandia App 🛍️
Bienvenido a Perfulandia, una aplicación móvil para Android diseñada como una tienda online de perfumes. Este proyecto sirve como un completo ejemplo de una aplicación de comercio electrónico moderna, construida nativamente con Kotlin y Jetpack Compose.

La aplicación presenta diferentes flujos y vistas dependiendo del rol del usuario (Cliente, Vendedor y Administrador).

## ✨ Características Principales
El proyecto está dividido en varios módulos y pantallas que cubren el ciclo completo de una tienda virtual.

### Flujo del Cliente
- Inicio de Sesión y Registro: Pantallas para que los usuarios creen una cuenta o inicien sesión.

- Navegación Principal: Una estructura de navegación centralizada (```MainScaffold```) que incluye una barra superior y un menú lateral desplegable (Navigation Drawer).

- Pantalla de Inicio (```HomeScreen```): Muestra productos destacados y una cuadrícula de productos para explorar.

- Detalle de Producto (```ProductDetailScreen```): Muestra información detallada de un perfume, permitiendo al usuario seleccionar la cantidad.

- Carrito de Compras (```ShoppingCartScreen```):

  - Lista los productos añadidos.

  - Permite modificar la cantidad de cada ítem (aumentar, disminuir).

  - Permite eliminar productos del carrito.

  - Muestra un resumen del pedido (Subtotal, Costo de envío y Total).

- Búsqueda (```SearchResultsScreen```): Pantalla para mostrar los resultados de una búsqueda y productos relacionados.

- Páginas Estáticas:

  - "Nosotros" (```AboutScreen```): Presenta información sobre la empresa, el equipo y la ubicación.

  - "Contacto" (```ContactScreen```): Un formulario para que los usuarios envíen mensajes.

### Panel de Vendedor
- Registro de Productos (```RegisterProductScreen```): Formulario para que los vendedores puedan subir nuevos productos, especificando nombre, precio, categoría (Hombre, Mujer, Unisex), descripción e imágenes.

- Lista de Productos (```ProductListScreen```): Vista que muestra los productos registrados por el vendedor, con opciones para filtrar por nombre y categoría.

### Panel de Administrador
- Registro de Usuarios (```RegisterUserScreen```): Formulario para que el administrador cree nuevos usuarios (Admin, Cliente, Vendedor).

- Lista de Usuarios (```UserListScreen```): Vista que muestra todos los usuarios registrados en el sistema, con filtros por nombre y tipo de usuario.

## 🛠️ Tecnologías y Dependencias
Este proyecto está construido 100% en Kotlin y utiliza las últimas tecnologías del stack de Android:

- Jetpack Compose: Todo el UI kit está construido de forma declarativa con Compose.

- Material 3: Implementación de los componentes visuales siguiendo la guía de diseño de Material You.

- Navigation Compose: Para gestionar la navegación y el paso de argumentos entre las diferentes pantallas (```@Composable```).

- Estado de Compose: Uso de ```remember``` y ```mutableStateOf``` para la gestión del estado a nivel de pantalla.

- Gradle (Kotlin DSL): Gestión de dependencias y configuración del proyecto usando ```build.gradle.kts``` y ```libs.versions.toml```.

## LINK DE PLANIFICACION (TRELLO)
https://trello.com/invite/b/68f810e029ae05164402ee02/ATTI436ad943530cbc6478cc2b268f900eeaE1A0483A/tablero-perfulandia
