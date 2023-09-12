package java_class.generics_ex.gererics_default_ex;

public class Box<E> {
    E element;

    E get() {
        return element;
    }

    void set(E e) {
        this.element = e;
    }
}
