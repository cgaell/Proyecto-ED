public class Flores<T> {
    private String nombre;
    private int cantidad;
    private T tipoDeFlor;

    public Flores(String nombre, int cantidad, T tipoDeFlor) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipoDeFlor = tipoDeFlor;
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
}
