public class SimpleLinkedList<T> {
	private Node<T> cabeza;
	public SimpleLinkedList() {
		this.cabeza = null;
	}

	public SimpleLinkedList(Node<T> cabeza){
		this.cabeza = cabeza;
	}

	public Node<T> getCabeza() {
		return cabeza;
	}

	public void insertarCabeza(T valor) {
        Node<T> nuevoNodo = new Node<>(valor);
		nuevoNodo.setNext(this.cabeza);
        cabeza = nuevoNodo;
	}
    


    public void insertarCola(T valor) {
        Node<T> nuevoNodo = new Node<>(valor);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Node<T> actual = this.cabeza;
            while (actual.getNext() != null) {
                actual = actual.getNext();
            }
            actual.setNext(nuevoNodo);
        }
    }


    public void mostrarLista() {
        Node<T> actual = this.cabeza;
        while (actual != null) {
            System.out.print(actual.getDatos());
            if (actual.getNext() != null) {
                System.out.print(" -> ");
            }
            actual = actual.getNext();
        }
        System.out.println();
    }

    public int buscar(T valor) {
        Node<T> actual = cabeza;
        int posicion = 1;
        while (actual != null) {
            if (actual.getDatos().equals(valor)) {
                System.out.println("Dato encontrado: " + valor);
                return posicion;
            }
            actual = actual.getNext();
            posicion++;
        }
        return -1;
    }

        public boolean actualizar(T nuevo, T viejo) {
        Node<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDatos().equals(viejo)) {
                actual.setDatos(nuevo);
                return true;
            }
            actual = actual.getNext();
        }
        return false;
}
    public boolean eliminarNodo(T valor) {
        if (cabeza == null){
            return false;
        }

        if (cabeza.getNext() == null){
            if (cabeza.getDatos().equals(valor)){
                cabeza = null;
                return true;
            }
            
        }

        Node<T> actual = cabeza;
        Node<T> anterior = cabeza.getNext();

        while (actual != null) {
            if (actual.getNext().getDatos().equals(valor)){
                if (anterior!= null){
                    anterior.setNext(null);
                }
                else {
                    anterior.setNext(actual.getNext());
                }
                return true;
            }
            anterior = actual;
            actual = actual.getNext();
        }
          return false;  
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.getDatos());
            if (actual.getNext() != null) {
                sb.append(" -> ");
            }
            actual = actual.getNext();
        }
        return sb.toString();
    }

        
}
