/**
 * Cliente 
 * - ID incremental garantizado (no se repite durante la ejecución).
 * - Timestamp (creadoEn) al momento de crear el peido.
 */
public class Cliente {
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

    public String toString() {
        return "Cliente{" + "nombre='" + nombre + '\'' + ", direccion='" + direccion + '\'' + ", telefono='" + telefono + '\'' + '}';
    }
}

