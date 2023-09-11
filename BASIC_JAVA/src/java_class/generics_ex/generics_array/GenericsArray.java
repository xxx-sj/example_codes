package java_class.generics_ex.generics_array;


import java.io.Serializable;
import java.util.*;
import java.util.function.IntFunction;

//ArrayList copy code
public class GenericsArray<T> extends AbstractList<T> implements List<T>, RandomAccess, Serializable {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENT_DATA = {};

    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    private int size;

    private static final long serialVersionUID = -596698600396814420L;

    transient Object[] elementData;

    //배열의 크기를 인자로 받는 생성자
    public GenericsArray(int initialCapacity) {
        if(initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("맞지않는 배열 사이즈 입니다. "  + initialCapacity);
        }
    }

    //기본 생성자.
    public GenericsArray() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public GenericsArray(Collection<? extends T> c) {
        Object[] a = c.toArray();
        if((size = a.length) != 0) {
            if(c.getClass() == ArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        }
    }

    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        List<Integer> arr = new ArrayList<Integer>();
        Object[] array = new ArrayList<Integer>().toArray();
        return super.toArray(generator);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T get(int index) {
        return null;
    }
}
