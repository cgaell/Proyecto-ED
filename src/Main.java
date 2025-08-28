import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear catálogo de flores
        SimpleLinkedList<Flores<String>> catalogo = new SimpleLinkedList<>();
        catalogo.insertarCola(new Flores<>("Ramo de Rosas", 30, "Rosas", 350.0));
        catalogo.insertarCola(new Flores<>("Ramo de Girasoles", 20, "Girasoles", 250.0));
        catalogo.insertarCola(new Flores<>("Ramo de Tulipanes", 12, "Tulipanes", 300.0));
        catalogo.insertarCola(new Flores<>("Ramo Mixto", 5, "Variado", 400.0));
        catalogo.insertarCola(new Flores<>("Ramo de Lirios", 10, "Lirios", 200.0));
        catalogo.insertarCola(new Flores<>("Ramo de Orquídeas", 12, "Orquídeas", 1000.0));
        catalogo.insertarCola(new Flores<>("Ramo de 100 Rosas", 2, "Rosas", 2000.0));

        
        // Cola de prioridad para pedidos
        PriorityQueue<Cliente> pedidos = new PriorityQueue<>();

        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== Sistema de Gestión de Pedidos de Florería ===");
            System.out.println("1. Realizar pedido");
            System.out.println("2. Eliminar pedido por nombre");
            System.out.println("3. Consultar catálogo de flores");
            System.out.println("4. Mostrar todos los pedidos");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = 0;
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número del 1 al 5.");
                sc.nextLine(); 
                continue;
            }
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    try {
                        System.out.println("=== Realizar Pedido ===");
                        System.out.print("Nombre del cliente: ");
                        String nombre = sc.nextLine();
                        System.out.print("Dirección: ");
                        String direccion = sc.nextLine();
                        System.out.print("Teléfono: ");
                        String telefono = sc.nextLine();

                        System.out.println("Selecciona el tipo de ramo:");
                        Node<Flores<String>> actual = catalogo.getCabeza();
                        int index = 1;
                        while (actual != null) {
                            Flores<String> f = actual.getDatos();
                            System.out.println(index + ". " + f.getNombre() + " - $" + f.getPrecio());
                            actual = actual.getNext();
                            index++;
                        }

                        System.out.print("Número de ramo: ");
                        int ramoSeleccionado = sc.nextInt();
                        sc.nextLine();

                        if (ramoSeleccionado < 1 || ramoSeleccionado >= index) {
                            System.out.println("Error: Número de ramo inválido.");
                            break;
                        }

                        System.out.print("¿Es urgente? (1: Sí, 2: No): ");
                        int urgente = sc.nextInt();
                        sc.nextLine();
                        if (urgente != 1 && urgente != 2) {
                            System.out.println("Error: Debes ingresar 1 o 2.");
                            break;
                        }

                        Cliente cliente = new Cliente(nombre, direccion, telefono);
                        int prioridad = (urgente == 1) ? 1 : 2;
                        pedidos.push(prioridad, cliente);
                        System.out.println("Pedido agregado con éxito.");

                    } catch (InputMismatchException e) {
                        System.out.println("Error: Entrada inválida, asegúrate de ingresar números donde corresponda.");
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("=== Eliminar Pedido ===");
                    System.out.print("Ingresa el nombre del cliente a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    boolean encontrado = false;
                    try {
                        for (int i = 0; i < pedidos.getSize(); i++) {
                            PriorityNode<Cliente> p = pedidos.getDatos()[i];
                            if (p != null && p.getDatos().getNombre().equalsIgnoreCase(nombreEliminar)) {
                                pedidos.getDatos()[i] = pedidos.getDatos()[pedidos.getSize() - 1]; // reemplazar con último
                                pedidos.setSize(pedidos.getSize() - 1);
                                pedidos.getheapify(i);
                                encontrado = true;
                                System.out.println("Pedido eliminado correctamente.");
                                break;
                            }
                        }
                        if (!encontrado) System.out.println("No se encontró ningún pedido con ese nombre.");
                    } catch (Exception e) {
                        System.out.println("Error al eliminar pedido: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("=== Catálogo de Flores ===");
                    catalogo.mostrarLista();
                    break;

                case 4:
                    System.out.println("=== Pedidos Actuales ===");
                    try {
                        if (pedidos.getSize() == 0) {
                            System.out.println("No hay pedidos.");
                            break;
                        }

                        // Encabezado de tabla
                        System.out.println("+----+-----------------+-----------------+-------------+-----------+");
                        System.out.printf("| %-2s | %-15s | %-15s | %-11s | %-9s |\n",
                                "ID", "Nombre", "Dirección", "Teléfono", "Prioridad");
                        System.out.println("+----+-----------------+-----------------+-------------+-----------+");

                        // Filas con datos
                        for (int i = 0; i < pedidos.getSize(); i++) {
                            PriorityNode<Cliente> p = pedidos.getDatos()[i];
                            if (p != null) {
                                Cliente c = p.getDatos();
                                System.out.printf("| %-2d | %-15s | %-15s | %-11s | %-9d |\n",
                                        (i + 1), c.getNombre(), c.getDireccion(),
                                        c.getTelefono(), p.getPriority());
                            }
                        }

                        // Línea final
                        System.out.println("+----+-----------------+-----------------+-------------+-----------+");

                    } catch (Exception e) {
                        System.out.println("Error al mostrar pedidos: " + e.getMessage());
                    }
                    break;

                case 5:
                    salir = true;
                    System.out.println("Salida ejecutada correctamente.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
