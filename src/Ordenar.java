import java.util.Collections;
import java.util.List;

public class Ordenar {
    public static void quickSort(List<Flores<String>> lista, int low, int high, boolean asc) {
        if (low < high) {
            int pi = partition(lista, low, high, asc);
            quickSort(lista, low, pi - 1, asc);
            quickSort(lista, pi + 1, high, asc);
        }
    }

    private static int partition(List<Flores<String>> lista, int low, int high, boolean asc) {
        double pivot = lista.get(high).getPrecio();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (asc) { // menor a mayor
                if (lista.get(j).getPrecio() <= pivot) {
                    i++;
                    Collections.swap(lista, i, j);
                }
            } else { // mayor a menor
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

