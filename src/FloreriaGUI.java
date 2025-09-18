/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * 
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Interfaz gráfica "bien chingona" para la florería.
 * Usa tus clases existentes: Cliente, Flores, HashTable, PriorityQueue, Stack, Node, SimpleLinkedList.
 *
 * Cómo correr (NetBeans/macOS):
 * - Asegúrate de tener todas las clases en el mismo Source Package (sin paquete o con el mismo package).
 * - Botón verde "Run" con Main Class = FloreriaGUI (ver sección 7).
 */
public class FloreriaGUI extends JFrame {

    // Estado/estructura de datos (reutilizamos tus clases)
    private HashTable<Flores<String>> catalogo;
    private PriorityQueue<Cliente> pedidos;       // cola con prioridad (atención)
    private Stack<Cliente> historial;             // pila de historial
    private static Node<Cliente> arbolpedidos;    // árbol de pedidos (raíz)

    // Componentes UI
    private JTextField txtNombre, txtDireccion, txtTelefono;
    private JSpinner spPrioridad;
    private JComboBox<String> cbRamo;
    private JTable tablaCatalogo, tablaCola, tablaHistorial;
    private DefaultTableModel modeloCatalogo, modeloCola, modeloHistorial;
    private JTree treeArbol;
    private JTextArea txtLog;

    // Para registrar fecha/hora de cada acción/consulta
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Map<String, String> ultimaConsultaPorCliente = new HashMap<>();

    public FloreriaGUI() {
        super("Florería – Panel de Pedidos (GUI)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 720);
        setLocationRelativeTo(null);

        // Look and Feel nativo (macOS) si está disponible
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Paleta sencilla
        Color bg = new Color(248, 250, 252);
        Color fg = new Color(20, 24, 28);
        Color brand = new Color(142, 68, 173); // morado elegante
        getContentPane().setBackground(bg);

        // Inicializa estructuras
        initEstructuras();

        // Construcción de UI
        JTabbedPane tabs = new JTabbedPane();
        tabs.setBorder(new EmptyBorder(10, 10, 10, 10));
        tabs.addTab("Nuevo pedido", crearPanelNuevoPedido(brand));
        tabs.addTab("Cola (Prioridad)", crearPanelCola());
        tabs.addTab("Historial", crearPanelHistorial());
        tabs.addTab("Catálogo", crearPanelCatalogo());
        tabs.addTab("Árbol de pedidos", crearPanelArbol());
        tabs.addTab("Logs", crearPanelLogs());

        setLayout(new BorderLayout());
        add(crearHeader(brand), BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
    }

    private JPanel crearHeader(Color brand) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(brand);
        header.setBorder(new EmptyBorder(12, 16, 12, 16));

        JLabel title = new JLabel("Florería – Gestor visual de pedidos");
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));

        JLabel subtitle = new JLabel("Todo conectado: catálogo, pedidos con prioridad, historial y árbol");
        subtitle.setForeground(new Color(255, 255, 255, 210));

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(title);
        left.add(Box.createVerticalStrut(4));
        left.add(subtitle);

        header.add(left, BorderLayout.WEST);

