/**
 * Main (consola) nivel estudiante:
 * 1) Fecha/hora en pedidos (Cliente.creadoEn).
 * 2) Filtro del catálogo con switch (precio asc/desc/default) SIN LAMBDAS.
 * 3) ID de clientes: AUTOGENERADO al crear pedido; buscar/eliminar por ID.
 *
 */
public class Main {
    // Estructuras
    private static HashTable<Flores<String>> catalogo;
    private static PriorityQueue<Cliente> pedidos;

    // Control simple
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        initCatalogo();
        pedidos = new PriorityQueue<>();

        while (true) {
            System.out.println("\n=== Florería (Menu) ===");
            System.out.println("1) Ver catálogo (con filtro)");
            System.out.println("2) Agregar pedido (ID automático)");
            System.out.println("3) Buscar pedido por ID");
            System.out.println("4) Eliminar pedido por ID");
            System.out.println("5) Ver cola actual");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1": verCatalogoFiltrado(); break;
                case "2": agregarPedido(); break;
                case "3": buscarPedidoPorId(); break;
                case "4": eliminarPedidoPorId(); break;
                case "5": verCola(); break;
                case "0": System.out.println("Bye!"); return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // ---------- (1) Catálogo + Filtro ----------
    private static void verCatalogoFiltrado() {
        List<Flores<String>> lista = obtenerCatalogoComoLista();
        System.out.println("Filtro: 0=Default, 1=Precio asc, 2=Precio desc");
        System.out.print("Elige: ");
        String sel = sc.nextLine().trim();

        switch (sel) {
            case "1":
                // Ascendente (menor a mayor) 
                Collections.sort(lista, new Comparator<Flores<String>>() {
                    @Override
                    public int compare(Flores<String> a, Flores<String> b) {
                        return Double.compare(a.getPrecio(), b.getPrecio());
                    }
                });
                break;
            case "2":
                // Descendente (mayor a menor) 
                Collections.sort(lista, new Comparator<Flores<String>>() {
                    @Override
                    public int compare(Flores<String> a, Flores<String> b) {
                        return Double.compare(b.getPrecio(), a.getPrecio());
                    }
                });
                break;
            default:
                // default: sin cambios
        }

        System.out.println("\n-- Catálogo --");
        for (Flores<String> f : lista) {
            System.out.printf("%-28s  tipo=%-12s  cant=%3d  $%.2f%n",
                    f.getNombre(), f.getTipoDeFlor(), f.getCantidad(), f.getPrecio());
        }
    }

    private static List<Flores<String>> obtenerCatalogoComoLista() {
        List<Flores<String>> out = new ArrayList<>();
        SimpleLinkedList<Flores<String>>[] tabla = catalogo.getTabla();
        for (SimpleLinkedList<Flores<String>> lista : tabla) {
            Node<Flores<String>> n = lista.getCabeza();
            while (n != null) {
                out.add(n.getDatos());
                n = n.getNext();
            }
        }
        return out;
    }

    private static void initCatalogo() {
        catalogo = new HashTable<>(23);
        catalogo.insertar(new Flores<>("Ramo de Rosas", 10, "Rosas", 350.0));
        catalogo.insertar(new Flores<>("Ramo de Girasoles", 8, "Girasoles", 250.0));
        catalogo.insertar(new Flores<>("Ramo de Tulipanes", 12, "Tulipanes", 300.0));
        catalogo.insertar(new Flores<>("Ramo Mixto", 5, "Variado", 400.0));
        catalogo.insertar(new Flores<>("Tulipanes Blancos", 10, "Rosas", 750.0));
        catalogo.insertar(new Flores<>("Manojos Margaritas Blancas", 10, "Margaritas", 200.0));
        catalogo.insertar(new Flores<>("Manojos de Gypsophila", 10, "Gypsophila", 150.0));
        catalogo.insertar(new Flores<>("Violetas", 25, "Violetas", 350.0));
        catalogo.insertar(new Flores<>("Orquídeas", 10, "Orquídeas", 480.0));
        catalogo.insertar(new Flores<>("Crisantemos Blancos", 15, "Crisantemos", 380.0));
        catalogo.insertar(new Flores<>("Gerberas", 20, "Gerberas", 350.0));
        catalogo.insertar(new Flores<>("Lirios", 45, "Lirios", 459.0));
        catalogo.insertar(new Flores<>("Claveles", 25, "Claveles", 390.0));
        catalogo.insertar(new Flores<>("Gladiolas", 42, "Gladiolas", 420.0));
        catalogo.insertar(new Flores<>("Lilas", 25, "Lilas", 425.0));
        catalogo.insertar(new Flores<>("Peonias", 10, "Peonías", 3800.0));
    }

    // ---------- (2) Agregar pedido con ID AUTO + fecha ----------
    private static void agregarPedido() {
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Dirección: ");
            String direccion = sc.nextLine().trim();
            System.out.print("Teléfono: ");
            String telefono = sc.nextLine().trim();
            System.out.print("Prioridad (1-10): ");
            int prio = Integer.parseInt(sc.nextLine().trim());

            if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
                System.out.println("Faltan datos.");
                return;
            }

            // Constructor SIN ID: se asigna automático dentro de Cliente
            Cliente c = new Cliente(nombre, direccion, telefono);
            pedidos.push(prio, c);
            System.out.println("Pedido agregado: ID " + c.getId() + " | " + nombre + " | creado " + c.getCreadoEn().format(fmt));
        } catch (Exception ex) {
            System.out.println("Dato inválido: " + ex.getMessage());
        }
    }

    // ---------- (3) Buscar pedido por ID ----------
    private static void buscarPedidoPorId() {
        try {
            System.out.print("ID a buscar: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            PriorityNode<Cliente>[] arr = pedidos.getDatos();
            int size = pedidos.getSize();

                        
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
                        //Agregar al arbol n-ario para buscarlo
                        Node<Cliente> nuevoPedido = new Node<> (cliente);
                        if (arbolpedidos == null){
                            arbolpedidos = nuevoPedido;
                        } else {
                            arbolpedidos.agregarHijos(nuevoPedido);
                            
                        }
                        

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
                String nombreEliminar = sc.nextLine().toLowerCase();
                boolean encontrado = false;

                try {
                    
                    for (int i = 0; i < pedidos.getSize(); i++) {
                        PriorityNode<Cliente> p = pedidos.getDatos()[i];
                        if (p != null && p.getDatos().getNombre().toLowerCase().equals(nombreEliminar)) {
                            pedidos.getDatos()[i] = pedidos.getDatos()[pedidos.getSize() - 1]; 
                            pedidos.setSize(pedidos.getSize() - 1);
                            pedidos.getheapify(i);
                            encontrado = true;
                            System.out.println(ANSI_AMARILLO + "Pedido eliminado correctamente." + ANSI_RESET);
                            break;
                        }
                    }

                    
                    if (arbolpedidos != null) {
                        Node<Cliente> nuevaRaiz = arbolpedidos.eliminarRaiz(nombreEliminar);
                        if (nuevaRaiz != null) {
                            arbolpedidos = nuevaRaiz;
                            System.out.println(ANSI_AMARILLO + "Pedido eliminado correctamente del arbol" + ANSI_RESET);
                        } else {
                            arbolpedidos = null;
                            System.out.println(ANSI_AMARILLO + "Pedido raiz eliminado, arbol vacio." + ANSI_RESET);
                        }
                        encontrado = true;
                    }

                    if (!encontrado) {
                        System.out.println(ANSI_ROJO + "No se encontró ningún pedido con ese nombre." + ANSI_RESET);
                    }

                } catch (Exception e) {
                    System.out.println(ANSI_ROJO + "Error al eliminar pedido: " + e.getMessage() + ANSI_RESET);
                }
            }
            System.out.println("No encontrado.");
        } catch (Exception ex) {
            System.out.println("Dato inválido.");
        }
    }

    // ---------- (4) Eliminar pedido por ID  ----------
    private static void eliminarPedidoPorId() {
        try {
            System.out.print("ID a eliminar: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            PriorityNode<Cliente>[] arr = pedidos.getDatos();
            int size = pedidos.getSize();

            for (int i = 0; i < size; i++) {
                PriorityNode<Cliente> pn = arr[i];
                if (pn != null && pn.getDatos().getId() == id) {
                    arr[i] = arr[size - 1];         // swap con el último
                    pedidos.setSize(size - 1);      // reducir tamaño
                    pedidos.getheapify(i);          // reacomodar heap
                    System.out.println("Eliminado ID " + id);
                    return;
                }
            }
            System.out.println("No encontrado.");
        } catch (Exception ex) {
            System.out.println("Dato inválido.");
        }
    }

    // ---------- (5) Ver cola ----------
    private static void verCola() {
        PriorityNode<Cliente>[] arr = pedidos.getDatos();
        int size = pedidos.getSize();
        if (size == 0) { System.out.println("(cola vacía)"); return; }
        for (int i = 0; i < size; i++) {
            PriorityNode<Cliente> pn = arr[i];
            if (pn != null) {
                Cliente c = pn.getDatos();
                System.out.printf("%2d) ID=%d  %-18s  prio=%d  creado=%s%n",
                        (i+1), c.getId(), c.getNombre(), pn.getPriority(),
                        (c.getCreadoEn() == null ? "-" : c.getCreadoEn().format(fmt)));
            }
        }
    }
}
