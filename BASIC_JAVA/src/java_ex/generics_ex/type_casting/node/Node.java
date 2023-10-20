package java_ex.generics_ex.type_casting.node;

public class Node<E> {
    public E key;

    public Node() {}

    public Node(E key) {
        this.key = key;
    }

    public void set(E key) {
        this.key = key;
    }
}
