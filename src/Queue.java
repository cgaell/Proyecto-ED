public class Queue<T> {
    
    SimpleLinkedList<T> datos;
    

    public Queue() {
        this.datos = new SimpleLinkedList<>();
    }



    public boolean isEmpty() {
        return (this.datos.getCabeza() == null);
    }

    public int size() {
        int size = 0;
        Node<T> actual = this.datos.getCabeza();
        while (actual != null) {
            size++;
            actual = actual.getNext();
        }
        return size;
    }

    public void push(T com) {
        // agrega en el tope de la cola el valor correspondiente
        this.datos.insertarCola(com);
    }

    public T pop() throws Exception {
    if (this.isEmpty()) {
        throw new Exception("La cola está vacía");
    }
    Node<T> cabeza = this.datos.getCabeza();
    T resultado = cabeza.getDatos();
    
    // Mover la cabeza al siguiente nodo
    this.datos = new SimpleLinkedList<>(cabeza.getNext());
    
    return resultado;
}


    public T peek() throws Exception {
        T resultado = null;
        if (this.isEmpty()) {
            throw new Exception("La cola está vacía");
        }
        resultado = this.datos.getCabeza().getDatos();
        return resultado;
    }
}
