

public class PriorityQueue<T> {
    private PriorityNode<T>[] datos;
    private int size;

    public PriorityQueue() {
        this.datos = new PriorityNode[10]; // Tamaño inicial
        this.size = 0;
    }


public boolean isEmpty() {
    return datos == null || datos.length == 0;
}

    public void push(int priority, T valor){

        //creamos  nodo a insertar

        PriorityNode<T> nodoDeEntrada = new PriorityNode<>(priority, valor);

        //aumentamos el tamaño de la cola priorizada

        this.size++;

        //si es el  primero lo ponemos en la posición 1 y listo

        if (size ==1){

            datos[0] = nodoDeEntrada;

        } else {

            //Si no  está vacía la cola priorizada           

            //la  posición temporal es la última

            int posicion = size - 1;

            //ponemos  el nodo ahí

            datos[posicion] = nodoDeEntrada;

            //encontramos  la posición del padre

            int posicionPadre = (posicion - 1) / 2;

            //Y  guardamos el valor del padre

            PriorityNode<T> miPadre=  datos[posicionPadre];

            //repetimos  intercambiar el padre con el hijo hasta que el padre

            //tenga  mayor prioridad que el hijo

            while (posicion > 0 && miPadre.getPriority() > nodoDeEntrada.getPriority()

                    ){

                datos[posicion] = miPadre;

                datos[posicionPadre] = nodoDeEntrada;

                posicion = posicionPadre;

                posicionPadre = (posicion - 1) / 2;

                miPadre= datos[posicionPadre];

            }

        }        

    }


   public PriorityNode<T> pop() {
    // Verificamos si la cola está vacía antes de hacer pop
    if (size == 0) {
        throw new IllegalStateException("La cola de prioridad está vacía.");
    }

    // Guardamos el nodo de mayor prioridad (la raíz del heap)
    PriorityNode<T> root = datos[0]; // La raíz siempre está en la posición 0

    // Colocamos el último nodo en la raíz
    datos[0] = datos[size - 1]; // Intercambiamos el último nodo con la raíz

    // Reducimos el tamaño de la cola
    size--;

    // Hacemos el "heapify" para reorganizar el heap
    heapify(0); // Reorganizamos desde la raíz (índice 0)

    // Devolvemos el nodo extraído
    return root;
}

    private void heapify(int posicion) {
    int hijoIzq = 2 * posicion; // Índice del hijo izquierdo
    int hijoDer = 2 * posicion + 1; // Índice del hijo derecho
    int mayor = posicion;

    // Verificamos si el hijo izquierdo existe y tiene mayor prioridad
    if (hijoIzq <= size && datos[hijoIzq].getPriority() > datos[mayor].getPriority()) {
        mayor = hijoIzq;
    }

    // Verificamos si el hijo derecho existe y tiene mayor prioridad
    if (hijoDer <= size && datos[hijoDer].getPriority() > datos[mayor].getPriority()) {
        mayor = hijoDer;
    }

    // Si el nodo actual no es el mayor, intercambiamos
    if (mayor != posicion) {
        PriorityNode<T> temp = datos[posicion];
        datos[posicion] = datos[mayor];
        datos[mayor] = temp;

        // Recursión para asegurar que la estructura del heap se mantenga
        heapify(mayor);
    }
}
    public void getheapify(int posicion) {
    heapify(posicion);
}

public PriorityNode<T> peek() {
    if (isEmpty()) {
        System.out.println("La cola está vacía.");
        return null;
    }
    return datos[0];

}

    public int getSize() {
    return size;
}
public PriorityNode<T>[] getDatos() {
    return datos;
}
public void setSize(int size) {
    this.size = size;
}
}