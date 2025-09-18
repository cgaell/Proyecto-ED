import java.util.List;

public class Node<T> {
    private T datos;
    private Node<T> siguiente;
    private Node<T> anterior;
    private List<Node<T>> hijos;

    public Node(T datos, Node<T> siguiente, Node<T> anterior, List<Node<T>> hijos) {
        this.datos = datos;
        this.siguiente = siguiente;
        this.anterior = anterior;
        this.hijos = hijos;
    }
    public Node(T datos) {
        this.datos = datos;
        
    }

    public Node(T datos, Node<T> siguiente) {
        this.datos = datos;
        this.siguiente = siguiente;
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

    public void agregarHijos(Node<T> hijo) {
        hijos.add(hijo);
    }

    public boolean eliminarHijo(Node<T> hijo){
        if (hijos != null) {
            return hijos.remove(hijo);
        }
        return false;
    }


}
