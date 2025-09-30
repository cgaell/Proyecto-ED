
public class HashTable<T> {
    private SimpleLinkedList<T>[] tabla;
    private int capacidad;



    /**
     * Constructor parametrado
     * @param capacidad dependiendo de la capacidad de la lista enlazada simple
     */
    public HashTable(int capacidad) {
        this.capacidad = capacidad;
        tabla = new SimpleLinkedList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new SimpleLinkedList<>();
        }
    }

    /**
     * metodo para comvertir a hash
     * @param valor el valor que se va a usar (generico)
     * @return el valor hash (puros numeros positivos)
     */
    private int hash(T valor) {
        return Math.abs(valor.hashCode() % capacidad);
    }

    
    /**
     * metodo para insertar datos a la tabla
     * @param valor
     */
    public void insertar(T valor) {
        int indice = hash(valor);
        tabla[indice].insertarCola(valor); 
    }

    
    public boolean buscar(T valor) {
        int indice = hash(valor);
        return tabla[indice].buscar(valor) != -1;
    }

    
    /** metodo para eliminar datos en la tabla
     * @param valor valor seleccionado
     * @return la tabla ya sin el valor que eliminaste
     */
    public boolean eliminar(T valor) {
        int indice = hash(valor);
        return tabla[indice].eliminarNodo(valor);
    }

    
    public SimpleLinkedList<T>[] getTabla() {
    return tabla;
} 
    }
