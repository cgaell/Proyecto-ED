import java.time.*;
public class Cliente{
    private static int cont = 1;
    private int ID;
    private String nombre;
    private String direccion;
    private String telefono;
    private int prioridad;
    private LocalDateTime fecha;
    private double precioDePedido;
    private String ramo;
    
        public Cliente(String nombre, String direccion, String telefono, int prioridad, double precioDePedido, String ramo) {  
            this.ID = cont++;    
            this.nombre = nombre;
            this.direccion = direccion;
            this.telefono = telefono;
            this.prioridad = prioridad;
            this.fecha = LocalDateTime.now();
            this.precioDePedido = precioDePedido;
            this.ramo= ramo;
    }
    
    public int getPrioridad() {
        return prioridad;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
            this.ID = ID;
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

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }
    
    public double getPrecioDePedido() {
        return precioDePedido;
    }
    
    public void setPrecioDePedido(double precioDePedido) {
        this.precioDePedido = precioDePedido;
    }

    

    public String toString() {
        return "Cliente{" +  "ID=" + ID + "nombre='" + nombre + '\'' + ", direccion='" + direccion + '\'' + ", telefono='" + telefono + '\'' + '}';
    }

    public int comparacion(Cliente otroCliente){
        return Integer.compare(this.prioridad, otroCliente.prioridad);
    }  

}
