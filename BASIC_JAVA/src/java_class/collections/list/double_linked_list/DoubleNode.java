package java_class.collections.list.double_linked_list;

public class DoubleNode<E> {

    public E data;
    public DoubleNode<E> next;
    public DoubleNode<E> prev;


    public DoubleNode(E value) {
        this.data = value;
        next = null;
        prev = null;
    }
}
