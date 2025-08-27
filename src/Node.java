


public class Node<I> {
    private I datos;
    private Node<I> siguiente;
    private Node<I> anterior;

    public Node(I datos, Node<I> siguiente, Node<I> anterior) {
        this.datos = datos;
        this.siguiente = null;
        this.anterior = null;
    }
    public Node(I datos) {
        this.datos = datos;
        
    }

    public Node(I datos, Node<I> siguiente) {
        this.datos = datos;
        this.siguiente = siguiente;
    }
    public Node<I> getNext() {
        return siguiente;
    }
    public void setNext(Node<I> siguiente) {
        this.siguiente = siguiente;
    }

    public Node<I> getAnterior() {
        return anterior;
    }
    public void setAnterior(Node<I> anterior) {
        this.anterior = anterior;
    }
    public I getDatos() {
        return datos;
    }
    public void setDatos(I datos) {
        this.datos = datos;
    }
}