        JButton btnRefrescar = new JButton("Refrescar todo");
        btnRefrescar.addActionListener(e -> {
            refrescarCatalogo();
            refrescarCola();
            refrescarHistorial();
            refrescarArbol();
            log("Refresco general de la interfaz.");
        });
        header.add(btnRefrescar, BorderLayout.EAST);
        return header;
    }

    private JPanel crearPanelNuevoPedido(Color brand) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(12, 12, 12, 12));

        // Formulario
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 6, 6, 6);
        gc.anchor = GridBagConstraints.WEST;

        txtNombre = new JTextField(18);
        txtDireccion = new JTextField(18);
        txtTelefono = new JTextField(14);

        cbRamo = new JComboBox<>();
        cargarRamosEnCombo();

        spPrioridad = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));

        int row = 0;
        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Nombre del cliente:"), gc);
        gc.gridx = 1; form.add(txtNombre, gc); row++;

        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Dirección:"), gc);
        gc.gridx = 1; form.add(txtDireccion, gc); row++;

        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Teléfono:"), gc);
        gc.gridx = 1; form.add(txtTelefono, gc); row++;

        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Ramo/Catálogo:"), gc);
        gc.gridx = 1; form.add(cbRamo, gc); row++;

        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Prioridad (1–10):"), gc);
        gc.gridx = 1; form.add(spPrioridad, gc); row++;

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = new JButton("Agregar pedido");
        btnAgregar.addActionListener(this::accionAgregarPedido);
        JButton btnEntregar = new JButton("Entregar pedido (pop)");
        btnEntregar.addActionListener(this::accionEntregarPedido);

        JButton btnBuscar = new JButton("Buscar cliente");
        btnBuscar.addActionListener(this::accionBuscarCliente);

        JButton btnEliminar = new JButton("Eliminar por nombre");
        btnEliminar.addActionListener(this::accionEliminarPorNombre);

        acciones.add(btnAgregar);
        acciones.add(btnEntregar);
        acciones.add(btnBuscar);
        acciones.add(btnEliminar);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(form, BorderLayout.NORTH);
        wrapper.add(acciones, BorderLayout.CENTER);

        p.add(wrapper, BorderLayout.NORTH);

        JTextArea hint = new JTextArea(
                "Tips:\n" +
                "• \"Agregar pedido\" guarda en la cola priorizada, en el historial (pila) y en el árbol.\n" +
                "• \"Entregar pedido (pop)\" atiende el de mayor prioridad.\n" +
                "• \"Buscar cliente\" usa el árbol para encontrar por nombre.\n" +
                "• \"Eliminar por nombre\" quita de la cola y del árbol (si existe)."
        );
        hint.setEditable(false);
        hint.setBackground(new Color(0,0,0,0));
        hint.setBorder(new EmptyBorder(10,0,0,0));
        p.add(hint, BorderLayout.CENTER);

        return p;
    }

    private JPanel crearPanelCola() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        modeloCola = new DefaultTableModel(new String[]{"#", "Nombre", "Dirección", "Teléfono", "Prioridad", "Últ. consulta"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaCola = new JTable(modeloCola);
        p.add(new JScrollPane(tablaCola), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Ver cola");
        btnRefrescar.addActionListener(e -> {
            refrescarCola();
            log("Consulta de cola (fecha registrada).");
        });
        p.add(btnRefrescar, BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelHistorial() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        modeloHistorial = new DefaultTableModel(new String[]{"#", "Nombre", "Dirección", "Teléfono", "Últ. consulta"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaHistorial = new JTable(modeloHistorial);
        p.add(new JScrollPane(tablaHistorial), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Ver historial");
        btnRefrescar.addActionListener(e -> {
            refrescarHistorial();
            log("Consulta de historial (fecha registrada).");
        });
        p.add(btnRefrescar, BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelCatalogo() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        modeloCatalogo = new DefaultTableModel(new String[]{"Nombre", "Tipo", "Cantidad", "Precio"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaCatalogo = new JTable(modeloCatalogo);
        p.add(new JScrollPane(tablaCatalogo), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Ver catálogo");
        btnRefrescar.addActionListener(e -> {
            refrescarCatalogo();
            log("Consulta de catálogo (fecha registrada).");
        });
        p.add(btnRefrescar, BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelArbol() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        treeArbol = new JTree(new DefaultMutableTreeNode("Árbol vacío"));
        p.add(new JScrollPane(treeArbol), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Ver árbol");
        btnRefrescar.addActionListener(e -> {
            refrescarArbol();
            log("Consulta del árbol (fecha registrada).");
        });
        p.add(btnRefrescar, BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelLogs() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        p.add(new JScrollPane(txtLog), BorderLayout.CENTER);
        return p;
    }

    // === Acciones ===

    private void accionAgregarPedido(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa nombre, dirección y teléfono.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int prioridad = (int) spPrioridad.getValue();
        Cliente c = new Cliente(nombre, direccion, telefono);

        // Cola con prioridad
        pedidos.push(prioridad, c);

        // Historial (pila) – lo agregamos arriba (push)
        try {
            // Tu Stack usa una lista enlazada; para push, reutilizamos insertarCabeza
            historial.getDatos().insertarCabeza(c);
        } catch (Exception ex) {
            // Si tu Stack tuviera un método push explícito y no getDatos(), cámbialo por historial.push(c);
            historial.getDatos().insertarCabeza(c);
        }

        // Árbol: insertamos como hijo de la raíz (si no hay raíz, el cliente será la raíz)
        if (arbolpedidos == null) {
            arbolpedidos = new Node<>(c, null, null, null);
        } else {
            arbolpedidos.agregarHijos(new Node<>(c, null, null, null));
        }

        // Actualiza UI + log con fecha/hora
        String stamp = LocalDateTime.now().format(fmt);
        ultimaConsultaPorCliente.put(nombre.toLowerCase(), stamp);
        log("[" + stamp + "] Pedido agregado -> " + nombre + " (prio " + prioridad + ")");
        limpiarFormulario();
        refrescarCola();
        refrescarHistorial();
        refrescarArbol();
    }

    private void accionEntregarPedido(ActionEvent e) {
        if (pedidos.getSize() == 0) {
            JOptionPane.showMessageDialog(this, "No hay pedidos en la cola.", "Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        PriorityNode<Cliente> nodo = pedidos.pop(); // mayor prioridad
        Cliente c = nodo.getDatos();

        // Remover del árbol por nombre
        if (arbolpedidos != null) {
            Node<Cliente> nueva = arbolpedidos.eliminarRaiz(c.getNombre());
            arbolpedidos = nueva;
        }

        String stamp = LocalDateTime.now().format(fmt);
        ultimaConsultaPorCliente.put(c.getNombre().toLowerCase(), stamp);
        log("[" + stamp + "] Pedido ENTREGADO -> " + c.getNombre() + " (prio " + nodo.getPriority() + ")");

        refrescarCola();
        refrescarArbol();
    }

    private void accionEliminarPorNombre(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del cliente a eliminar (cola + árbol):");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String target = nombre.trim().toLowerCase();

        boolean eliminado = eliminarDeColaPorNombre(target);
        // Árbol
        if (arbolpedidos != null) {
            Node<Cliente> nueva = arbolpedidos.eliminarRaiz(target);
            arbolpedidos = nueva;
        }

        String stamp = LocalDateTime.now().format(fmt);
        log("[" + stamp + "] Eliminar por nombre \"" + nombre + "\" -> " + (eliminado ? "OK" : "No encontrado en cola"));

        refrescarCola();
        refrescarArbol();
    }

    private void accionBuscarCliente(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del cliente a buscar (árbol):");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String target = nombre.trim().toLowerCase();

        Node<Cliente> encontrado = (arbolpedidos == null) ? null : arbolpedidos.buscarPorNombre(target);
        String stamp = LocalDateTime.now().format(fmt);
        ultimaConsultaPorCliente.put(target, stamp);

        if (encontrado != null) {
            Cliente c = encontrado.getDatos();
            JOptionPane.showMessageDialog(this,
                    "Encontrado:\nNombre: " + c.getNombre() + "\nDirección: " + c.getDireccion() + "\nTel: " + c.getTelefono(),
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            log("[" + stamp + "] Búsqueda -> encontrado \"" + c.getNombre() + "\" en el árbol.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el cliente.", "Resultado", JOptionPane.WARNING_MESSAGE);
            log("[" + stamp + "] Búsqueda -> NO encontrado \"" + nombre + "\".");
        }
        refrescarArbol();
        refrescarCola();   // para refrescar la columna "Últ. consulta"
        refrescarHistorial();
    }

    // === Utilidades de UI/Modelo ===

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        spPrioridad.setValue(5);
        if (cbRamo.getItemCount() > 0) cbRamo.setSelectedIndex(0);
    }

    private void log(String msg) {
        txtLog.append(msg + "\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }

    private void cargarRamosEnCombo() {
        cbRamo.removeAllItems();
        SimpleLinkedList<Flores<String>>[] tabla = catalogo.getTabla();
        for (SimpleLinkedList<Flores<String>> lista : tabla) {
            Node<Flores<String>> n = lista.getCabeza();
            while (n != null) {
                Flores<String> f = n.getDatos();
                cbRamo.addItem(f.getNombre() + " – $" + f.getPrecio());
                n = n.getNext();
            }
        }
    }

    private void refrescarCatalogo() {
        modeloCatalogo.setRowCount(0);
        SimpleLinkedList<Flores<String>>[] tabla = catalogo.getTabla();
        for (SimpleLinkedList<Flores<String>> lista : tabla) {
            Node<Flores<String>> n = lista.getCabeza();
            while (n != null) {
                Flores<String> f = n.getDatos();
                modeloCatalogo.addRow(new Object[]{
                        f.getNombre(), f.getTipoDeFlor(), f.getCantidad(), f.getPrecio()
                });
                n = n.getNext();
            }
        }
    }

    private void refrescarCola() {
        modeloCola.setRowCount(0);
        int size = pedidos.getSize();
        PriorityNode<Cliente>[] arr = pedidos.getDatos();
        for (int i = 0; i < size; i++) {
            PriorityNode<Cliente> p = arr[i];
            if (p != null) {
                Cliente c = p.getDatos();
                String stamp = ultimaConsultaPorCliente.getOrDefault(c.getNombre().toLowerCase(), "-");
                modeloCola.addRow(new Object[]{ (i + 1), c.getNombre(), c.getDireccion(), c.getTelefono(), p.getPriority(), stamp });
            }
        }
    }

    private void refrescarHistorial() {
        modeloHistorial.setRowCount(0);
        Node<Cliente> n = historial.getDatos().getCabeza();
        int i = 1;
        while (n != null) {
            Cliente c = n.getDatos();
            String stamp = ultimaConsultaPorCliente.getOrDefault(c.getNombre().toLowerCase(), "-");
            modeloHistorial.addRow(new Object[]{ i++, c.getNombre(), c.getDireccion(), c.getTelefono(), stamp });
            n = n.getNext();
        }
    }

    private void refrescarArbol() {
        DefaultMutableTreeNode root;
        if (arbolpedidos == null) {
            root = new DefaultMutableTreeNode("Árbol vacío");
        } else {
            root = buildSwingNode(arbolpedidos);
        }
        treeArbol.setModel(new DefaultTreeModel(root));
        for (int i = 0; i < treeArbol.getRowCount(); i++) {
            treeArbol.expandRow(i);
        }
    }

    private DefaultMutableTreeNode buildSwingNode(Node<Cliente> node) {
        Cliente c = node.getDatos();
        DefaultMutableTreeNode swingNode = new DefaultMutableTreeNode(c.getNombre() + " (" + c.getTelefono() + ")");
        if (node.getHijos() != null) {
            for (Node<Cliente> h : node.getHijos()) {
                swingNode.add(buildSwingNode(h));
            }
        }
        return swingNode;
    }

    private boolean eliminarDeColaPorNombre(String nombreLower) {
        int size = pedidos.getSize();
        PriorityNode<Cliente>[] arr = pedidos.getDatos();
        for (int i = 0; i < size; i++) {
            PriorityNode<Cliente> p = arr[i];
            if (p != null && p.getDatos().getNombre().toLowerCase().equals(nombreLower)) {
                // swap con el último y heapify en esa posición (igual que en tu Main)
                arr[i] = arr[size - 1];
                pedidos.setSize(size - 1);
                pedidos.getheapify(i);
                return true;
            }
        }
        return false;
    }

    private void initEstructuras() {
        // Catálogo base (hash table)
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

        pedidos = new PriorityQueue<>();
        historial = new Stack<>();
        arbolpedidos = null; // inicia vacío
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FloreriaGUI().setVisible(true));
    }
}
