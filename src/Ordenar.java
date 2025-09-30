import java.util.Collections;
import java.util.List;

public class Ordenar {
    /** metodo que se puede utilizar en todo el codigo para ordenar por precios
     * @param lista el catalogo guardado de flores
     * @param low precios menores
     * @param high precios mayores
     * @param asc en ascendencia, en caso de false es descendente
     */
    public static void quickSort(List<Flores<String>> lista, int low, int high, boolean asc) {
        if (low < high) {
            int pi = partition(lista, low, high, asc);
            quickSort(lista, low, pi - 1, asc);
            quickSort(lista, pi + 1, high, asc);
        }
    }

    /** metodo para comparar uno con uno los precios
     * @param lista el catalogo
     * @param low precio menor
     * @param high precio mayor
     * @param asc true si es ascendente, false si es descendente
     * @return objeto + 1 (devuelve las flores conforme se compraran)
     */
    private static int partition(List<Flores<String>> lista, int low, int high, boolean asc) {
        double pivot = lista.get(high).getPrecio();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (asc) { 
                if (lista.get(j).getPrecio() <= pivot) {
                    i++;
                    Collections.swap(lista, i, j);
                }
            } else { 
                if (lista.get(j).getPrecio() >= pivot) {
                    i++;
                    Collections.swap(lista, i, j);
                }
            }
        }

        Collections.swap(lista, i + 1, high);
        return i + 1;
    }
}

