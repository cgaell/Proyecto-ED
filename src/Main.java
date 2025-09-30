import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random; 

public class Main {
   
    static Node<Cliente> arbolpedidos = null;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_ROJO = "\u001B[31m";
    public static final String ANSI_AMARILLO = "\u001B[33m";
    public static final String ANSI_AZUL = "\u001B[34m";
    public static final String ANSI_MORADO = "\u001B[35m";
    public static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MMMM/yyyy HH:mm:ss");

    /**
     * funcion  para generar y agregar 100 clientes, cada uno con 
     * información unica, por ejemplo con nombre, dirección, teléfono y prioridad propia
     * @param pedidos La PriorityQueue a la que se añadirán los clientes.
     */
    private static void generarYAgregarClientesPersonalizados(PriorityQueue<Cliente> pedidos) {
        Random random = new Random();
        
        String[] nombres = {"Ana", "Luis", "Sofía", "Javier", "Elena", "Ricardo", "Valeria", "Andrés", "Camila", "Diego", 
                            "Gael", "Hilda", "Omar", "Paula", "Rubén", "Ximena", "Yago", "Diana", "Iván", "Nora"};
        String[] apellidos = {"García", "Pérez", "López", "Ramírez", "Torres", "Castro", "Soto", "Gómez", "Díaz", "Mendoza",
                              "Vargas", "Mora", "Reyes", "Sánchez", "Muñoz", "Herrera", "Cruz", "Paz", "Flores", "Solís"};
        String[] calles = {"Calle de la Flor", "Avenida Tulipán", "Paseo Rosaleda", "Boulevard Girasol", "Calle Clavel", 
                           "Av. Orquídea", "Blvd. Gardenia", "Paseo de las Lilas", "Calle Crisantemo", "Av. Margarita"};
        
        String[] ramos = {"Ramo de Rosas", "Ramo de Girasoles", "Ramo de Tulipanes", "Ramo Mixto", "Gerberas", "Lirios", "Claveles", "Gladiolas"};
        double[] precios = {350.0, 250.0, 300.0, 400.0, 350.0, 459.0, 390.0, 420.0};
        
        for (int i = 1; i <= 100; i++) {
            
            String nombre = nombres[i % nombres.length] + " " + apellidos[(i + 5) % apellidos.length];
            
            String calleElegida = calles[i % calles.length];
            String direccion = calleElegida + " #" + (100 + i * 5) + " Col. Jardín " + (i % 10 + 1);
            
            String telefono = "868" + String.format("%07d", 1000000 + i * 11);
            
            int prioridad = random.nextInt(2) + 1; 

            int indiceRamo = i % ramos.length;
            String ramoElegido = ramos[indiceRamo];
            double precioPedido = precios[indiceRamo] + random.nextDouble() * 50; 
            
            Cliente nuevoCliente = new Cliente(nombre, direccion, telefono, prioridad, precioPedido, ramoElegido);
            pedidos.push(prioridad, nuevoCliente);
        }
    }

