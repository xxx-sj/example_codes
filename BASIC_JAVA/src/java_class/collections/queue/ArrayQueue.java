package java_class.collections.queue;

public class ArrayQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 64; //기본 크기

    private Object[] array;
    private int size;

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

        rear = (rear + 1) % array.length;

        array[rear] = value;
        size++;

        return true;
    }
}
