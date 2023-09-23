package java_class.collections.queue;

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


    
}
