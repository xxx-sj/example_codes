package java_ex.collections.node;

public class Node<E> {

    public E data;
    public Node<E> next;

    public Node(E data) {
        this.data = data;
        this.next = null;
    }
}
