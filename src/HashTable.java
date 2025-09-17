

public class HashTable<T> {
    private SimpleLinkedList<T>[] tabla;
    private int capacidad;



    public HashTable(int capacidad) {
        this.capacidad = capacidad;
        tabla = new SimpleLinkedList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new SimpleLinkedList<>();
        }
    }

    private int hash(T valor) {
        return Math.abs(valor.hashCode() % capacidad);
    }

    
    public void insertar(T valor) {
        int indice = hash(valor);
        tabla[indice].insertarCola(valor); 
    }

    
    public boolean buscar(T valor) {
        int indice = hash(valor);
        return tabla[indice].buscar(valor) != -1;
    }

    
    public boolean eliminar(T valor) {
        int indice = hash(valor);
        return tabla[indice].eliminarNodo(valor);
    }

    
    public SimpleLinkedList<T>[] getTabla() {
    return tabla;
} 
    }

