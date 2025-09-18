

public class PriorityQueue<T> {
    private PriorityNode<T>[] datos;
    private int size;

    public PriorityQueue() {
        this.datos = new PriorityNode[100]; // Tamaño inicial
        this.size = 0;
    }


