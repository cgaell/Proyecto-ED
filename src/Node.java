import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T datos;
    private Node<T> siguiente;
    private Node<T> anterior;
    private Node<T> padre;
    private List<Node<T>> hijos;

    private static String ANSI_AMARILLO = "\u001B[33m";
    private static String ANSI_RESET = "\u001B[0m";
    /** constructor parametrado
     * @param datos que se almacenan dentro del nodo
     * @param siguiente para recorrerlos 
     * @param anterior para retroceder en caso de implementar otro tipo de lista
     * @param hijos para implementar arbol
     */
    public Node(T datos, Node<T> siguiente, Node<T> anterior, List<Node<T>> hijos, Node<T> padre) {
        this.datos = datos;
        this.siguiente = siguiente;
        this.anterior = anterior;
        this.hijos = 
        (hijos!=null) ? hijos : new ArrayList<>();
        this.padre = padre;

    }
    /** constructor para enviar a los hijos a una lista de proteccion
     * @param datos
     */
    public Node(T datos) {
        this.datos = datos;
        this.hijos = new ArrayList<>();
        
    }

    /** constructor para inicializar la lista enlazada simple
     * @param datos valores alamcenados
     * @param siguiente recorrido de la lista
     */
    public Node(T datos, Node<T> siguiente) {
        this.datos = datos;
        this.siguiente = siguiente;
        this.hijos = new ArrayList<>();
        this.padre = null;
    }
    public Node<T> getNext() {
        return siguiente;
    }
    public void setNext(Node<T> siguiente) {
        this.siguiente = siguiente;
    }

    public Node<T> getAnterior() {
        return anterior;
    }
    public void setAnterior(Node<T> anterior) {
        this.anterior = anterior;
    }
    public T getDatos() {
        return datos;
    }
    public void setDatos(T datos) {
        this.datos = datos;
    }

    public List<Node<T>> getHijos(){
        return hijos;
    }
    public void setPadre(Node<T> padre) {
        this.padre = padre;
    }

    /** metodo para agregar hijos al arbol
     * @param hijo a agregar
     */
    public void agregarHijos(Node<T> hijo) {
        hijo.setPadre(this);
        hijos.add(hijo);
    }

    /** metodo para eliminar hijos del arbol
     * @param hijo
     * @return
     */
    public boolean eliminarHijo(Node<T> hijo){
        if (hijos != null) {
            return hijos.remove(hijo);
        }
        return false;
    }
   /** metodo para eliminar por nombre del empleado
 * @param nombre a buscar
 * @return true si se llega a eliminar, de lo contrario false
 */

    /** metodo para imprimir el arbol completo
     * @param prefijo indice para ordenar el arbol en la terminal
     */
    public void imprimirArbol(String prefijo) {
        System.out.println(prefijo + datos);
        for (int i = 0; i < hijos.size(); i++) {
            System.out.println(prefijo + "\n |");
            
            hijos.get(i).imprimirArbol(prefijo + "   ");
        }
    }

    /** metodo para buscar el empleado dentro del arbol
     * @param nodo a recorrer
     * @param empleadoBuscar nombre del empleado que se busca
     * @return true si lo encuentra, false de lo contrario
     */
    public boolean buscarempleado(Node<String> nodo, String empleadoBuscar){
        if (nodo.getDatos().toString().contains(empleadoBuscar)){
            System.out.println(ANSI_AMARILLO +"Empleado encontrado " + nodo.getDatos() + " en " + getDeptonombre(nodo) + ANSI_RESET);
            return true;
        }
         
    for (Node<String> hijo : nodo.getHijos()) {
        if (buscarempleado(hijo, empleadoBuscar)) {
            return true;
        }
    }
    return false;
    }

    /** metodo estetico para imprimir en que departamento esta el empleado que esta buscando
     * @param nodo empleado
     * @return nombre del departamento, si no hay departamento en el arbo, desconocido 
     */
    private String getDeptonombre(Node<String> nodo) {
    if (nodo.padre != null){
        return nodo.padre.getDatos().toString();
    }
    return "Desconocido";
}
}

