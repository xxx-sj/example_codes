package java_ex.collections.deque;

import java_ex.collections.queue.Queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 64; //최소 용적
    
    private Object[] array; //요소를 담을 배열
    private int size; //요소 갯수
    
    private int front; //시작 인덱스를 가리키는 변수 (빈 공간임을 유의)
    private int rear; //마지막 요소의 인덱스


    public ArrayDeque() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public ArrayDeque(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    private void resize(int newCapacity) {

        int arrayCapacity = array.length; //현재 용적
        Object[] newArray = new Object[newCapacity]; //새로운 배열

        /*
         * i = new array index
         * j = original array
         * index 요소 개수(size)만큼 새 배열에 값 복사
         */
        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            newArray[i] = array[j % arrayCapacity];
        }

        this.array = null;
        this.array = newArray; // 새 배열을 기존 array의 배열로 덮어씌움

        front = 0;
        rear = size;

    }

    /**
     * offer rear + 1 위치에 값을 넣는다.
     * @param item 큐에 추가할 요소
     * @return
     */
    @Override
    public boolean offer(E item) {
        return offerLast(item);
    }

    /**
     * rear + 1 위치에 값을 넣는다.
     * @param item
     * @return
     */
    public boolean offerLast(E item) {

        //용적이 가득찰 경우
        if((rear + 1) % array.length == front) {
            resize(array.length * 2);
        }

        rear = (rear + 1) % array.length; //rear를 다음 위치로 갱신

        array[rear] = item;
        size++;

        return true;
    }

    /**
     * front 에 값을 넣고 front를 뒤로 한칸 민다.
     * @param item
     * @return
     */
    public boolean offerFirst(E item) {

        //용적이 가득찬 경우
        /**
         * front에 값을 넣는데 front - 1을 해서 계산하는 이유는
         * front에 요소를 넣은 후 front를 뒤로 밀게 되는데, 이때 rear와 같은
         * 위치일 경우 사이즈를 늘려야 하기 때문이다.
         */
        if((front - 1 + array.length) % array.length == rear) {

            resize(array.length * 2);
        }

        array[front] = item;
        front = (front - 1 + array.length) % array.length;
        size++;

        return true;
    }

    /**
     * poll은 element가 없으면 null을 반환한다.
     * @return
     */
    @Override
    public E poll() {
        return pollFirst();
    }

    /**
     * element가 없으면 null을 반환한다.
     * @return
     */
    public E pollFirst() {
        if (size == 0) {
            return null;
        }

        front = (front + 1) % array.length; //front를 앞으로 옮긴다. 해당 위치에는 element가 있다.

        @SuppressWarnings("unchecked")
        E item = (E) array[front]; //반환할 element

        array[front] = null;
        size--;

        //array.length가 기본용적보다 크지만, 들어있는 element의 양은 1/4보다 작다면 resize를 통해
        //배열의 크기를 줄인다.
        if(array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {

            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }

        return item;
    }

    public E remove() {
        return removeFirst();
    }

    public E removeFirst() {
        E item = pollFirst();

        if(item == null)
            throw new NoSuchElementException();

        return item;
    }

    public E pollList() {
        if(size == 0) {
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) array[rear];

        array[rear] = null;

        /**
         * array.length를 더하는 이유는 rear - 1을 했을 경우 rear = 0 일 때
         * -1을 하게되면 rear == -1 이 되기 때문에 미리 array.lenght를 더해준다
         * 이러한 경우는 뒤로 밀 때 array.length를 더해주게 된다.
         */
        rear = (rear - 1 + array.length) % array.length;

        // 용적이 최소 크기(64)보다 크고 요소 개수가 1/4 미만일 경우
        if(array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {

            // 아무리 작아도 최소용적 미만으로 줄이지는 않도록 한다.
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }

        return item;
    }

    public E removeLast() {

        E item = pollList();

        if(item == null) {
            throw  new NoSuchElementException();
        }

        return item;
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        if(size == 0) return null;

        @SuppressWarnings("unckecked")
        E item = (E) array[(front + 1) % array.length];

        return item;
    }

    public E peekLast() {

        if(size == 0)
            return null;

        @SuppressWarnings("unckecked")
        E item = (E) array[rear];

        return item;
    }

    public E element() {
        return getFirst();
    }

    public E getFirst() {
        E item = peek();

        // 앞의 원소가 null 이라면(size = 0) 예외를 던진다.
        if(item == null) {
            throw new NoSuchElementException();
        }
        return item;
    }

    public E getLast() {
        E item = peekLast();

        // 앞의 원소가 null 이라면(size = 0) 예외를 던진다.
        if(item == null) {
            throw new NoSuchElementException();
        }
        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) {

        //front + 1부터 요소가 존재한다. 앞에서 부터 시작.
        int start = (front + 1) % array.length;

        /**
         *  i : 요소 개수만큼만 반복한다.
         *  idx : 원소 위치로, 매 회 (idx + 1) % array.length; 의 위치로 갱신
         */
        for(int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length) {
            if(array[idx].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {

        for(int i = 0; i < array.length; i++) {
            array[i] = null;
        }

        front = rear = size = 0;
    }

    public Object[] toArray() {
        return toArray(new Object[size]);
    }


    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {

        final T[] res;
        // 들어오는 배열의 길이가 덱의 요소 개수보다 작은경우
        if(a.length < size) {
            /*
             * front가 rear보다 뒤에 있을 경우 (또는 요소가 없을 경우 f==r)
             *  ______________________
             *  |  |  |  |  |  |  |  |
             *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
             *    	f        r
             */
            if(front <= rear) {
                return (T[]) Arrays.copyOfRange(array, front + 1, rear + 1, a.getClass());
            }

            /*
             * front가 rear보다 앞에 있을 경우
             *  ______________________
             *  |  |  |  |  |  |  |  |
             *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
             *    	r        f
             */

            res = (T[]) Arrays.copyOfRange(array, 0, size, a.getClass());
            int rearlength = array.length - 1 - front;	// 뒷 부분의 요소 개수

            // 뒷 부분 복사
            if(rearlength > 0) {
                System.arraycopy(array, front + 1, res, 0, rearlength);
            }
            // 앞 부분 복사
            System.arraycopy(array, 0, res, rearlength, rear + 1);

            return res;
        }


        /*
         * front가 rear보다 뒤에 있을 경우 (또는 요소가 없을 경우 f==r)
         *  ______________________
         *  |  |  |  |  |  |  |  |
         *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
         *    	f        r
         */
        if(front <= rear) {
            System.arraycopy(array, front + 1, a, 0, size);
        }


        /*
         * front가 rear보다 앞에 있을 경우
         *  ______________________
         *  |  |  |  |  |  |  |  |
         *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
         *    	r        f
         */
        else {

            int rearlength = array.length - 1 - front;	// 뒷 부분의 요소 개수

            // 뒷 부분 복사
            if(rearlength > 0) {
                System.arraycopy(array, front + 1, a, 0, rearlength);
            }
            // 앞 부분 복사
            System.arraycopy(array, 0, a, rearlength, rear + 1);
        }

        return a;
    }


    @Override
    public Object clone() {

        // super.clone() 은 CloneNotSupportedException 예외를 처리해주어야 한다.
        try {

            @SuppressWarnings("unchecked")
            ArrayDeque<E> clone = (ArrayDeque<E>) super.clone();// 새로운 덱 객체 생성

            // 새로운 덱의 배열도 생성해주어야 함 (내부 객체는 깊은 복사가 되지 않기 때문)
            clone.array = Arrays.copyOf(array, array.length);
            return clone;
        }
        catch(CloneNotSupportedException e) {
            throw new Error(e);
        }
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

        // null 접근 방지를 위해 toArray로 요소만 있는 배열을 얻어 이를 정렬한뒤 덮어씌운다.
        Object[] res = toArray();

        Arrays.sort((E[]) res, 0, size, c);

        clear();
        /*
         *  정렬된 원소를 다시 array에 0부터 차례대로 채운다.
         *  이 때 front = 0 인덱스는 비워야 하므로 사실상 1번째 인덱스부터 채워야 한다.
         *
         */
        System.arraycopy(res, 0, array, 1, res.length);	// res 배열을 array에 복사

        this.rear = this.size = res.length;

    }


}
