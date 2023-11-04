package java_ex.collections.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 64; //기본 크기

    private Object[] array;
    private int size;

    /**
     * front와 rear의 개념은 다음과 같다.
     * rear은 queue에서 가장 나중에 들어온 위치를 뜻한다.
     * rear+1을 하게되면 다음 요소를 넣을 인덱스를 나타낸다.
     * front는 queue에서 꺼낼 위치 - 1 인덱스를 가리킨다.
     * front + 1을 하게되면 다음으로 떠낼 인덱스의 위치가 된다.
     */
    private int front;
    private int rear;

    // 생성자 기본
    public ArrayQueue() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    // 생성자 capacity를 받을경우
    public ArrayQueue(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    //동적할당을 위한 resize
    private void resize(int newCapacity) {
        int arrayCapacity = array.length; //현재 용적 크기 current_capacity

        Object[] newArray = new Object[newCapacity]; //용적을 변경한 배열


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

    @Override
    public boolean offer(E value) {

        /**
         * rear + 1을 하는 이유는 rear은 현재까지 들어간 요소의 인덱스를 가리키기 때문에
         * 그 다음 인덱스에 값을 넣기 위해 자리가 있는지 확인하기 위해 rear + 1 을 하게 되는 것
         *  % array.length 로 나누는 이유는 원형큐로 구현되어있기 때문에 만약
         *  length = 8, front = 0, rear = 7 인 상태일 때
         *  rear + 1 을하면 8이된다. 하지만
         */
        if((rear + 1) % array.length == front) {
            resize(array.length * 2);
        }

        //rear를 다음 위치로 갱신한다. [다음 요소를 넣을 위치]
        rear = (rear + 1) % array.length;

        //update 한 rear 인덱스에 value를 넣는다.
        array[rear] = value;
        //요소 갯수를 하나 증가시킨다.
        size++;

        return true;
    }


    /**
     * poll 메서드는 remove와는 다르게 NoSuch exception을 
     * 던지지 않고 null을 반환한다.
     */
    @Override
    public E poll() {
        
        if(size == 0) return null;

        // %array.length로 나누는 이유는 원형큐로 
        front = (front + 1) % array.length; //front를 한칸 옮긴다.
        

        @SuppressWarnings("unchecked")
        E item = (E) array[front]; //반환할 데이터를 임시 저장
        
        array[front] = null;
        size--;

        /**
         * array.length > DEFAULT_CAPACITY 조건이 있는 이유는
         * array.length가 적어도 DEFAULT_CAPACITY 보다 커야하기 때문이다.
         * size < (array.length / 4) 인 이유는 배열의 크기는 크나, 내부에 있는
         * 요소의 크기가 배열의 크기의 1/4 일 경우에 빈 공간이 많기 때문에 
         * 줄여주기 위해서이다.
         */

        if(array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {

            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }

        return item;
    }

    public E remove() {

        E item = poll();

        if(item == null) {
            throw new NoSuchElementException();
        }

        return item;
    }


    @Override
    public E peek() {

        if(size == 0) {
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) array[(front + 1) % array.length]; 

        return item;
    }

    public E element() {

        E item = peek();

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

        int start = (front + 1) % array.length;

        /**
         * i : 요소 갯수만큼 반복
         * idx : 원소위치 front + 1 % array.length의 위치로 갱신
         */

        /**
         * idx는 현재 존재하는 요소의 위치를 나타내는 index이다.
         * 증감식에서 idx + 1 % array.length 를 하는 이유는 일반적인 ++이 아니라
         * 원형큐이기 때문에 %array.lengh 를 하는 것이다.
         */
        for(int i = 0, idx = start; i < size; i++, idx =(idx + 1) % array.length) {
            if(array[idx].equals(value)) {
                return true;
            }
        }

        return false;
    }


    /**
     * array 데이터를 모두 null로 바꾸고 front , rear를 초기시점 0으로 돌린다.,
     */
    public void clear() {

        for(int i = 0; i < array.length; i++) {
            array[i] = null;
        }

        front = rear = 0;
    }

    public Object[] toArray() {
        return toArray(new Object[size]);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {

        final T[] res;

        // 들어오는 배열의 길이가 큐의 요소 개수보다 작은경우
        if(a.length < size) {
            /*
             * front가 rear보다 앞에 있을 경우 (또는 요소가 없을 경우 f==r)
             *  ______________________
             *  |  |  | x | x | x | x |  |
             *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
             *    	f               r
             */
            if(front <= rear) {
                return (T[]) Arrays.copyOfRange(array, front + 1, rear + 1, a.getClass());
            }

            /*
             * front가 rear보다 뒤에 있을 경우
             *  ______________________
             *  | x | x |  |  | x | x | x |
             *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
             *    	  r      f
             */
            //new size = 5 // size;
            res = (T[]) Arrays.copyOfRange(array, 0, size, a.getClass());
            // 7 - 1 - front(3); = 3; // -1을 하는 이유는 index가 0부터 시작하기 때문에 갯수계산을 위해 - 1
            int rearlength = array.length - 1 - front;	// 뒷 부분의 요소 개수

            // 뒷 부분 복사
            if(rearlength > 0) {
                //front + 1 위치부터 시작하여 res배열에 0위치에 rearlength만큼 복사
                System.arraycopy(array, front + 1, res, 0, rearlength);
            }
            // 앞 부분 복사
            //array배열의 0 위치부터 시작하여 res 배열에 rearlength위치부터 rear+1 길이만큼 복사.
            System.arraycopy(array, 0, res, rearlength, rear + 1);

            return res;

        }

        /*
         * front가 rear보다 앞에 있을 경우 (또는 요소가 없을 경우 f==r)
         *  ______________________
         *  |  |  |  |  |  |  |  |
         *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
         *    	f        r
         */
        if(front <= rear) {
            System.arraycopy(array, front + 1, a, 0, size);
        }


        /*
         * front가 rear보다 뒤에 있을 경우
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
            ArrayQueue<E> clone = (ArrayQueue<E>) super.clone();// 새로운 큐 객체 생성

            // 새로운 큐의 배열도 생성해주어야 함 (내부 객체는 깊은 복사가 되지 않기 때문)
            clone.array = Arrays.copyOf(array, array.length);
            return clone;
        }
        catch(CloneNotSupportedException e) {
            throw new Error(e);	// 예외처리는 여러분들이 자유롭게 구성하면 된다.
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
         *가득 차있는 경우는 (rear + 1) % array.length == front
         *
즉, 가득 차있는 경우는 말을 가득차있다고 표현했지만 큐에 들어있는 원소의 개수가 array.length 보다 한개 적은경우 맞나요?
         *
         */
        System.arraycopy(res, 0, array, 1, res.length);	// res 배열을 array에 복사

        this.rear = this.size = res.length;

    }



}
