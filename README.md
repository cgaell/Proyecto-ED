## PROYECTO ESTRUCTURA DE DATOS

### SISTEMA DE GESTION DE UNA FLORERIA

__¿En qué consiste?__
Consiste en un sistema *user-friendly* que ayuda a gestionar inventario de una floreria, esta hecho con una interfaz en consola y puede realizar varias funciones.

__¿Qué funciones se pueden realizar?__
1. **Realizar pedido**: El usuario registra el pedido hecho por el cliente con los datos de este, y el producto que desea.
2. **Eliminar pedido**: En caso de que el cliente cambie de opinión, el usuario puede eliminar el pedido solicitado.
3. **Mostrar catálogo**: El usuario puede observar el catálogo de los tipos de ramos y flores que tienen, al igual que la cantidad por si se llegan a acabar.
4. **Mostrar pedidos**: El usuario puede ver los pedidos con los datos del cliente y lo que se pidió.
5. **Mostrar historial de pedidos**: El usuario puede consultar el historial de pedidos, aun si se eliminaron o ya se procesaron y entregaron, al igual que los pendientes.
6. **Salir**: El usuario tiene la opcion de salir del sistema cuando lo requiera.
7. **Procesar pedido**: El usuario puede registrar que el pedido se esta realizando en tiempo.


En este proyecto se implementó un sistema para una florería usando estructuras de datos en Java solo con lo que ya funciona:

* **Pedidos con prioridad (PriorityQueue)**:
    * Agregar cliente (pide nombre, teléfono, dirección, urgente Sí/No), listar por prioridad (urgentes primero) y eliminar por nombre.
* **Catálogo (lista enlazada)**:
    * Imprimir ~19 tipos de flores con descripción y precio en consola (ej.: 25 rosas amarillas — 380, 10 peonias — 3800).

__¿Cómo compilar el programa?__

* En la terminal, ubicarse en la carpeta del proyecto y ejecutar:
 ``` bash
javac -d bin -encoding UTF-8 src/*.java
 ``` 
> -d bin → indica que los compilados van a la carpeta bin.
__O también puedes__:
> cd Directorio/al/que/se/encuentra/la/carpeta
* Después, consultar los archivos de la carpeta con:
   ``` bash
   dir
    ```
* Posteriormente, ejecutar este comando:\
   ``` bash
  src/*.java
     ```  
> Compila todas las clases dentro de src.

__¿Cómo ejecutar el programa?__

* En la terminal, ubicarse en la carpeta del proyecto y ejecutar:
 ``` bash
java -cp bin Main
 ```
> -cp bin → usa la carpeta bin como classpath.
> Main → clase principal del proyecto.

__O también intentar__:
 ``` bash
java Main.java
 ```
