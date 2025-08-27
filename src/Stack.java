

public class Stack<T> {
     //asignar la capacidad del arreglo en la pila
    //en este caso es infinito

    private SimpleLinkedList<T> datos;

    //inicializar atributos
    
    
    

    //constructor para inicializar la pila
    public Stack() {
        this.datos = new SimpleLinkedList<>();
    }

    public boolean isEmpty() {
        return this.datos.getCabeza() == null;
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

    public void push(T valor) {
        this.datos.insertarCabeza(valor);
    }
    
    public T pop() throws Exception {
    

        //si está vacía la pila regresa una excepción 
        if (this.isEmpty()) {
            throw new Exception("La Pila está vacía");
        }

        T resultado = this.datos.getCabeza().getDatos();
        this.datos.eliminarNodo(resultado);
        return resultado;
    }

    public void show() {
       this.datos.mostrarLista();
    }

    public T peek() throws Exception {

        //si está vacía la pila regresa una excepción
        if (this.isEmpty()) {
            throw new Exception("La Pila está vacía");
        }

        return this.datos.getCabeza().getDatos();
    }

    public SimpleLinkedList<T> getDatos() {
        return this.datos;
    }
}