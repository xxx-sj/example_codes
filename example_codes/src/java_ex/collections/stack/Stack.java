package java_ex.collections.stack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EmptyStackException;

public class Stack<E> implements  StackInterface<E>, Cloneable{

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY  = {};

    private Object[] array;
    private int size;

    public Stack() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public Stack(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    private void resize() {

        //빈 배열인 경우 capacity = 0;
        //최초 생성장에서 배열을 EMPTY_ARRAY로 초기화 시 여기 부분에서 기본용적크기의 배열로 초기화한다.
        if(Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        int arrayCapacity = array.length;
        
        //가득 찼을 경우
        if(size == arrayCapacity) {
            int newSize = arrayCapacity * 2;

            array = Arrays.copyOf(array, newSize);

            return;
        }


        //요소 갯수가 배열의 capacity의 반도 안될 경우
        //줄이기
        if (size < (arrayCapacity / 2)) {

            int newCapacity = (arrayCapacity / 2);

            array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity));
            return;
        }
    }


    @Override
    public E push(E value) {

        //크기 늘리기.
        if(size == array.length) {
            resize();
        }

        array[size] = value; //마지막 위치에 요소 추가
        size++;

        return value;
    }

    @Override
    public E pop() {

        if(size ==  0) {
            throw new EmptyStackException();
        }

        //여기서 size - 1 인 이유는 size는 요소 갯수이다. 즉, length와 같은데,
        //length - 1을 통해 가장 마지막 값을 꺼내는 것이다.
        @SuppressWarnings("unchecked")
        E obj = (E) array[size - 1]; // 가장 위의 요소를 꺼낸다.

        array[size - 1] = null;

        size--;
        resize();


        return obj;
    }


    @SuppressWarnings("unchekced")
    @Override
    public E peek() {

        if(size == 0) {
            throw new EmptyStackException();
        }

        return (E)array[size - 1];
    }


    @Override
    /**
     * search의 거리의 값은 1부터 시작한다.
     */
    public int search(Object value) {
        // value가 null일 때는 eqauls(null)이되어 null pointer exception이 발생할 수 있으니,
        // == 로 null값을 비교해준다.
        if(value == null) {
            //거꾸로 순회하는 이유는 stack같은 경우 가장 아랫부분이 0 이기 때문이다.
            for(int idx = size - 1;  idx >= 0; idx--) {
                if(array[idx] == null) {

                    /**
                     * size - idx인 이유는 다음과같다.
                     * 예를들어 size가 6일 경우, 가장 아랫 값 idx[0]의 값을 인자로 받았다면
                     * search의 return 값은 6이 될것이다. 이것을 다르게 나타내면
                     * size - idx가 되는 것이다.
                     */
                    return size - idx;
                }
            }
        }

        else {
            for(int idx = size - 1; idx >= 0; idx--) {
                if(array[idx].equals(value)) {
                    return size - idx;
                }
            }
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {

        // 저장되어있던 모든 요소를 null 처리 해준다.
        for(int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        // 새로운 스택 객체 생성
        Stack<?> cloneStack = (Stack<?>) super.clone();

        // 새로운 스택의 배열도 생성해주어야 함(내부 객체는 깊은 복사가 되지 않기 때문)
        cloneStack.array = new Object[size];

        // 현재 배열을 새로운 스택의 배열에 값을 복사함
        System.arraycopy(array, 0, cloneStack.array, 0, size);
        return cloneStack;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(array, size, a.getClass());

        System.arraycopy(array, 0, a, 0, size);

        return a;
    }

    public void sort() {
        /**
         *  Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된
         *  정렬 방식을 사용한다.
         *  만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable
         *  에러가 발생한다.
         *  만약 구현되어있을 경우 null로 파라미터를 넘기면
         *  Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[]) array, 0, size, c);
    }
}

