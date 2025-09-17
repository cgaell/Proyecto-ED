import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // Colores ANSI para consola
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_ROJO = "\u001B[31m";
    public static final String ANSI_AMARILLO = "\u001B[33m";
    public static final String ANSI_AZUL = "\u001B[34m";

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
        catalogo.insertar(new Flores<>("Lirios", 459, "Lirios", 459.0));
        catalogo.insertar(new Flores<>("Claveles", 25, "Claveles", 390.0));
        catalogo.insertar(new Flores<>("Gladiolas", 420, "Gladiolas", 420.0));
        catalogo.insertar(new Flores<>("Lilas", 25, "Lilas", 425.0));
        catalogo.insertar(new Flores<>("Peonias", 10, "Peoinas", 3800.));

        
        PriorityQueue<Cliente> pedidos = new PriorityQueue<>();

        Stack<Cliente> historial = new Stack<>();

        boolean salir = false;
        while (!salir) {

            
            System.out.println("\n" + ANSI_AZUL + "==================================================" + ANSI_RESET);
            System.out.println(ANSI_AZUL + "                  FLORERIA GMC      " + ANSI_RESET);
            System.out.println(ANSI_AZUL + "==================================================" + ANSI_RESET);
            System.out.println("| 1. Realizar pedido                            |");
            System.out.println("| 2. Eliminar pedido por nombre                 |");
            System.out.println("| 3. Consultar catálogo de flores               |");
            System.out.println("| 4. Mostrar todos los pedidos                  |");
            System.out.println("| 5. Ver historial de pedidos                   |");
            System.out.println("| 6. Procesar pedido                             |");
            System.out.println("| 0. Salir                                      |");
            
            System.out.println(ANSI_AZUL + "==================================================" + ANSI_RESET);
            System.out.print("Selecciona una opción: ");

            int opcion = 0;
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ANSI_ROJO + "Error: Debes ingresar un número del 1 al 7." + ANSI_RESET);
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

                        Cliente cliente = new Cliente(nombre, direccion, telefono);
                        int prioridad = (urgente == 1) ? 1 : 2;
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
                    System.out.print("Ingresa el nombre del cliente a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    boolean encontrado = false;
                    try {
                        for (int i = 0; i < pedidos.getSize(); i++) {
                            PriorityNode<Cliente> p = pedidos.getDatos()[i];
                            if (p != null && p.getDatos().getNombre().equalsIgnoreCase(nombreEliminar)) {
                                pedidos.getDatos()[i] = pedidos.getDatos()[pedidos.getSize() - 1]; 
                                pedidos.setSize(pedidos.getSize() - 1);
                                pedidos.getheapify(i);
                                encontrado = true;
                                System.out.println(ANSI_AMARILLO + "Pedido eliminado correctamente." + ANSI_RESET);
                                break;
                            }
                        }
                        if (!encontrado) System.out.println(ANSI_ROJO + "No se encontró ningún pedido con ese nombre." + ANSI_RESET);
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
                    System.out.printf("| %-2s | %-15s | %-15s | %-11s | %-9s |\n",
                            "ID", "Nombre", "Dirección", "Teléfono", "Prioridad");
                    System.out.println("+----+-----------------+-----------------+-------------+-----------+");
                    for (int i = 0; i < pedidos.getSize(); i++) {
                        PriorityNode<Cliente> p = pedidos.getDatos()[i];
                        if (p != null) {
                            Cliente c = p.getDatos();
                            System.out.printf("| %-2d | %-15s | %-15s | %-11s | %-9d |\n",
                                    (i + 1), c.getNombre(), c.getDireccion(),
                                    c.getTelefono(), p.getPriority());
                        }
                    }
                    System.out.println("+----+-----------------+-----------------+-------------+-----------+");
                    break;

                case 5:
                    System.out.println(ANSI_AZUL + "\n=== Historial de Pedidos ===" + ANSI_RESET);
                    if (historial.isEmpty()) {
                        System.out.println(ANSI_ROJO + "No hay historial de pedidos." + ANSI_RESET);
                        break;
                    }
                    System.out.println("+----+-----------------+-----------------+-------------+");
                    System.out.printf("| %-2s | %-15s | %-15s | %-11s |\n",
                            "ID", "Nombre", "Dirección", "Teléfono");
                    System.out.println("+----+-----------------+-----------------+-------------+");
                    Node<Cliente> nodoHist = historial.getDatos().getCabeza();
                    int idHist = 1;
                    while (nodoHist != null) {
                        Cliente c = nodoHist.getDatos();
                        System.out.printf("| %-2d | %-15s | %-15s | %-11s |\n",
                                idHist, c.getNombre(), c.getDireccion(), c.getTelefono());
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
                    

                case 0:
                    salir = true;
                    System.out.println(ANSI_AMARILLO + "\nSalida ejecutada correctamente." + ANSI_RESET);
                    break;

                default:
                    System.out.println(ANSI_ROJO + "Opción no válida." + ANSI_RESET);
            }
        }
    }
}
