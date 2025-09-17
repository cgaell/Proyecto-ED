import java.util.*;

public class HashTable<T> {

    public static final int M = 23;  
    private List<T>[] datos;  

 
    /**
     * Constructor por default
     */
    public HashTable() {
        datos = new List[M];
    }

    
    /**
     * Revisar si la lista esta vacia
     * @return true si esta vacia, de lo contrario false
     */
    public boolean isEmpty() {
        for (List<T> list : datos) {
            if (list != null && !list.isEmpty()) {
                return false;  
            }
        }
        return true;
    }

    
    /**
     * Metodo para implementar el hashing con numeros
     * @param valor valor ingresado (objeto, string, entero, etc)
     * @return valores hashcodeados con ayuda de Mathfloor, hashea puro numero positivo
     */
    private int hashfunction(T valor) {
        return Math.floorMod(valor.hashCode(), M);  
    }

   
    /**
     * Metodo para agregar valores para la lista de hashing
     * @param valor valor a tomar en cuenta
     */
    public void add(T valor) {
        int key = hashfunction(valor);

        if (datos[key] == null) {
            datos[key] = new ArrayList<>();  
        }

        datos[key].add(valor);  
    }

    
    /**
     * Getter de la lista hasheada
     * @param valor valores dentro de la lista
     * @return null si no hay valores dentro de la lista
     */
    public List<T> get(T valor) {
        int key = hashfunction(valor);
        if (datos[key] != null) {
            return datos[key];
        }
        return null; 
    }

    
    /**
     * Metodo para revisar si contiene un valor
     * @param valor valor a busca r
     * @return el valor buscado
     */
    public boolean contains(T valor) {
        List<T> lista = get(valor);
        return lista != null && lista.contains(valor);  
    }

    
    /**
     * Metodo para eliminar un valor de la lista
     * @param valor valor a tomar en cuenta
     * @return la confirmacion de la eliminacion, de lo contrario false
     */
    public boolean remove(T valor) {
        int key = hashfunction(valor);
        if (datos[key] != null) {
            return datos[key].remove(valor);  
        }
        return false;  
    }

    
    /**
     * getter
     * @param valor valor dentro del metodo
     * @return el metodo hashfunction
     */
    public int getHashFunction(T valor) {
        return hashfunction(valor);
    }

}
