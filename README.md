# Mi Lista de Películas Favoritas

## 1. Descripción
Una app que permite al usuario gestionar su lista personal de películas favoritas: agregarlas, verlas en un listado, consultar su detalle, editarlas y eliminarlas. El usuario puede ver un listado con póster, nombre y descripción breve de cada película, y acceder al detalle completo de cada una tocándola desde la lista.

## 2. Problema que resuelve
Muchas personas ven decenas de películas pero no llevan un registro organizado de cuáles les gustaron y por qué. Esta app cubre la necesidad de tener, en un solo lugar, una lista personal y visual de películas favoritas —totalmente editable— sin depender de notas sueltas o memoria.

## 3. Pantallas

| # | Nombre de pantalla | Descripción breve |
|---|-------------------|-------------------------------|
| 1 | InicioScreen | Pantalla de bienvenida con botón para ver la lista |
| 2 | ListaScreen | Lista scrollable (LazyColumn) de películas favoritas con imagen, nombre y descripción corta. Incluye buscador y botón flotante (+) para agregar una nueva película |
| 3 | DetalleScreen | Detalle completo de la película seleccionada: imagen grande, nombre, descripción completa y botones para Editar o Eliminar |
| 4 | FormularioScreen | Formulario reutilizable para crear una película nueva o editar una existente (nombre, descripción, URL de imagen) |

## 4. Tecnologías usadas
- Kotlin 2.x
- Jetpack Compose + Material 3
- Navigation Compose
- Estado con remember / rememberSaveable / mutableStateListOf
- Coil (carga de imágenes desde URL)
- Operaciones CRUD en memoria sobre un listado observable (`PeliculasRepo`), sin base de datos externa

## 5. Diagrama de navegación

```
InicioScreen
    │
    │  (botón "Ver mis películas")
    ▼
ListaScreen ──────────────────────────┐
    │           (FAB "+")             │
    │  (tap en una película)          ▼
    ▼                        FormularioScreen (modo Agregar)
DetalleScreen                          │
    │   │                    (guardar) │
    │   │ (Editar)                     ▼
    │   └──────────────────► FormularioScreen (modo Editar)
    │                                  │
    │  (Eliminar / Atrás)   (guardar) │
    ▼                                  ▼
ListaScreen ◄──────────────────────────┘
```

## 6. Operaciones CRUD implementadas

| Operación | Dónde se ejecuta | Descripción |
|-----------|-------------------|-------------|
| Crear | ListaScreen → FormularioScreen | Botón flotante (+) abre el formulario vacío; al guardar se agrega una nueva película al listado |
| Leer | ListaScreen / DetalleScreen | Listado completo con buscador y vista de detalle individual |
| Actualizar | DetalleScreen → FormularioScreen | Botón "Editar" abre el formulario precargado con los datos existentes |
| Eliminar | DetalleScreen | Botón "Eliminar" quita la película del listado y regresa a la lista |

## 7. Capturas de pantalla
<img width="610" height="1289" alt="image" src="https://github.com/user-attachments/assets/3f5b6084-bfd2-4a23-bd50-026a30426366" />
<img width="610" height="1294" alt="image" src="https://github.com/user-attachments/assets/f85cb7d1-6961-4d0f-be74-b35b515e9fb1" />
<img width="610" height="1287" alt="image" src="https://github.com/user-attachments/assets/97897847-e99b-46a0-9353-4d2719b5060c" />
<img width="604" height="1280" alt="image" src="https://github.com/user-attachments/assets/f51fb6e2-1e4a-4ee6-9421-edc0b4002f18" />
<img width="1220" height="2573" alt="image" src="https://github.com/user-attachments/assets/a3f9ccee-977c-429a-897d-e16ba7ffa50e" />




