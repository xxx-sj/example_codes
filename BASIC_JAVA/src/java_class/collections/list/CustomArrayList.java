package java_class.collections.list;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomArrayList<E> implements CustomList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    //배열에 담긴 요소의 개수 [용적크기가 아니다.]
    private int size;

    Object[] array;

    public CustomArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public CustomArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }
    
    //size [요소 갯수]가 capacity에 얼만큼 차있는지 확인하고 적절한 크기에 맞게 capacity 변경
    private void resize() {
        int array_capacity = array.length;

        //Arrays.equals 내부를 살펴보면 for문을 돌면서 요소하나씩 비교한다.
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        if(size == array_capacity) {
            int new_capacity = array_capacity * 2;

            array = Arrays.copyOf(array, new_capacity);

            return;
        }


        //불필요한 공간으로 인해 메모리 차지하는 것을 방지하기 위해
        //하지만 최소한의 용적크기와 비교하여 더 큰 값을 배열로 복사하는 것.
        if(size < (array_capacity / 2)) {
            int new_capacity = array_capacity / 2;

            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
            return;
        }
    }
}
