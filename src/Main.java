import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // Colores ANSI para consola
    static Node<Cliente> arbolpedidos = null;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_ROJO = "\u001B[31m";
    public static final String ANSI_AMARILLO = "\u001B[33m";
    public static final String ANSI_AZUL = "\u001B[34m";
    public static final String ANSI_MORADO = "\u001B[35m";
    public static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    /**
     * Logica principal del codigo
     * @param args todo lo que se va a implementar dentro (default)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


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
        pedidos.push(1, new Cliente(1, "Gael Castro", "Av Gomez Morin 38", "8681718201", 1));
        pedidos.push(1, new Cliente(2, "Carlos Sanchez", "Av Gomez Morin 38", "8681718201", 1));
        pedidos.push(1, new Cliente(3, "Rogelio Martinez", "Av Gomez Morin 38", "8681718201", 1));
        pedidos.push(2, new Cliente(4, "Gael Castro", "Av Gomez Morin 38", "8681718201", 1));
        pedidos.push(2, new Cliente(5, "Gael Castro", "Av Gomez Morin 38", "8681718201", 1));
        pedidos.push(2, new Cliente(6, "Gael Castro", "Av Gomez Morin 38", "8681718201", 1));

        Stack<Cliente> historial = new Stack<>();

        boolean salir = false;
        while (!salir) {
            System.out.println("\n" + ANSI_MORADO + "==================================================" + ANSI_RESET);
            System.out.println(ANSI_MORADO + "         MENU PRINCIPAL      " + ANSI_RESET);
            System.out.println(ANSI_MORADO + "==================================================" + ANSI_RESET);
            System.out.println("| 1. Gestionar pedidos                         |");
            System.out.println("| 2. Gestionar departamentos                   |");
            System.out.println("| 0. Salir                  |");
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
            System.out.println("| 5. Ver historial de pedidos                   |");
            System.out.println("| 6. Procesar pedido                            |");
            System.out.println("| 7. Ver Informacion de la empresa              |");
            System.out.println("| 8. Buscar pedido por ID                       |");
            System.out.println("| 0. Volver al menu principal                                      |");

            System.out.println(ANSI_AZUL + "==================================================" + ANSI_RESET);
            System.out.print("Selecciona una opción: ");

            int opcion = 0;
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ANSI_ROJO + "Error: Debes ingresar un número del 1 al 8." + ANSI_RESET);
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


                        int index = 1;
                        Node<Flores<String>> nodoActual;
                        for (int i = 0; i < catalogo.getTabla().length; i++) {
                            nodoActual = catalogo.getTabla()[i].getCabeza();
                            while (nodoActual != null) {
                                Flores<String> f = nodoActual.getDatos();
                                System.out.println(index + ". " + f.getNombre() + " - $" + f.getPrecio());
                                nodoActual = nodoActual.getNext();
                                index++;
                            }
                        }

                        System.out.print("Número de ramo: ");
                        int ramoSeleccionado = sc.nextInt();
                        sc.nextLine();

                        if (ramoSeleccionado < 1 || ramoSeleccionado >= index) {
                            System.out.println(ANSI_ROJO + "Error: Número de ramo inválido." + ANSI_RESET);
                            break;
                        }

                        System.out.print("¿Es urgente? (1: Sí, 2: No): ");
                        int urgente = sc.nextInt();
                        sc.nextLine();
                        if (urgente != 1 && urgente != 2) {
                            System.out.println(ANSI_ROJO + "Error: Debes ingresar 1 o 2." + ANSI_RESET);
                            break;
                        }
                        int idCliente = Cliente.getID();
                        int prioridad = (urgente == 1) ? 1 : 2;
                        Cliente cliente = new Cliente(idCliente, nombre, direccion, telefono, prioridad);
                        
                        pedidos.push(prioridad, cliente);


                        historial.push(cliente);
                        System.out.println(ANSI_AMARILLO + "Pedido agregado con éxito." + ANSI_RESET);

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
                        if (p != null && Cliente.getID() == IDeliminado) {
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
                    System.out.println(ANSI_AZUL + "\n=== Catálogo de Flores ===" + ANSI_RESET);
                    System.out.println("+----+-----------------------------+-----------+----------------+---------+");
                    System.out.printf("| %-2s | %-27s | %-9s | %-14s | %-7s |\n",
                            "ID", "Nombre", "Cantidad", "Tipo", "Precio");
                    System.out.println("+----+-----------------------------+-----------+----------------+---------+");
                     int contador = 1;
                    Node<Flores<String>> nodo;
                    for (int i = 0; i < catalogo.getTabla().length; i++) {
                        nodo = catalogo.getTabla()[i].getCabeza();
                        while (nodo != null) {
                            Flores<String> f = nodo.getDatos();
                            System.out.printf("| %-2d | %-27s | %-9d | %-14s | $%-6.2f |\n",
                                contador, f.getNombre(), f.getCantidad(), f.getTipoDeFlor(), f.getPrecio());
                                nodo = nodo.getNext();
                                contador++;
                            }
                        }

                        System.out.println("+----+-----------------------------+-----------+----------------+---------+");
                        break;

                case 4:
                    System.out.println(ANSI_AZUL + "\n=== Pedidos Actuales ===" + ANSI_RESET);
                    if (pedidos.getSize() == 0) {
                        System.out.println(ANSI_ROJO + "No hay pedidos." + ANSI_RESET);
                        break;
                    }
                    System.out.println("+----+-----------------+-----------------+-------------+-----------+");
                    System.out.printf("| %-2s | %-15s | %-15s | %-11s | %-9s | %-19s |\n",
                            "ID", "Nombre", "Dirección", "Teléfono", "Prioridad", "Fecha");
                    System.out.println("+----+-----------------+-----------------+-------------+-----------+");
                   
                    

                    for (int i = 0; i < pedidos.getSize(); i++) {
                        PriorityNode<Cliente> p = pedidos.getDatos()[i];
                        if (p != null) {
                            Cliente c = p.getDatos();
                            System.out.printf("| %-2s | %-15s | %-15s | %-11s | %-9s | %-19s |\n",
                                    (i + 1), c.getNombre(), c.getDireccion(),
                                    c.getTelefono(), p.getPriority(), c.getFecha().format(formato));
                        }
                    }
                    System.out.println("+----+-----------------+-----------------+-------------+-----------+");
                    break;

                case 5:
                    System.out.println(ANSI_AZUL + "\n=== Historial de Pedidos Registrados ===" + ANSI_RESET);
                    if (historial.isEmpty()) {
                        System.out.println(ANSI_ROJO + "No hay historial de pedidos." + ANSI_RESET);
                        break;
                    }
                    System.out.println("+----+-----------------+-----------------+-------------+");
                    System.out.printf("| %-2s | %-15s | %-15s | %-11s | %-19s |\n",
                            "ID", "Nombre", "Dirección", "Teléfono", "Fecha");
                    System.out.println("+----+-----------------+-----------------+-------------+");
                    Node<Cliente> nodoHist = historial.getDatos().getCabeza();
                    int idHist = 1;
                    while (nodoHist != null) {
                        Cliente c = nodoHist.getDatos();
                        System.out.printf("| %-2s | %-15s | %-15s | %-11s | %-19s |\n",
                                idHist, c.getNombre(), c.getDireccion(), c.getTelefono(), c.getFecha().format(formato));
                        nodoHist = nodoHist.getNext();
                        idHist++;
                    }
                    System.out.println("+----+-----------------+-----------------+-------------+");
                    break;

                case 6:
                System.out.println(ANSI_AZUL + "\n=== Procesar Pedido ===" + ANSI_RESET);
                    if (pedidos.getSize() == 0) {
                        System.out.println(ANSI_ROJO + "No hay pedidos para procesar." + ANSI_RESET);
                        break;
                    }
                    try {
                        PriorityNode<Cliente> pedidoProcesado = pedidos.pop();
                        Cliente c = pedidoProcesado.getDatos();
                        System.out.println(ANSI_AMARILLO + "Pedido procesado:" + ANSI_RESET);
                        System.out.println("Nombre: " + c.getNombre());
                        System.out.println("Dirección: " + c.getDireccion());
                        System.out.println("Teléfono: " + c.getTelefono());
                        System.out.println("Prioridad: " + pedidoProcesado.getPriority());
                    } catch (Exception e) {
                        System.out.println(ANSI_ROJO + "Error al procesar el pedido: " + e.getMessage() + ANSI_RESET);
                    }
                    break;
                    case 7:
                        System.out.println(ANSI_AZUL + "\n=== Información de la Empresa ===" + ANSI_RESET);

                        Node<String> arbolEmpresa = new Node<>("Empresa");
                        Node<String> gael = new Node<>("Gael - Distribuidor");
                        Node<String> carlos = new Node<>("Carlos - Comprador");
                        Node<String> mauricio = new Node<>("Mauricio - Vendedor");
                        arbolEmpresa.agregarHijos(gael);
                        arbolEmpresa.agregarHijos(carlos);
                        arbolEmpresa.agregarHijos(mauricio);
                            System.out.println(ANSI_AMARILLO + "Estructura de la empresa:" + ANSI_RESET);
                            arbolEmpresa.imprimirArbol("");
                            break;

                    case 8:
                    if (pedidos.getSize() == 0) {
                        System.out.println(ANSI_ROJO + "No hay pedidos registrados." + ANSI_RESET);
                        break;
                    }

                    System.out.print("Ingresa el ID del cliente a buscar: ");
                    int IDaBuscar = sc.nextInt();
                    sc.nextLine();

                    Cliente encontradoBusqueda = (Cliente) pedidos.buscarPorID(IDaBuscar);

                    if (encontradoBusqueda!= null){
                                System.out.println(ANSI_AMARILLO + "Cliente encontrado: " + ANSI_RESET); System.out.println("Nombre: " + encontradoBusqueda.getNombre()); System.out.println("Dirección: " + encontradoBusqueda.getDireccion()); System.out.println("Teléfono: " + encontradoBusqueda.getTelefono());
                                System.out.println("Prioridad: " + encontradoBusqueda.getPrioridad());
                                System.out.println("Fecha: " + encontradoBusqueda.getFecha().format(formato));
                                System.out.println("--------------------------------");
                                break;
                    } else {
                        System.out.println(ANSI_ROJO + "No se encontro ningun cliente con ese ID." + ANSI_RESET);
                    }
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
