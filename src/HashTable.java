

import java.util.*;

public class HashTable<T> {

    public static final int M = 23; // Tamaño del listado  

    List<T>[] datos = new List[M]; // Arreglo de Listas

    // Constructor
    public HashTable() {
        datos = new List[M];
    }

    public boolean isEmpty() {
        return datos == null || datos.length == 0;
    }

    // Función de hash utilizando Math.floorMod() para asegurar que el índice sea no negativo
     private int hashfunction(T valor) {
        return Math.floorMod(valor.hashCode(), M);  // Utiliza floorMod para asegurar que el índice sea positivo
    }

    // Añadir un valor
    public void add(T valor) {
        int key = hashfunction(valor);

        if (datos[key] == null) {
            datos[key] = new ArrayList<>();
        }

        datos[key].add(valor);
    }
    
    public int getHashFunction(T valor){
        return hashfunction(valor);
}
}