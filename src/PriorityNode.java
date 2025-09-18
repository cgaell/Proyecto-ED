

public class PriorityNode<T> {
    private int priority;
    private T datos;

    public PriorityNode() {
    }

    public PriorityNode(int priority, T datos) {
        this.priority = priority;
        this.datos = datos;
    }

    public int getPriority() {
        return priority;
    }

    public T getDatos() {
        return datos;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }

    public String toString() {
        return "PriorityNode [prioridad =" + priority + ", datos=" + datos + "]";
    }
}