    /**
     * Logica principal del codigo
     * @param args todo lo que se va a implementar dentro (default)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double gananciasTotales = 0.0;

        HashTable<Flores<String>> catalogo = new HashTable<>(23);
        catalogo.insertar(new Flores<>("Ramo de Rosas", 10, "Rosas", 350.0));
        catalogo.insertar(new Flores<>("Ramo de Girasoles", 8, "Girasoles", 250.0));
        catalogo.insertar(new Flores<>("Ramo de Tulipanes", 12, "Tulipanes", 300.0));
        catalogo.insertar(new Flores<>("Ramo Mixto", 5, "Variado", 400.0));
        catalogo.insertar(new Flores<>("Tulipanes Blancos", 10, "Rosas", 750.0));
        catalogo.insertar(new Flores<>("Manojos Margaritas Blancas", 10, "Margaritas", 200.0));
        catalogo.insertar(new Flores<>("Manojos de Gypsophila", 10, "Gypsophila", 150.0));
        catalogo.insertar(new Flores<>("Violetas", 25, "Violetas", 350.0));
        catalogo.insertar(new Flores<>("Orquideas", 10, "Orquideas", 480.0));
        catalogo.insertar(new Flores<>("Crisantemos Blancos", 15, "Crisantemos", 380.0));
        catalogo.insertar(new Flores<>("Gerberas", 20, "Gerberas", 350.0));
        catalogo.insertar(new Flores<>("Lirios", 45, "Lirios", 459.0));
        catalogo.insertar(new Flores<>("Claveles", 25, "Claveles", 390.0));
        catalogo.insertar(new Flores<>("Gladiolas", 420, "Gladiolas", 420.0));
        catalogo.insertar(new Flores<>("Lilas", 25, "Lilas", 425.0));
        catalogo.insertar(new Flores<>("Peonias", 10, "Peoinas", 900.));

        PriorityQueue<Cliente> pedidos = new PriorityQueue<>();
        
        generarYAgregarClientesPersonalizados(pedidos);
        
        Queue<Cliente> pedidosProcesados = new Queue<>();

        boolean salir = false;
        while (!salir) {
            System.out.println("\n" + ANSI_MORADO + "==================================================" + ANSI_RESET);
            System.out.println(ANSI_MORADO + "         MENU PRINCIPAL      " + ANSI_RESET);
            System.out.println(ANSI_MORADO + "==================================================" + ANSI_RESET);
            System.out.println("| 1. Gestionar pedidos                         |");
            System.out.println("| 2. Gestionar departamentos                   |");
            System.out.println("| 0. Salir                                     |");
            System.out.println(ANSI_MORADO + "==================================================" + ANSI_RESET);
            System.out.print("Selecciona una opcion: ");
            int opcionMenuPrincipal = 0;
            try {
                opcionMenuPrincipal = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ANSI_ROJO + "Error: Debes ingresar 1 o 2." + ANSI_RESET);
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (opcionMenuPrincipal) {
                case 1:
                    boolean salirFloreria = false;
                    while (!salirFloreria) {
                
            System.out.println("\n" + ANSI_AZUL + "==================================================" + ANSI_RESET);
            System.out.println(ANSI_AZUL + "                  FLORERIA GMC      " + ANSI_RESET);
            System.out.println(ANSI_AZUL + "==================================================" + ANSI_RESET);
            System.out.println("| 1. Realizar pedido                            |");
            System.out.println("| 2. Eliminar pedido por ID                     |");
            System.out.println("| 3. Consultar catálogo de flores               |");
            System.out.println("| 4. Mostrar todos los pedidos                  |");
            System.out.println("| 5. Ver Informacion de la empresa              |");
            System.out.println("| 6. Buscar pedido por ID                       |");
            System.out.println("| 7. Procesar pedido por ID                     |");
            System.out.println("| 8. Ver historial de pedidos procesados        |");
            System.out.println("| 9. Ver ganancias totales                      |");
            System.out.println("| 0. Volver al menu principal                   |");

            System.out.println(ANSI_AZUL + "==================================================" + ANSI_RESET);
            System.out.print("Selecciona una opción: ");

            int opcion = 0;
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ANSI_ROJO + "Error: Debes ingresar un número del 1 al 9." + ANSI_RESET);
                sc.nextLine();    
                continue;
            }
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    try {
                        System.out.println(ANSI_AZUL + "\n=== Realizar Pedido ===" + ANSI_RESET);
                        System.out.print("Nombre del cliente: ");
                        String nombre = sc.nextLine();
                        System.out.print("Dirección: ");
                        String direccion = sc.nextLine();
                        System.out.print("Teléfono: ");
                        String telefono = sc.nextLine();

                        System.out.println("Selecciona el tipo de ramo:");

                        List<Flores<String>> floresEnCatalogo = new ArrayList<>();
                        System.out.println(ANSI_AZUL + "\n=== Catálogo de Flores ===" + ANSI_RESET);
                        System.out.println("+----+-----------------------------+-----------+----------------+---------+");
                        System.out.printf("| %-2s | %-27s | %-9s | %-14s | %-7s |\n",
                                "ID", "Nombre", "Cantidad", "Tipo", "Precio");
                        System.out.println("+----+-----------------------------+-----------+----------------+---------+");

                        int index = 1;
                        Node<Flores<String>> nodoActual;
                        for (int i = 0; i < catalogo.getTabla().length; i++) {
                            nodoActual = catalogo.getTabla()[i].getCabeza();
                            while (nodoActual != null) {
                                Flores<String> f = nodoActual.getDatos();
                                floresEnCatalogo.add(f);
                                System.out.printf("| %-2d | %-27s | %-9d | %-14s | $%-6.2f |\n",
                                        index, f.getNombre(), f.getCantidad(), f.getTipoDeFlor(), f.getPrecio());
                                nodoActual = nodoActual.getNext();
                                index++;
                            }
                        }
                        System.out.println("+----+-----------------------------+-----------+----------------+---------+");
                        System.out.print("Selecciona un ramo (1-16): ");
                        
                        int indiceRamo = sc.nextInt();
                        sc.nextLine();

                        if (indiceRamo < 1 || indiceRamo > floresEnCatalogo.size()) {
                            System.out.println(ANSI_ROJO + "Error: Número de ramo inválido." + ANSI_RESET);
                            break;
                        }

                        Flores<String> florSeleccionada = floresEnCatalogo.get(indiceRamo - 1);
                        double precioPedido = florSeleccionada.getPrecio();
                        String nombreRamo = florSeleccionada.getNombre();


                        System.out.print("¿Es urgente? (1: Sí, 2: No): ");
                        int urgente = sc.nextInt();
                        sc.nextLine();
                        if (urgente != 1 && urgente != 2) {
                            System.out.println(ANSI_ROJO + "Error: Debes ingresar 1 o 2." + ANSI_RESET);
                            break;
                        }
                        int prioridad = (urgente == 1) ? 1 : 2;
                        
                        String ramo = nombreRamo;
                        
                        Cliente cliente = new Cliente(nombre, direccion, telefono, prioridad, precioPedido,ramo);
                        
                        pedidos.push(prioridad, cliente);

                        System.out.println(ANSI_AMARILLO + "Pedido agregado con éxito. ID: " + cliente.getID() + " | Ramo: " + nombreRamo + ANSI_RESET);

                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_ROJO + "Error: Entrada inválida, asegúrate de ingresar números donde corresponda." + ANSI_RESET);
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println(ANSI_ROJO + "Error inesperado: " + e.getMessage() + ANSI_RESET);
                    }
                    break;


                case 2:
                System.out.println(ANSI_AZUL + "\n=== Eliminar Pedido ===" + ANSI_RESET);
                System.out.print("Ingresa el ID del cliente a eliminar: ");
                String IDaeliminar = sc.nextLine().toLowerCase();
                int IDeliminado;
                try {
                    IDeliminado = Integer.parseInt(IDaeliminar);
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_ROJO + "El ID proporcionado no es válido." + ANSI_RESET);
                    break;
                }
                boolean encontrado = false;

                try {

                    for (int i = 0; i < pedidos.getSize(); i++) {
                        PriorityNode<Cliente> p = pedidos.getDatos()[i];
                        p.getDatos();
                        if (p != null && p.getDatos().getID() == IDeliminado) {
                            pedidos.getDatos()[i] = pedidos.getDatos()[pedidos.getSize() - 1]; 
                            pedidos.setSize(pedidos.getSize() - 1);
                            pedidos.getheapify(i);
                            encontrado = true;
                            System.out.println(ANSI_AMARILLO + "Pedido eliminado correctamente." + ANSI_RESET);
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println(ANSI_ROJO + "No se encontró ningún pedido con ese ID." + ANSI_RESET);
                    }

                } catch (Exception e) {
                    System.out.println(ANSI_ROJO + "Error al eliminar pedido: " + e.getMessage() + ANSI_RESET);
                }
                break;


                case 3:
                    System.out.println(ANSI_AZUL + "Como quieres mostrarlo?" + ANSI_RESET);
                    System.out.println("| 1. Precio de mayor a menor     |");
                    System.out.println("| 2. Precio de menor a mayor     |");
                    System.out.println("| 3. Mostrar por default         |");
                    System.out.print("Selecciona una opcion: ");
                    int orden = sc.nextInt();

                    boolean ascendente = (orden == 2);

                    List<Flores<String>> listaFlores = new ArrayList<>();
                    for (int i = 0; i < catalogo.getTabla().length; i++) {
                            Node<Flores<String>> nodo = catalogo.getTabla()[i].getCabeza();
                        while (nodo != null) {
                            listaFlores.add(nodo.getDatos());
                            nodo = nodo.getNext();
                        }
                    }

                    
                    if (orden == 1 || orden == 2) {
                        Ordenar.quickSort(listaFlores, 0, listaFlores.size() - 1, ascendente);
                    }

                    System.out.println(ANSI_AZUL + "\n=== Catálogo de Flores ===" + ANSI_RESET);
                    System.out.println("+----+-----------------------------+-----------+----------------+---------+");
                    System.out.printf("| %-2s | %-27s | %-9s | %-14s | %-7s |\n",
                            "ID", "Nombre", "Cantidad", "Tipo", "Precio");
                    System.out.println("+----+-----------------------------+-----------+----------------+---------+");
                    

                    int contador = 1;
                    for (Flores<String> f : listaFlores) {
                            System.out.printf("| %-2d | %-27s | %-9d | %-14s | $%-6.2f |\n",
                            contador, f.getNombre(), f.getCantidad(), f.getTipoDeFlor(), f.getPrecio());
                            contador++;
                    }

                        System.out.println("+----+-----------------------------+-----------+----------------+---------+");
                        break;

                        case 4:
                        System.out.println(ANSI_AZUL + "\n=== Pedidos Actuales ===" + ANSI_RESET);
                        if (pedidos.getSize() == 0) {
                            System.out.println(ANSI_ROJO + "No hay pedidos." + ANSI_RESET);
                            break;
                        }
                        
                        String separador4 = "+----+--------------------------------+--------------------------------+-------------+-----------+-----------------------+";
                        System.out.println(separador4);
                        System.out.printf("| %-2s | %-30s | %-30s | %-11s | %-9s | %-21s |\n",
                                "ID", "Nombre", "Dirección", "Teléfono", "Prioridad", "Fecha");
                        System.out.println(separador4);
                       
                        List<Cliente> pedidosP1 = new ArrayList<>();
                        List<Cliente> pedidosP2 = new ArrayList<>();
    
                        for (int i = 0; i < pedidos.getSize(); i++) {
                            PriorityNode<Cliente> p = pedidos.getDatos()[i];
                            if (p != null) {
                                Cliente c = p.getDatos();
                                if (p.getPriority() == 1) {
                                    pedidosP1.add(c);
                                } else if (p.getPriority() == 2) {
                                    pedidosP2.add(c);
                                }
                            }
                        }
                        
                        System.out.println(ANSI_ROJO + "--- PRIORIDAD 1 (URGENTE) ---" + ANSI_RESET);
                        for (Cliente c : pedidosP1) {
                            String nombreCorto = c.getNombre().substring(0, Math.min(c.getNombre().length(), 30));
                            String direccionCorta = c.getDireccion().substring(0, Math.min(c.getDireccion().length(), 30));
                            
                            System.out.printf("| %-3d | %-30s | %-30s | %-11s | %-9d | %-21s |\n",
                                    c.getID(), 
                                    nombreCorto, 
                                    direccionCorta,
                                    c.getTelefono(), 
                                    c.getPrioridad(), 
                                    c.getFecha().format(formato));
                        }
    
                        System.out.println(ANSI_AZUL + "--- PRIORIDAD 2 (NO URGENTE) ---" + ANSI_RESET);
                        for (Cliente c : pedidosP2) {
                            String nombreCorto = c.getNombre().substring(0, Math.min(c.getNombre().length(), 30));
                            String direccionCorta = c.getDireccion().substring(0, Math.min(c.getDireccion().length(), 30));
                            
                            System.out.printf("| %-3d | %-30s | %-30s | %-11s | %-9d | %-21s |\n",
                                    c.getID(), 
                                    nombreCorto, 
                                    direccionCorta,
                                    c.getTelefono(), 
                                    c.getPrioridad(), 
                                    c.getFecha().format(formato));
                        }
    
                        System.out.println(separador4);
                        break;

                case 5:
                    System.out.println(ANSI_AZUL + "\n=== Información de la Empresa ===" + ANSI_RESET);

                    Node<String> arbolEmpresa = new Node<>("Empresa");
                    Node<String> gael = new Node<>("Gael - Distribuidor");
                    Node<String> carlos = new Node<>("Carlos - Comprador");
                    Node <String> mauricio = new Node<>("Mauricio -Vendedor");
                    arbolEmpresa.agregarHijos(gael);
                    arbolEmpresa.agregarHijos(carlos);
                    arbolEmpresa.agregarHijos(mauricio);
                        System.out.println(ANSI_AMARILLO + "Estructura de la empresa:" + ANSI_RESET);
                        arbolEmpresa.imprimirArbol("");
                        break;

                case 6:
                if (pedidos.getSize() == 0) {
                    System.out.println(ANSI_ROJO + "No hay pedidos registrados." + ANSI_RESET);
                    break;
                }

                System.out.print("Ingresa el ID del cliente a buscar: ");
                int IDaBuscar = sc.nextInt();
                sc.nextLine();

                Cliente encontradoBusqueda = (Cliente) pedidos.buscarPorID(IDaBuscar);

                if (encontradoBusqueda!= null){
                            System.out.println(ANSI_AMARILLO + "Cliente encontrado: " + ANSI_RESET); 
                            System.out.println("ID: " + encontradoBusqueda.getID()); 
                            System.out.println("Nombre: " + encontradoBusqueda.getNombre()); 
                            System.out.println("Dirección: " + encontradoBusqueda.getDireccion()); 
                            System.out.println("Teléfono: " + encontradoBusqueda.getTelefono()); 
                            System.out.println("Ramo elegido: " + encontradoBusqueda.getRamo()); 
                            System.out.println("Prioridad: " + encontradoBusqueda.getPrioridad());
                            System.out.println("Fecha: " + encontradoBusqueda.getFecha().format(formato));
                            System.out.println("Precio: $" + encontradoBusqueda.getPrecioDePedido());
                            System.out.println("--------------------------------");
                            break;
                } else {
                    System.out.println(ANSI_ROJO + "No se encontro ningun cliente con ese ID." + ANSI_RESET);
                }
                break;
                case 7:
                    System.out.println(ANSI_AZUL + "\n=== Procesar Pedido ===" + ANSI_RESET);
                    if (pedidos.getSize() == 0) {
                        System.out.println(ANSI_ROJO + "No hay pedidos para procesar." + ANSI_RESET);
                        break;
                    }
                    try {
                        System.out.print("Ingresa el ID del pedido a procesar: ");
                        int idProcesar = sc.nextInt();
                        sc.nextLine();

                        boolean pedidoEncontrado = false;
                        for (int i = 0; i < pedidos.getSize(); i++) {
                            PriorityNode<Cliente> p = pedidos.getDatos()[i];
                            if (p != null && p.getDatos().getID() == idProcesar) {
                                Cliente clienteProcesado = p.getDatos();

                                System.out.println(ANSI_AMARILLO + "\nPedido procesado:" + ANSI_RESET);
                                System.out.println("ID: " + clienteProcesado.getID());
                                System.out.println("Nombre: " + clienteProcesado.getNombre());
                                System.out.println("Dirección: " + clienteProcesado.getDireccion());
                                System.out.println("Teléfono: " + clienteProcesado.getTelefono());
                                System.out.println("Ramo elegido: " + clienteProcesado.getRamo()); 
                                System.out.println("Prioridad: " + p.getPriority());
                                System.out.println("Fecha: " + clienteProcesado.getFecha().format(formato));
                                System.out.println("Precio: $" + clienteProcesado.getPrecioDePedido());
                                System.out.println("----------------------------------------");
                                
                                gananciasTotales += clienteProcesado.getPrecioDePedido();

                                pedidos.getDatos()[i] = pedidos.getDatos()[pedidos.getSize() - 1];
                                pedidos.setSize(pedidos.getSize() - 1);
                                pedidos.getheapify(i);
                                
                                pedidosProcesados.push(clienteProcesado);

                                pedidoEncontrado = true;
                                System.out.println(ANSI_AMARILLO + "Pedido movido al historial de procesados." + ANSI_RESET);
                                break;
                            }
                        }

                        if (!pedidoEncontrado) {
                            System.out.println(ANSI_ROJO + "No se encontró ningún pedido con ese ID." + ANSI_RESET);
                        }

                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_ROJO + "Error: Entrada inválida. Ingresa un número de ID válido." + ANSI_RESET);
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println(ANSI_ROJO + "Error al procesar el pedido: " + e.getMessage() + ANSI_RESET);
                    }
                    break;
                
                    case 8:
                    System.out.println(ANSI_AZUL + "\n=== Historial de Pedidos Procesados ===" + ANSI_RESET);
                    if (pedidosProcesados.isEmpty()) {
                        System.out.println(ANSI_ROJO + "No hay pedidos procesados." + ANSI_RESET);
                        break;
                    }

                    System.out.println("+----+------------------------------+------------------------------+-----------------+-----------------+-------------------------+------------------------------+");
                    System.out.printf("| %-3s | %-30s | %-30s | %-15s | %-15s | %-23s | %-30s |\n",
                            "ID", "Nombre", "Dirección", "Teléfono", "Prioridad", "Fecha", "Ramo");
                    System.out.println("+----+------------------------------+------------------------------+-----------------+-----------------+-------------------------+------------------------------+");

                    try {
                        Queue<Cliente> tempQueue = new Queue<>();
                        while (!pedidosProcesados.isEmpty()) {
                            Cliente c = pedidosProcesados.pop();
                            System.out.printf("| %-3s | %-30.30s | %-30.30s | %-15.15s | %-15.15s | %-23.23s | %-30.30s |\n",
                                    c.getID(),
                                    c.getNombre(),
                                    c.getDireccion(),
                                    c.getTelefono(),
                                    c.getPrioridad(),
                                    c.getFecha().format(formato),
                                    c.getRamo());
                            tempQueue.push(c);
                        }
                        pedidosProcesados = tempQueue;
                    } catch (Exception e) {
                        System.out.println(ANSI_ROJO + "Error al mostrar historial de pedidos: " + e.getMessage() + ANSI_RESET);
                    }

                    System.out.println("+----+------------------------------+------------------------------+-----------------+-----------------+-------------------------+------------------------------+");
                    break;

                    
                case 9:
                    System.out.println(ANSI_AZUL + "\n=== Ganancias Totales ===" + ANSI_RESET);
                    System.out.printf(ANSI_AMARILLO + "Total de ganancias hasta el momento: $%.2f\n" + ANSI_RESET, gananciasTotales);
                    break;

                case 0:
                    salirFloreria = true;
                    System.out.println(ANSI_AMARILLO + "\nSalida al menu principal..." + ANSI_RESET);
                    break;

                default:
                    System.out.println(ANSI_ROJO + "Opción no válida." + ANSI_RESET);
            }
        }
        break;
    
    
        case 2:
            boolean salirmenu2 = false;
            Node<String> arbolEmpresa = new Node<>(" GMC- CEO de la empresa");
            Node<String> distribuidor = new Node<>("Distribuidor");
            distribuidor.agregarHijos(new Node<String>("------ Gael"));
            Node<String> vendedor = new Node<>("Vendedor");
            vendedor.agregarHijos(new Node<String>("------ Carlos"));
            Node <String> comprador = new Node<>("Comprador");
            comprador.agregarHijos(new Node<String>("------ Mauricio"));
            arbolEmpresa.agregarHijos(distribuidor);
            arbolEmpresa.agregarHijos(vendedor);
            arbolEmpresa.agregarHijos(comprador);
            while (!salirmenu2){
            System.out.println("\n" + ANSI_AMARILLO + "==================================================" + ANSI_RESET);
            System.out.println(ANSI_AMARILLO + "              GESTION DE EMPLEADOS     " + ANSI_RESET);
            System.out.println(ANSI_AMARILLO + "==================================================" + ANSI_RESET);
            System.out.println("| 1. Consultar todos los departamentos              |");
            System.out.println("| 2. Agregar departamento nuevo                     |");
            System.out.println("| 3. Consultar empleados por departamento           |");
            System.out.println("| 4. Agregar empleado a un departamento             |");
            System.out.println("| 5. Buscar empleado                                |");
            System.out.println("| 6. Eliminar empleado                              |");
            System.out.println("| 0. Volver al menu principal                       |");
            System.out.println(ANSI_AMARILLO + "=============================================================" + ANSI_RESET);
            System.out.print("Selecciona una opcion: ");
            int opcionmenu2 = 0;
            try {
                opcionmenu2 = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ANSI_ROJO + "Error: Debes ingresar un número del 1 al 5." + ANSI_RESET);
                sc.nextLine();    
                continue;
            }
            sc.nextLine(); 
            switch (opcionmenu2) {
                case 1:
                    System.out.println(ANSI_AMARILLO + "Todos los empleados:" + ANSI_RESET);
                    arbolEmpresa.imprimirArbol("");
                    break;

                case 2:
                System.out.print("Ingresa el nombre del nuevo departamento: ");
                String nombre = sc.nextLine().trim();

                if (!nombre.isEmpty()) {
                    Node<String> nuevoDepto = new Node<>(nombre);
                    arbolEmpresa.agregarHijos(nuevoDepto);
                    System.out.println(ANSI_AMARILLO + "Departamento '" + nombre + "' agregado correctamente." + ANSI_RESET);
                } else {
                    System.out.println(ANSI_ROJO + "Error: El nombre del departamento no puede estar vacío." + ANSI_RESET);
                }
                break;

                case 3:
                System.out.println("Lista de departamentos: ");
                for (Node<String> departamento : arbolEmpresa.getHijos()){
                    System.out.println(departamento.getDatos());
                }

                System.out.print("Ingresa el departamento que deseas consultar: ");
                String depabuscado = sc.nextLine().trim();

                Node<String> depaNodo = null;
                for (Node<String> depa : arbolEmpresa.getHijos()){
                    if (depa.getDatos().equalsIgnoreCase(depabuscado)){
                        depaNodo = depa;
                        break;
                }
                }
                if (depaNodo!= null){
                    System.out.println(ANSI_AMARILLO + "Empleados listados en " + depaNodo.getDatos() + ": " + ANSI_RESET);
                    for (Node<String> empleados : depaNodo.getHijos()){
                        System.out.println(empleados.getDatos());
                    }
                } else {
                    System.out.println(ANSI_ROJO + "No se encontro el departamento solicitado" + " " + depabuscado + "." + ANSI_RESET);
                }
                break;
                case 4:
                System.out.println("Lista de departamentos disponibles para agregar empleado:");
                int deptoIndex = 1;
                for (Node<String> departamento : arbolEmpresa.getHijos()) {
                    System.out.println(deptoIndex + ". " + departamento.getDatos());
                    deptoIndex++;
                }

                System.out.print("Ingresa el número del departamento al que quieres agregar un empleado: ");
                int deptoSeleccionado;
                try {
                    deptoSeleccionado = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_ROJO + "Error: Entrada inválida. Ingresa un número." + ANSI_RESET);
                    sc.nextLine();
                    break;
                }
                sc.nextLine();

                if (deptoSeleccionado < 1 || deptoSeleccionado > arbolEmpresa.getHijos().size()) {
                    System.out.println(ANSI_ROJO + "Número de departamento inválido." + ANSI_RESET);
                    break;
                }

                Node<String> targetDepto = arbolEmpresa.getHijos().get(deptoSeleccionado - 1);
                
                System.out.print("Ingresa el nombre del nuevo empleado: ");
                String nombreEmpleado = sc.nextLine().trim();
                
                if (!nombreEmpleado.isEmpty()) {
                    targetDepto.agregarHijos(new Node<String>("------ " + nombreEmpleado));
                    System.out.println(ANSI_AMARILLO + "Empleado '" + nombreEmpleado + "' agregado a '" + targetDepto.getDatos() + "'." + ANSI_RESET);
                } else {
                    System.out.println(ANSI_ROJO + "Error: El nombre del empleado no puede estar vacío." + ANSI_RESET);
                }
                break;

                case 5:
                System.out.print("Ingresa el empleado que deseas consultar: ");
                String empleadoBuscar = sc.nextLine().trim();

                if (empleadoBuscar.isEmpty()){
                    System.out.println(ANSI_ROJO + "El nombre del empleado no puede estar vacio" + ANSI_RESET);
                }

                boolean empleadoEncontrado = false;
                
                for (Node<String> departamento : arbolEmpresa.getHijos()){
                    empleadoEncontrado = arbolEmpresa.buscarempleado(arbolEmpresa, empleadoBuscar);
                    if (empleadoEncontrado) break;
                }
                
                if (!empleadoEncontrado){
                    System.out.println(ANSI_ROJO + "No se ha logrado encontrar el empleado." + ANSI_RESET);
                }
                break;
                case 6:
                //logica del caso 6
                
                case 0:
                salirmenu2 = true;
                    System.out.println(ANSI_AMARILLO + "\nSalida al menu principal..." + ANSI_RESET);
                    break;
            
                default:
                System.out.println(ANSI_ROJO + "Opción no válida." + ANSI_RESET);
                    break;
            }
            }
        break;
            

        case 0:
        salir = true;
        System.out.println(ANSI_AMARILLO + "Saliendo del programa." + ANSI_RESET);
        break;
        default:
        System.out.println(ANSI_ROJO + "Opcion no valida." + ANSI_RESET);
    }
}
    }
}
