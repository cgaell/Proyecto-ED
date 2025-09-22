/**
 * Cliente 
 * - ID incremental garantizado (no se repite durante la ejecución).
 * - Timestamp (creadoEn) al momento de crear el peido.
 */
public class Cliente {
    // === ID autoincremental simple ===
    private static int SEQ = 1;   // 1,2,3,...

    private int id;               // siempre único cuando usas el ctor sin ID
    private String nombre;
    private String direccion;
    private String telefono;
    private LocalDateTime creadoEn;

    // Constructor : asigna ID automático
    public Cliente(String nombre, String direccion, String telefono) {
        this.id = SEQ++;                          // <-- aquí se asegura unicidad
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.creadoEn = LocalDateTime.now();
    }

    // Constructor opcional con ID (compatibilidad, no recomendado para nuevos pedidos)
    public Cliente(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.creadoEn = LocalDateTime.now();
    }

    // Getters mínimos
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public LocalDateTime getCreadoEn() { return creadoEn; }

    // Setters si los necesitas 
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
