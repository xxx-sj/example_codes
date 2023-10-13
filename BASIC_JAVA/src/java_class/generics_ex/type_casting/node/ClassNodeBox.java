package java_class.generics_ex.type_casting.node;

import java_class.generics_ex.type_casting.Writable;

import java.util.HashMap;

public class ClassNodeBox <E extends Writable>{
    private E key;
    HashMap<E, Object> map = new HashMap<>();

    public Node<E> create() {
        return new Node<>();
    }

    public void add(E e) {
        Node<E> node = new Node<>();
        key = e;
        map.put(e, new Object());
    }

    public void print() {
        map.forEach((key, value) -> {
            key.write();
        });
    }
}
