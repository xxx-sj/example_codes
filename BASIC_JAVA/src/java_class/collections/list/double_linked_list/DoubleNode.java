package java_class.collections.list.double_linked_list;

public class DoubleNode<E> {

    E data;
    DoubleNode<E> next;
    DoubleNode<E> prev;


    DoubleNode(E value) {
        this.data = value;
        next = null;
        prev = null;
    }
}
