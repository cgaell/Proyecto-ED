//objeto para usar colas con prioridad
import java.time.*;
public class Cliente{
    private static int cont = 0;
    private int ID;
    private String nombre;
    private String direccion;
    private String telefono;
    private int prioridad;
    private LocalDateTime fecha;

    public Cliente(int ID, String nombre, String direccion, String telefono, int prioridad) {  
        this.ID = cont++;    
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.prioridad = prioridad;
        this.fecha = LocalDateTime.now();
    }
    
    
    public int getPrioridad() {
        return prioridad;
    }
    
    public static int getID() {
        return cont;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFecha(){
        return fecha;
    }

    public void setFecha(LocalDateTime fecha){
        this.fecha = fecha;
    }

    public String toString() {
        return "Cliente{" +  "ID=" + ID + "nombre='" + nombre + '\'' + ", direccion='" + direccion + '\'' + ", telefono='" + telefono + '\'' + '}';
    }

    public int comparacion(Cliente otroCliente){
        return Integer.compare(this.prioridad, otroCliente.prioridad);
    }
}
