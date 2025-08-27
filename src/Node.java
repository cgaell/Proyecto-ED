


public class Node<T> {
    private T datos;
    private Node<T> siguiente;
    private Node<T> anterior;

    public Node(T datos, Node<T> siguiente, Node<T> anterior) {
        this.datos = datos;
        this.siguiente = siguiente;
        this.anterior = anterior;
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
}
