/**
 *
 */
//implementar objeto para hacer una tabla hash


public class Flores<T> {
    private String nombre;
    private int cantidad;
    private T tipoDeFlor;
    private double precio;

    /** constructor parametrado
     * @param nombre del ramo
     * @param cantidad de ramos en el inventario
     * @param tipoDeFlor tipo de flor q se usa
     * @param precio del ramo
     */
    public Flores(String nombre, int cantidad, T tipoDeFlor, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipoDeFlor = tipoDeFlor;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public T getTipoDeFlor() {
        return tipoDeFlor;
    }

    public void setTipoDeFlor(T tipoDeFlor) {
        this.tipoDeFlor = tipoDeFlor;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
